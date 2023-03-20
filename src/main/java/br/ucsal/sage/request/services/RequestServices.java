package br.ucsal.sage.request.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import br.ucsal.sage.request.model.Request;

public class RequestServices {

	// Ajustando o tipo de busca de acordo com o tipo de usuário
	// 1 - Escola
	// 2 - Coletor
	public String queryByUserType(Connection conexao, String id_user) throws SQLException {

		if (getUserType(conexao, id_user) == 1)
			return "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
					+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
					+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario, user_coletor.nome_usuario\r\n"
					+ "	FROM public.\"CHAMADO\" as chamado "
					+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
					+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
					+ "INNER JOIN public.\"USUARIO\" as user_coletor ON user_coletor.id_usuario = chamado.id_coletor"
					+ " WHERE id_escola = '" + id_user + "'";
		else
			return "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
					+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
					+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario, user_coletor.nome_usuario\r\n"
					+ "	FROM public.\"CHAMADO\" as chamado "
					+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
					+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
					+ "INNER JOIN public.\"USUARIO\" as user_coletor ON user_coletor.id_usuario = chamado.id_coletor"
					+ " WHERE id_coletor = '" + id_user + "'";
	}

	// Capturando o tipo de usuario
	public int getUserType(Connection conexao, String id_user) throws SQLException {
		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT tipo_usuario\r\n" + "	FROM public.\"USUARIO\"" + "WHERE id_usuario = '" + id_user + "';";
		ResultSet rs = st.executeQuery(sql);

		rs.next();

		return rs.getInt(1);
	}

	// Gerando um ArrayList com todos os chamados retornados pela busca
	public ArrayList<Request> generateRequestList(ResultSet rs) throws SQLException {
		ArrayList<Request> list = new ArrayList<Request>();

		while (rs.next()) {
			Request chamado = new Request();

			chamado.setId_chamado(rs.getString(1));
			chamado.setId_escola(rs.getString(2));
			chamado.setId_coletor(rs.getString(3) == null ? "-" : rs.getString(3));
			chamado.setData_abertura(rs.getString(4));
			chamado.setData_coleta(rs.getString(5) == null ? "-" : rs.getString(5));
			chamado.setPrevisao_coleta(rs.getString(6) == null ? "-" : rs.getString(6));
			chamado.setStatus_int(rs.getInt(7));
			chamado.setTipo_lixo(rs.getString(8));
			chamado.setCodigo_verificacao(rs.getString(9));
			chamado.setHora_abertura(rs.getString(10));
			chamado.setSobre_chamado(rs.getString(11).substring(0, rs.getString(11).lastIndexOf("@")));
			chamado.setDisponibilidade(rs.getString(11).substring(rs.getString(11).lastIndexOf("@") + 1));
			chamado.setStatus_chamado(rs.getString(12));
			chamado.setNome_escola(rs.getString(13));
			if (rs.getMetaData().getColumnCount() == 14)
				chamado.setNome_coletor(rs.getString(14));
			else
				chamado.setNome_coletor(" - ");
			list.add(chamado);

		}

		return list;
	}

	// Criando dinamicamente o código de verificação
	public String generateVerificationCode() {
		Random generator = new Random();

		String code = "";

		char char_ASCII;

		int number;

		for (int i = 0; i < 3; i++) {
			number = generator.nextInt(26) + 65;

			// Gerando uma letra aleatoria de 65 a 90 de acordo com a tabela ASCII
			char_ASCII = (char) number;

			code = code + char_ASCII;
		}

		for (int i = 0; i < 4; i++)
			code = code + generator.nextInt(10);

		return code;
	}

	// Criando dinamicamente um ID pro chamado
	public String generateCodeRequest(Connection conexao) throws SQLException {

		Statement st;
		st = conexao.createStatement();

		// Capturando o último ID cadastrado
		String sql = "SELECT MAX(id_chamado)\r\n" + "	FROM public.\"CHAMADO\";";
		ResultSet rs = st.executeQuery(sql);

		rs.next();

		// Conferindo se existe algum ID cadastrado no banco
		if (rs.getString(1) != null)

			// Caso exista pelo menos 1 ID cadastrado no banco
			return formatID(rs.getString(1));
		else

			// Caso não exista nenhum ID cadastrado no banco
			return "COD0001";
	}

	// Formatando um ID novo
	private String formatID(String last_id) {

		last_id = last_id.substring(3, 7);

		int numero = Integer.valueOf(last_id) + 1;

		last_id = String.valueOf(numero);

		// Acrescentando 0 no incio do ID
		while (last_id.length() < 4) {
			last_id = "0" + last_id;
		}

		return "COD" + last_id;
	}

	// Capturando a data que o chamado foi aberto
	public String getDateNow() {

		DateTimeFormatter data = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		return LocalDateTime.now().format(data);
	}

	// Capturando a hora que o chamado foi aberto
	public String getHourNow() {
		DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");

		return LocalDateTime.now().format(hora);
	}

	// Validando uma data informada
	public boolean validateDate(String date) {

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			LocalDate data = LocalDate.parse(date, formatter);

			if (data.isBefore(LocalDate.now()))
				return false;

			else
				return true;

		} catch (Exception e) {
			return false;
		}

	}

	// Verificando se o código de verificação informado é o mesmo que consta no
	// sistema
	public boolean validateCollection(Connection conexao, String verification_code, String id_request)
			throws SQLException {

		Statement st;
		st = conexao.createStatement();

		String sql = "SELECT cod_verificacao FROM public.\"CHAMADO\" WHERE id_chamado = '" + id_request + "';";

		ResultSet rs = st.executeQuery(sql);

		rs.next();

		if (rs.getString(1).equalsIgnoreCase(verification_code))
			return true;
		else
			return false;
	}

	// Retornando todos os dados de um chamado atráves do id do chamado
	public Request createObjectRequest(Connection conexao, String id_request) throws SQLException {
		Request request = new Request();

		// Conferindo se o chamado existe
		// Caso não exista o meótdo retorna um objeto vazio
		if (checkExistingRequest(conexao, id_request)) {
			Statement st;
			st = conexao.createStatement();

			// Pesquisando um chamado com id_coletor diferente de null
			String sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
					+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
					+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario, user_coletor.nome_usuario\r\n"
					+ "	FROM public.\"CHAMADO\" as chamado "
					+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
					+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
					+ "INNER JOIN public.\"USUARIO\" as user_coletor ON user_coletor.id_usuario = chamado.id_coletor"
					+ " WHERE id_chamado = '" + id_request + "'";

			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				request.setId_chamado(rs.getString(1));
				request.setId_escola(rs.getString(2));
				request.setId_coletor(rs.getString(3));
				request.setData_abertura(rs.getString(4));
				request.setData_coleta(rs.getString(5) == null ? "-" : rs.getString(5));
				request.setPrevisao_coleta(rs.getString(6) == null ? "-" : rs.getString(6));
				request.setStatus_int(rs.getInt(7));
				request.setTipo_lixo(rs.getString(8));
				request.setCodigo_verificacao(rs.getString(9));
				request.setHora_abertura(rs.getString(10));
				request.setSobre_chamado(rs.getString(11).substring(0, rs.getString(11).lastIndexOf("@")));
				request.setDisponibilidade(rs.getString(11).substring(rs.getString(11).lastIndexOf("@") + 1));
				request.setStatus_chamado(rs.getString(12));
				request.setNome_escola(rs.getString(13));
				request.setNome_coletor(rs.getString(14));
			}

			// Verificando se a primeira busca contemplou algum resultado
			// Caso não tenha, será feita uma nova busca onde id_coletor é null
			if (request.getId_chamado() == null) {
				Statement st2;
				st2 = conexao.createStatement();

				sql = "SELECT id_chamado, id_escola, data_abertura, "
						+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
						+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario\r\n"
						+ "	FROM public.\"CHAMADO\" as chamado "
						+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
						+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
						+ " WHERE id_chamado = '" + id_request + "'";

				ResultSet rs2 = st.executeQuery(sql);

				if (rs2.next()) {
					request.setId_chamado(rs2.getString(1));
					request.setId_escola(rs2.getString(2));
					request.setData_abertura(rs2.getString(3));
					request.setData_coleta(rs2.getString(4) == null ? "-" : rs2.getString(5));
					request.setPrevisao_coleta(rs2.getString(5) == null ? "-" : rs2.getString(6));
					request.setStatus_int(rs2.getInt(6));
					request.setTipo_lixo(rs2.getString(7));
					request.setCodigo_verificacao(rs2.getString(8));
					request.setHora_abertura(rs2.getString(9));
					request.setSobre_chamado(rs2.getString(10).substring(0, rs2.getString(10).lastIndexOf("@")));
					request.setDisponibilidade(rs2.getString(10).substring(rs2.getString(10).lastIndexOf("@") + 1));
					request.setStatus_chamado(rs2.getString(11));
					request.setNome_escola(rs2.getString(12));

				}
			}
		}
		return request;

	}

	// Inserindo o resumo dos dados da coleta na tabela DADOS_COLETA
	public void saveCollectionData(Connection conexao, Request request, double amount_collected) throws SQLException {

		Statement st;
		st = conexao.createStatement();
		String sql = "INSERT INTO public.\"DADOS_COLETA\"(\r\n"
				+ "	id_escola, id_coletora, data_coleta, quantidade_coleta, id_chamado)\r\n" + "	VALUES ('"
				+ request.getId_escola() + "', '" + request.getId_coletor() + "', '" + request.getData_coleta() + "', "
				+ amount_collected + ", '" + request.getId_chamado() + "');";

		st.executeUpdate(sql);

	}

	// Checar se um chamado existe
	private boolean checkExistingRequest(Connection conexao, String id_request) throws SQLException {
		Statement st;
		st = conexao.createStatement();

		String sql = "SELECT count(*) FROM \"CHAMADO\" where id_chamado =  '" + id_request + "'";

		ResultSet rs = st.executeQuery(sql);

		rs.next();

		if (rs.getInt(1) > 0)

			return true;

		return false;
	}

	// Remover valores duplicados do ArrayList
	public ArrayList<Request> removeDuplicates(ArrayList<Request> list, ArrayList<Request> list2) {

		if (list2.size() > 0 && list.size() > 0) {
			int i = 0, j = 0;
			for (Request r : list) {
				for (Request r2 : list2) {
					j = 0;
					if (list2.get(j).getId_chamado().equals(list.get(i).getId_chamado())) {
						list2.remove(j);
						if (j > 0)
							j--;
						break;
					}
					j++;
				}
				i++;
			}
		}

		return list2;
	}

	// Comparando se data de incicio é menor ou igual hora de finalização
	public boolean validateHour(String start_hour, String end_hour) {
		int start = Integer.valueOf(start_hour);
		int end = Integer.valueOf(end_hour);

		if (end >= start)
			return true;
		return false;
	}

	// Criando uma matriz atráves e um ArrayList
	public Object[][] buildObjectArray(ArrayList<Request> array, int user_type) {

		Object[][] ob = new Object[array.size()][6];
		if (array.size() > 0) {
			int i = 0;
			for (Request r : array) {
				ob[i][0] = r.getId_chamado();
				if (user_type == 1)
					ob[i][1] = r.getNome_coletor();
				else
					ob[i][1] = r.getNome_escola();
				ob[i][2] = r.getTipo_lixo();
				ob[i][3] = r.getStatus_chamado();
				LocalDate data = LocalDate.parse(r.getData_abertura());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				ob[i][4] = formatter.format(data);
				if (r.getData_coleta().equals("-"))
					ob[i][5] = r.getData_coleta();
				else {
					data = LocalDate.parse(r.getData_coleta());
					formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					ob[i][5] = formatter.format(data);
				}
				i++;
			}
		}
		return ob;
	}

	// Ordeando chamados
	public ArrayList<Request> organizeArray(ArrayList<Request> list, int order_type, int user_type) {

		ArrayList<String> saveParam = new ArrayList<String>();
		ArrayList<Request> arrayOrganized = new ArrayList<Request>();

		switch (order_type) {
			case 1:
				for (Request r : list)
					saveParam.add(r.getStatus_chamado());
				Collections.sort(saveParam);

				break;

			case 2:
				for (Request r : list)
					saveParam.add(r.getStatus_chamado());
				Collections.sort(saveParam, Collections.reverseOrder());
				break;

			case 3:
				if (user_type == 1) {
					for (Request r : list)
						saveParam.add(r.getNome_coletor());
					Collections.sort(saveParam);
				} else {
					for (Request r : list)
						saveParam.add(r.getNome_escola());
					Collections.sort(saveParam);
				}
				break;

			case 4:
				if (user_type == 1) {
					for (Request r : list)
						saveParam.add(r.getNome_coletor());
					Collections.sort(saveParam, Collections.reverseOrder());
				} else {
					for (Request r : list)
						saveParam.add(r.getNome_escola());
					Collections.sort(saveParam, Collections.reverseOrder());
				}
				break;

			case 5:
				for (Request r : list)
					saveParam.add(r.getData_abertura());
				Collections.sort(saveParam);
				break;

			case 6:
				for (Request r : list)
					saveParam.add(r.getData_abertura());
				Collections.sort(saveParam, Collections.reverseOrder());
				break;

			case 7:
				for (Request r : list)
					saveParam.add(r.getData_coleta());
				Collections.sort(saveParam);
				break;

			case 8:
				for (Request r : list)
					saveParam.add(r.getData_coleta());
				Collections.sort(saveParam, Collections.reverseOrder());
				break;

		}

		if (order_type == 1 || order_type == 2) {
			for (String s : saveParam) {

				for (Request r : list) {
					if (r.getStatus_chamado().equals(s)) {
						arrayOrganized.add(r);
						list.remove(r);
						break;
					}

				}

			}
		}

		if (order_type == 3 || order_type == 4) {
			boolean aux;
			for (String s : saveParam) {

				for (Request r : list) {
					if (user_type == 1)
						aux = r.getNome_coletor().equals(s);
					else
						aux = r.getNome_escola().equals(s);
					if (aux) {
						arrayOrganized.add(r);
						list.remove(r);
						break;
					}

				}

			}
		}

		if (order_type == 5 || order_type == 6) {
			for (String s : saveParam) {

				for (Request r : list) {

					if (r.getData_abertura().equals(s)) {
						arrayOrganized.add(r);
						list.remove(r);
						break;
					}

				}

			}
		}

		if (order_type == 7 || order_type == 8) {
			for (String s : saveParam) {

				for (Request r : list) {

					if (r.getData_coleta().equals(s)) {
						arrayOrganized.add(r);
						list.remove(r);
						break;
					}

				}

			}
		}

		return arrayOrganized;
	}

	// Validando data de coleta informada
	public boolean validateCollectDate(String open_date, String forecast_date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			LocalDate data = LocalDate.parse(forecast_date, formatter);

			if (data.isBefore(LocalDate.parse(open_date)))
				return false;

			else
				return true;

		} catch (Exception e) {
			return false;
		}
	}
	
	// Pesquisando um chamado atráves do nome da escola
		// Esse metódo pode ser acessado em dois casos:
		// 1 - Coletor buscando em suas coletas (contexto = 0)
		// 2 - Coletor buscando em chamados abertos (contexto = 1)
		public ArrayList<Request> queryByRequesterName(Connection conexao, String search_word, String id_user,
				int contexto) throws SQLException {

			Statement st;
			st = conexao.createStatement();
			String sql;
			if (contexto == 0)
				sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
						+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
						+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario, user_coletor.nome_usuario\r\n"
						+ "	FROM public.\"CHAMADO\" as chamado "
						+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
						+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
						+ "INNER JOIN public.\"USUARIO\" as user_coletor ON user_coletor.id_usuario = chamado.id_coletor"
						+ " WHERE id_coletor = '" + id_user + "' AND user_escola.nome_usuario ~* '" + search_word + "'";

			else
				sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
						+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
						+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario\r\n"
						+ "	FROM public.\"CHAMADO\" as chamado "
						+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
						+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"

						+ " WHERE status_chamado = 1 AND user_escola.nome_usuario ~* '" + search_word + "'";

			ResultSet rs = st.executeQuery(sql);

			return generateRequestList(rs);

		}

		// Pesquisando um chamado atráves do nome da coletora
		public ArrayList<Request> queryByCollectorName(Connection conexao, String search_word, String id_user)
				throws SQLException {

			Statement st;
			st = conexao.createStatement();
			String sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
					+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
					+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario, user_coletor.nome_usuario\r\n"
					+ "	FROM public.\"CHAMADO\" as chamado "
					+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
					+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
					+ "INNER JOIN public.\"USUARIO\" as user_coletor ON user_coletor.id_usuario = chamado.id_coletor"
					+ " WHERE id_escola = '" + id_user + "' AND user_coletor.nome_usuario ~* '" + search_word + "'";

			ResultSet rs = st.executeQuery(sql);

			return generateRequestList(rs);

		}

		// Pesquisa por tipo de lixo
		// Esse metódo pode ser acessado em 3 casos:
		// 1 - Coletor/Escola buscando em suas coletas (contexto = 0)
		// 2 - Coletor buscando em chamados abertos (contexto = 1)
		// 3 -Escola pesquisando em chamados abertos (contexto = 2)
		public ArrayList<Request> queryByGarbageType(Connection conexao, String search_word, String id_user, int contexto)
				throws SQLException {
			ArrayList<Request> list = new ArrayList<Request>();
			Statement st;
			st = conexao.createStatement();
			String sql;
			if (contexto == 0)
				sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
						+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
						+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario, user_coletor.nome_usuario\r\n"
						+ "	FROM public.\"CHAMADO\" as chamado "
						+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
						+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
						+ "INNER JOIN public.\"USUARIO\" as user_coletor ON user_coletor.id_usuario = chamado.id_coletor"
						+ " WHERE (id_escola = '" + id_user + "' OR id_coletor = '" + id_user + "') AND tipo_lixo ~* '"
						+ search_word + "'";

			else if (contexto == 1)
				sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
						+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
						+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario\r\n"
						+ "	FROM public.\"CHAMADO\" as chamado "
						+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
						+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"

						+ " WHERE  status_chamado = 1 AND tipo_lixo ~* '" + search_word + "'";
			else
				sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
						+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
						+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario\r\n"
						+ "	FROM public.\"CHAMADO\" as chamado "
						+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
						+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"

						+ " WHERE  status_chamado = 1 AND id_escola = '" + id_user + "' AND tipo_lixo ~* '" + search_word
						+ "'";
			ResultSet rs = st.executeQuery(sql);

			return generateRequestList(rs);

		}

		// Pesquisa pelo Status
		// Esse metódo pode ser acessado em 3 casos:
		// 1 - Coletor/Escola buscando em suas coletas (contexto = 0)
		// 3 -Escola pesquisando em chamados abertos (contexto = 1)
		public ArrayList<Request> queryByStatus(Connection conexao, String search_word, String id_user, int contexto)
				throws SQLException {

			Statement st;
			st = conexao.createStatement();
			String sql;
			if (contexto == 0) {
				sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
						+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
						+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario, user_coletor.nome_usuario\r\n"
						+ "	FROM public.\"CHAMADO\" as chamado "
						+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
						+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
						+ "INNER JOIN public.\"USUARIO\" as user_coletor ON user_coletor.id_usuario = chamado.id_coletor"
						+ " WHERE id_coletor = '" + id_user + "'AND descricao_status ~* '" + search_word + "'";
				ResultSet rs = st.executeQuery(sql);

				return generateRequestList(rs);

			} else {
				ArrayList<Request> list = new ArrayList<Request>();

				sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
						+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
						+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario, user_coletor.nome_usuario\r\n"
						+ "	FROM public.\"CHAMADO\" as chamado "
						+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
						+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
						+ "INNER JOIN public.\"USUARIO\" as user_coletor ON user_coletor.id_usuario = chamado.id_coletor"
						+ " WHERE id_escola = '" + id_user + "' AND descricao_status ~* '" + search_word + "'";
				ResultSet rs = st.executeQuery(sql);

				Statement st2;
				st2 = conexao.createStatement();
				sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
						+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
						+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario\r\n"
						+ "	FROM public.\"CHAMADO\" as chamado "
						+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
						+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"

						+ " WHERE id_escola = '" + id_user + "' AND descricao_status ~* '" + search_word + "'";
				ResultSet rs2 = st2.executeQuery(sql);
				list.addAll(generateRequestList(rs));
				list.addAll(removeDuplicates(list, generateRequestList(rs2)));
				return list;
			}

		}

}
