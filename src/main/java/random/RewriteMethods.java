package random;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import br.ucsal.sage.request.model.Request;
import br.ucsal.sage.request.services.RequestServices;
import br.ucsal.sage.user.services.UserServices;

public class RewriteMethods {
	
	
	
	
	UserServices utilities = new UserServices();
	RequestServices utilities2 = new RequestServices();

	// Inserção de usuário no banco de dados
	public boolean createUser(Connection conexao, String email, String senha, String nome, String apelido,
			int tipo_usuario, String sobre_usuario, String cnpj, String endereco, String cidade, String estado,
			String telefone, String celular, String data_criacao) throws SQLException {

		if (!utilities.checkExistingEmail(conexao, email)) {

			if (utilities.validateEmail(email)) {
				Statement st;
				st = conexao.createStatement();
				String sql = "INSERT INTO public.\"USUARIO\"(\r\n"
						+ "	id_usuario, email_usuario, senha_usuario, nome_usuario, cnpj_usuario, "
						+ "data_criacao, endereco_usuario, cidade_usuario, estado_usuario, telefone_usuario, "
						+ "celular_usuario, quantidade_total, tipo_usuario, apelido_usuario, sobre_usuario)\r\n"
						+ "	VALUES ('" + utilities.generateIdUser(conexao) + "', '" + email + "', '" + senha + "', '"
						+ nome + "', '" + cnpj + "' , '" + data_criacao + "', '" + endereco + "', '" + cidade
						+ "', '" + estado + "', '" + telefone + "', '" + celular + "', 0, "
						+ tipo_usuario + ", '" + apelido + "', '" + sobre_usuario + "');";

				st.executeUpdate(sql);

				return true;
			} else {
				System.out.println("E-mail inválido");
				return false;
			}
		} else {
			System.out.println("E-mail informado já existe");
			return false;
		}

	}
	
	
	// Cria um chamado
		public boolean createRequest(Connection conexao, String id_escola, String tipo_lixo, String sobre_chamado, String data_abertura)
				throws SQLException {
			if(confereData(conexao, data_abertura, id_escola)) {
			Statement st;
			st = conexao.createStatement();
			String sql = "INSERT INTO public.\"CHAMADO\"(\r\n"
					+ "	id_chamado, id_escola, id_coletor, data_abertura, data_coleta, previsao_coleta, status_chamado, tipo_lixo, cod_verificacao, hora_abertura, sobre_chamado)\r\n"
					+ "	VALUES ('" + utilities2.generateCodeRequest(conexao) + "', '" + id_escola + "', null , '"
					+ data_abertura + "', null, null, 1, '" + tipo_lixo + "', '"
					+ utilities2.generateVerificationCode() + "', '" + utilities2.getHourNow() + "', '" + sobre_chamado
					+ "');";

			st.executeUpdate(sql);

			return true;
			}else {
				System.out.println("Data de abertura anterior a data de criação do usuário");
				return false;
			}
			}
		
		private boolean confereData(Connection conexao, String data_criação, String id_escola) throws SQLException {
			Statement st;
			st = conexao.createStatement();
			String sql = "SELECT data_criacao FROM public.\"USUARIO\" "
					+ " WHERE id_usuario = '" + id_escola +"'";

			ResultSet rs = st.executeQuery(sql);
			
			rs.next();
			
			
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate data_user = LocalDate.parse(rs.getString(1));
			LocalDate data = LocalDate.parse(data_criação, formatter);
			
			
			if(!data.isBefore(data_user)) {
				return true;
			}else 
				return false;
			


			
			
		}
		
		
		
		public boolean takeRequest(Connection conexao, String id_request, String id_collector, String forecast_date)
				throws SQLException {

			if (validateDate(conexao, forecast_date, id_request)) {
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
			
			System.out.println("Data de previsão anterior a data de abertura do chamado");

			return false;
		}
		
		
		public boolean validateDate(Connection conexao, String date, String id_chamado) throws SQLException {

			Statement st;
			st = conexao.createStatement();
			String sql = "SELECT data_abertura FROM public.\"CHAMADO\" "
					+ " WHERE id_chamado = '" + id_chamado +"'";

			ResultSet rs = st.executeQuery(sql);
			
			rs.next();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate data_user = LocalDate.parse(rs.getString(1));
			LocalDate data = LocalDate.parse(date, formatter);
			
			
			if(!data.isBefore(data_user)) {
				return true;
			}else 
				return false;
		}
		
		
		public boolean completedRequest(Connection conexao, String id_request, String verification_code,
				String collection_date, double amount_collected) throws SQLException {

			RequestServices utilities = new RequestServices();

			

					Request chamado = utilities.createObjectRequest(conexao, id_request);
					Statement st;
					st = conexao.createStatement();
					String sql = "UPDATE public.\"CHAMADO\"\r\n" + "	SET status_chamado = 3, data_coleta = '"
							+ collection_date + "' \r\n" + "	WHERE id_chamado = '" + id_request + "';"
							+ "UPDATE public.\"USUARIO\"\r\n" + "SET quantidade_total = quantidade_total + "
							+ amount_collected + "\r\n" + "where id_usuario = '" + chamado.getId_coletor()
							+ "' OR  id_usuario = '" + chamado.getId_escola() + "';";

					st.executeUpdate(sql);

					// Salvando os dados na tabela DADOS_COLETA
					utilities.saveCollectionData(conexao, utilities.createObjectRequest(conexao, id_request),
							amount_collected);

					return true;
				

			
			}
		}


