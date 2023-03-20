package br.ucsal.sage.request.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;

import br.ucsal.sage.request.model.Request;
import br.ucsal.sage.request.services.RequestServices;
@Controller
public class RequestController {

	RequestServices services = new RequestServices();

	// Cria um chamado
	public boolean createRequest(Connection conexao, String id_escola, String tipo_lixo, String sobre_chamado)
			throws SQLException {

		Statement st;
		st = conexao.createStatement();
		String sql = "INSERT INTO public.\"CHAMADO\"(\r\n"
				+ "	id_chamado, id_escola, id_coletor, data_abertura, data_coleta, previsao_coleta, status_chamado, tipo_lixo, cod_verificacao, hora_abertura, sobre_chamado)\r\n"
				+ "	VALUES ('" + services.generateCodeRequest(conexao) + "', '" + id_escola + "', null , '"
				+ services.getDateNow() + "', null, null, 1, '" + tipo_lixo + "', '"
				+ services.generateVerificationCode() + "', '" + services.getHourNow() + "', '" + sobre_chamado + "');";

		st.executeUpdate(sql);

		return true;
	}

	// Retorna todos os chamados associados a um usuário
	public ArrayList<Request> findRequestByUser(Connection conexao, String id_user) throws SQLException {
		ArrayList<Request> principal = new ArrayList<Request>();
		Statement st;
		st = conexao.createStatement();
		// Pega todos os chamados do usuário (não inclui chamados abertos)
		String sql = services.queryByUserType(conexao, id_user);

		ResultSet rs = st.executeQuery(sql);

		principal.addAll(services.generateRequestList(rs));

		// Se o usuário for escola, procura todos os chamados sem coletor da escola que
		// não
		// conseguem ser contemplados na primeira busca
		if (services.getUserType(conexao, id_user) == 1) {
			Statement st2;
			st2 = conexao.createStatement();
			sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
					+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
					+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario\r\n"
					+ "	FROM public.\"CHAMADO\" as chamado "
					+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
					+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
					+ " WHERE id_escola = '" + id_user + "' AND id_coletor IS NULL;";

			ResultSet rs2 = st.executeQuery(sql);

			principal.addAll(services.generateRequestList(rs2));
		}

		return principal;
	}

	// Coletor assumindo coleta
	// São módificados no chamado o id do coletor, o status e a previsão de coleta
	public boolean takeRequest(Connection conexao, String id_request, String id_collector, String forecast_date)
			throws SQLException {

		if (services.validateDate(forecast_date)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			LocalDate data = LocalDate.parse(forecast_date, formatter);

			Statement st;
			st = conexao.createStatement();
			String sql = "UPDATE public.\"CHAMADO\"\r\n" + "	SET id_coletor= '" + id_collector
					+ "', previsao_coleta = '" + data + "', status_chamado = 2\r\n" + "	WHERE id_chamado = '"
					+ id_request + "';";

			st.executeUpdate(sql);

			return true;
		}

		System.out.println("Problema na data");

		return false;
	}

	// Escola cancelando chamado
	// São modificados a previsão de coleta e status do chamado
	public boolean cancelRequest(Connection conexao, String id_request) throws SQLException {
		Statement st;
		st = conexao.createStatement();
		String sql = "UPDATE public.\"CHAMADO\"\r\n" + "	SET status_chamado = 5, previsao_coleta = null \r\n"
				+ "	WHERE id_chamado = '" + id_request + "';";

		st.executeUpdate(sql);

		return true;
	}

	// Coletor cancelando uma coleta
	// São modificados o id do coletor, previsão de coleta e o status do chamado
	public boolean cancelCollect(Connection conexao, String id_request) throws SQLException {
		Statement st;
		st = conexao.createStatement();
		String sql = "UPDATE public.\"CHAMADO\"\r\n"
				+ "	SET status_chamado = 1, previsao_coleta = null, id_coletor = null \r\n" + "	WHERE id_chamado = '"
				+ id_request + "';";

		st.executeUpdate(sql);

		return true;
	}

	// Finalizando uma coleta
	// Na tabela CHAMADO será modificado o status e data de coleta
	// Na tabela USUARIO será modificado a quantidade total de lixo reciclado da
	// escola e do coletor
	// Na tabela DADOS_COLETA será inserido o resumo de dados da coleta
	public boolean completedRequest(Connection conexao, String id_request, String verification_code,
			String collection_date, double amount_collected) throws SQLException {

		// Verificando se o código de coleta está correto
		if (services.validateCollection(conexao, verification_code, id_request)) {

			Request chamado = services.createObjectRequest(conexao, id_request);
			Statement st;
			st = conexao.createStatement();
			String sql = "UPDATE public.\"CHAMADO\"\r\n" + "	SET status_chamado = 3, data_coleta = '"
					+ collection_date + "' \r\n" + "	WHERE id_chamado = '" + id_request + "';"
					+ "UPDATE public.\"USUARIO\"\r\n" + "SET quantidade_total = quantidade_total + " + amount_collected
					+ "\r\n" + "where id_usuario = '" + chamado.getId_coletor() + "' OR  id_usuario = '"
					+ chamado.getId_escola() + "';";

			st.executeUpdate(sql);

			// Salvando os dados na tabela DADOS_COLETA
			services.saveCollectionData(conexao, services.createObjectRequest(conexao, id_request), amount_collected);

			return true;
		} else {

			return false;
		}

	}

	// Metódo de busca para a escola
	public ArrayList<Request> requesterSearch(Connection conexao, String search_word, String id_user)
			throws SQLException {
		ArrayList<Request> list = new ArrayList<Request>();
		// Buscando pelo código do chamado
		Request request = services.createObjectRequest(conexao, search_word.toUpperCase());

		// Caso não exista nenhum código de chamado a busca é feita pelo tipo de lixo e
		// nome do colégio
		if (request.getId_chamado() == null || !request.getId_escola().equals(id_user)) {
			list.addAll(services.queryByCollectorName(conexao, search_word, id_user));
			list.addAll(services.removeDuplicates(list, services.queryByGarbageType(conexao, search_word, id_user, 2)));
			list.addAll(services.removeDuplicates(list, services.queryByStatus(conexao, search_word, id_user, 1)));
		} else
			// Caso exista, não é necessária a busca pelos parâmetros anteriores
			list.add(request);

		return list;
	}

	// Metódo de busca para o coletor
	public ArrayList<Request> collectorSearch(Connection conexao, String search_word, String id_user)
			throws SQLException {
		ArrayList<Request> list = new ArrayList<Request>();

		// Buscando pelo código do chamado
		Request request = services.createObjectRequest(conexao, search_word.toUpperCase());

		// Caso não exista nenhum código de chamado a busca é feita pelo tipo de lixo e
		// nome do colégio
		if (request.getId_coletor() == null || !request.getId_coletor().equals(id_user)) {
			
			list.addAll(services.queryByRequesterName(conexao, search_word, id_user, 0));
			list.addAll(services.removeDuplicates(list, services.queryByGarbageType(conexao, search_word, id_user, 0)));
			list.addAll(services.removeDuplicates(list, services.queryByStatus(conexao, search_word, id_user, 0)));
			
			} else
			// Caso exista, não é necessária a busca pelos parâmetros anteriores
			list.add(request);

		return list;
	}

	

	// Filtrando os chamados entre ABERTO, CANCELADO, EM COLETA e CONCLUÍDO
	public ArrayList<Request> filterRequest(Connection conexao, String id_user, int status) throws SQLException {

		Statement st;

		st = conexao.createStatement();

		ResultSet rs;

		String sql;
		if (services.getUserType(conexao, id_user) == 1) {
			ArrayList<Request> temp = new ArrayList<Request>();
			sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
					+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
					+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario, user_coletor.nome_usuario\r\n"
					+ "	FROM public.\"CHAMADO\" as chamado "
					+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
					+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
					+ "INNER JOIN public.\"USUARIO\" as user_coletor ON user_coletor.id_usuario = chamado.id_coletor"
					+ " WHERE id_escola = '" + id_user + "' AND status_chamado = " + status + ";";

			rs = st.executeQuery(sql);

			temp.addAll(services.generateRequestList(rs));

			Statement st2 = conexao.createStatement();

			sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
					+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
					+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario\r\n"
					+ "	FROM public.\"CHAMADO\" as chamado "
					+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
					+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
					+ " WHERE (id_escola = '" + id_user + "' AND id_coletor = IS NULL) AND status_chamado = " + status
					+ ";";

			ResultSet rs2 = st2.executeQuery(sql);

			temp.addAll(services.generateRequestList(rs2));

			return temp;

		} else {

			sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
					+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
					+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario, user_coletor.nome_usuario\r\n"
					+ "	FROM public.\"CHAMADO\" as chamado "
					+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
					+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
					+ "INNER JOIN public.\"USUARIO\" as user_coletor ON user_coletor.id_usuario = chamado.id_coletor"
					+ " WHERE id_coletor = '" + id_user + "' AND status_chamado = " + status + ";";

			rs = st.executeQuery(sql);

			return services.generateRequestList(rs);
		}
	}

	// Capturando o status de um chamado
	public int getStatusRequest(Connection conexao, String id_request) throws SQLException {

		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT status_chamado	FROM public.\"CHAMADO\" " + " WHERE id_chamado = '" + id_request + "'";

		ResultSet rs = st.executeQuery(sql);

		rs.next();

		return rs.getInt(1);

	}

	// Fazendo busca nos chamados abertos
	public ArrayList<Request> searchInOpenRequests(Connection conexao, String search_word, String id_user,
			int user_type) throws SQLException {
		ArrayList<Request> list = new ArrayList<Request>();

		Request request = services.createObjectRequest(conexao, search_word.toUpperCase());

		if (request.getId_chamado() == null) {
			list.addAll(services.queryByRequesterName(conexao, search_word, id_user, 1));
			list.addAll(services.removeDuplicates(list, services.queryByGarbageType(conexao, search_word, id_user, 1)));
		} else
			list.add(request);
		return list;

	}

	// Metodo para auxiliar outras classes a acessar o metódo de criar um objeto do
	// tipo Request
	public Request createObjectRequest(Connection conexao, String id_request) throws SQLException {

		return services.createObjectRequest(conexao, id_request);
	}

	// Comparando hora de inciio e fim
	public boolean validateHour(String start_hour, String end_hour) {

		return services.validateHour(start_hour, end_hour);
	}

	// Gerando uma matriz atráves de um ArraList
	public Object[][] generateObjectArray(ArrayList<Request> array, int user_type) {

		return services.buildObjectArray(array, user_type);
	}

	// Organizando um array
	// Ordenação de chamados
	public ArrayList<Request> organizeArray(ArrayList<Request> list, int order_type, int user_type) {

		return services.organizeArray(list, order_type, user_type);
	}

	// Metodo para auxiliar outras classes a acessar o metódo de validar uma data
	// Verifica se a data é anterior ao dia atual
	public boolean validateDate(String collection_date) {

		return services.validateDate(collection_date);
	}

	// Validando se a data de coleta é posterior a dtaa de abertura do chamado
	public boolean validateCollectDate(String open_date, String forecast_date) {

		return services.validateCollectDate(open_date, forecast_date);
	}

	

}
