package br.ucsal.sage.user.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Controller;

import br.ucsal.sage.user.model.Adress;
import br.ucsal.sage.user.model.User;
import br.ucsal.sage.user.services.UserServices;
@Controller
public class UserController {

	UserServices services = new UserServices();

	// Inserção de usuário no banco de dados
	public boolean createUser(Connection conexao, String email, String senha, String nome, String apelido,
			int tipo_usuario, String sobre_usuario, String cnpj, String endereco, String cidade, String estado,
			String telefone, String celular) throws SQLException {
		//Verificando se todos os dados obrigatórios foram informados
		if (services.validateMandatoryData(email, senha, nome, apelido, cnpj, endereco, cidade, estado)) {

			// Verificando se o CNPJ informado já foi cadastrado
			if (services.validateCNPJ(conexao, cnpj)) {

				// Verificando se o email informado já foi cadastrado
				if (!services.checkExistingEmail(conexao, email)) {
					
					//Verificando se o email informado é válido
					if (services.validateEmail(email)) {
						Statement st;
						st = conexao.createStatement();
						String sql = "INSERT INTO public.\"USUARIO\"(\r\n"
								+ "	id_usuario, email_usuario, senha_usuario, nome_usuario, cnpj_usuario, "
								+ "data_criacao, endereco_usuario, cidade_usuario, estado_usuario, telefone_usuario, "
								+ "celular_usuario, quantidade_total, tipo_usuario, apelido_usuario, sobre_usuario)\r\n"
								+ "	VALUES ('" + services.generateIdUser(conexao) + "', '" + email + "', '" + senha
								+ "', '" + nome + "', '" + cnpj + "' , '" + services.getDateNow() + "', '" + endereco
								+ "', '" + cidade + "', '" + estado + "', '" + telefone + "', '" + celular + "', 0, "
								+ tipo_usuario + ", '" + apelido + "', '" + sobre_usuario + "');";

						st.executeUpdate(sql);

						return true;
					}

				}
			}

		}

		return false;

	}

	// Autenticação de usuário
	public boolean validateLogin(Connection conexao, String email, String senha) throws SQLException {
//Verificando se o email informado existe
		if (services.checkExistingEmail(conexao, email)) {
			// Verificando se senha e email combinam
			if (services.validatePasswordEmail(conexao, email, senha))
				return true;

		}

		return false;

	}

	// Capturando todos os dados de um usuário através do email
	public User getAllUserData(Connection conexao, String email) throws SQLException {

		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT id_usuario, email_usuario, senha_usuario, nome_usuario, "
				+ "cnpj_usuario, data_criacao, endereco_usuario, cidade_usuario, "
				+ "estado_usuario, telefone_usuario, celular_usuario,"
				+ "apelido_usuario, sobre_usuario, quantidade_total, tipo_usuario\r\n"
				+ "	FROM public.\"USUARIO\" WHERE email_usuario = '" + email + "';";

		ResultSet rs = st.executeQuery(sql);

		return services.createObjectUser(rs);

	}

	// Alterando dados do usuário
	public boolean alterUserData(Connection conexao, String id_user, String email, String senha, String nome,
			String apelido, String sobre_usuario, String cnpj, String endereco, String cidade, String estado,
			String telefone, String celular) throws SQLException {
		if (services.validateMandatoryData(email, senha, nome, apelido, cnpj, endereco, cidade, estado)) {
			// Verificando se o email informado é válido
			if (services.validateEmail(email)) {

				// Verificando se o email pode ser alterado
				if (services.validateAlterEmail(conexao, email, id_user)) {

					Statement st;
					st = conexao.createStatement();
					String sql = "UPDATE public.\"USUARIO\"\r\n" + "	SET email_usuario= '" + email
							+ "', senha_usuario= '" + senha + "', nome_usuario= '" + nome + "', cnpj_usuario= '" + cnpj
							+ "', endereco_usuario= '" + endereco + "', cidade_usuario= '" + cidade
							+ "', estado_usuario= '" + estado + "', telefone_usuario= '" + telefone
							+ "', celular_usuario= '" + celular + "', apelido_usuario= '" + apelido
							+ "', sobre_usuario= '" + sobre_usuario + "'\r\n" + "	WHERE id_usuario = '" + id_user
							+ "';";

					st.executeUpdate(sql);
					System.out.println("Alterações realizadas");
					return true;
				} else {
					System.out.println("E-mail informado já está em uso");
					return false;
				}
			} else {
				System.out.println("E-mail inválido");
				return false;
			}
		} else
			return false;

	}

	// Preenchendo o endereço do usuário com base no CEP informado
	public Adress getAdressByZipCode(String zip_code) {

		String complete_adress = null;

		String url = "jdbc:postgresql://localhost:5432/LOCALIDADES";
		String usuario = "postgres";
		String senha = "postgres";
		Connection conexao;
		Adress adress = new Adress();

		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			Statement st;
			st = conexao.createStatement();
			String sql = "SELECT cep, descricao, nome_bairro\r\n" + "	FROM public.endereco \r\n"
					+ "	INNER JOIN public.bairro ON bairro.id_bairro = endereco.bairro\r\n" + "	where cep = '"
					+ zip_code + "'\r\n" + "	;";

			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {

				adress.setBairro(rs.getString(3));
				adress.setCep(rs.getString(1));
				adress.setLogradouro(rs.getString(2));

			}
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return adress;

	}

	// Capturando todos os dados de um usuário pelo id
	public User getUserDataById(Connection conexao, String id_user) throws SQLException {

		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT id_usuario, email_usuario, senha_usuario, nome_usuario, "
				+ "cnpj_usuario, data_criacao, endereco_usuario, cidade_usuario, "
				+ "estado_usuario, telefone_usuario, celular_usuario,"
				+ "apelido_usuario, sobre_usuario, quantidade_total, tipo_usuario\r\n"
				+ "	FROM public.\"USUARIO\" WHERE id_usuario = '" + id_user + "';";

		ResultSet rs = st.executeQuery(sql);

		return services.createObjectUser(rs);

	}

}
