package br.ucsal.sage.user.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import br.ucsal.sage.user.model.User;

public class UserServices {

	// Gerando um ID para o novo usuário
	public String generateIdUser(Connection conexao) throws SQLException {

		Statement st;
		st = conexao.createStatement();
		// Capturando o último ID cadastrado
		String sql = "SELECT MAX(id_usuario)\r\n" + "	FROM public.\"USUARIO\";";
		ResultSet rs = st.executeQuery(sql);
		rs.next();

		// Conferindo se existe algum ID cadastrado no banco
		if (rs.getString(1) != null)

			// Caso exista pelo menos 1 ID cadastrado no banco
			return formatID(rs.getString(1));
		else

			// Caso não exista nenhum ID cadastrado no banco
			return "00001";
	}

	// Verificando se o CPJ informado já existe no banco de dados
	public boolean validateCNPJ(Connection conexao, String cnpj) throws SQLException {
		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT cnpj_usuario FROM \"USUARIO\" " + "WHERE cnpj_usuario = '" + cnpj + "'";

		ResultSet rs = st.executeQuery(sql);

		if (rs.next())
			return true;

		else

			return false;

	}

	// Capturando a data que o usuário se cadastrou na plataforma
	public String getDateNow() {

		DateTimeFormatter data = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		return LocalDateTime.now().format(data);
	}

	// Checando se o email informado já existe na base de dados
	public boolean checkExistingEmail(Connection conexao, String email) throws SQLException {
		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT email_usuario FROM \"USUARIO\" " + "WHERE email_usuario = '" + email + "'";

		ResultSet rs = st.executeQuery(sql);

		if (rs.next())
			return true;

		else

			return false;
	}

	// Formatando um ID novo
	private String formatID(String last_id) {

		int numero = Integer.valueOf(last_id) + 1;

		last_id = String.valueOf(numero);

		// Acrescentando 0 no incio do ID
		while (last_id.length() < 5) {
			last_id = "0" + last_id;
		}

		return last_id;
	}

	// Verificando se a String informada é um e-mail
	public boolean validateEmail(String email) {

		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
			return true;
		} catch (AddressException ex) {
			return false;
		}

	}

	// Verificando se a senha informada é a mesma cadastrada pa o email
	public boolean validatePasswordEmail(Connection conexao, String email, String senha) throws SQLException {
		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT senha_usuario FROM \"USUARIO\" " + "WHERE email_usuario = '" + email + "'";

		ResultSet rs = st.executeQuery(sql);

		rs.next();

		if (rs.getString(1).equals(senha)) {
			return true;
		}

		return false;
	}

	// Criando um objeto Usuário através dos dados capturados do banco de dados
	public User createObjectUser(ResultSet rs) throws SQLException {
		User usuario = new User();

		rs.next();

		usuario.setId_usuario(rs.getString(1));
		usuario.setEmail(rs.getString(2));
		usuario.setSenha(rs.getString(3));
		usuario.setNome(rs.getString(4));
		usuario.setCnpj(rs.getString(5));
		usuario.setData_criacao(rs.getString(6));
		usuario.setEndereco(rs.getString(7));
		usuario.setCidade(rs.getString(8));
		usuario.setEstado(rs.getString(9));
		usuario.setTelefone(rs.getString(10));
		usuario.setCelular(rs.getString(11));
		usuario.setApelido(rs.getString(12));
		usuario.setSobre_usuario(rs.getString(13));
		usuario.setQuantidade_reciclada(rs.getDouble(14));
		usuario.setTipo_usuario(rs.getInt(15));

		return usuario;
	}

	// Verificando se o usuário pode mudar de e-mail
	// Para essa verificação é avaliado se já existe o email no banco de dados
	// Caso já exista, é verificado se o email está vinculado ao id do usuário que
	// está tentanado fazer a alteração
	// Caso não esteja, a alteração não é permitida
	public boolean validateAlterEmail(Connection conexao, String email, String id_user) throws SQLException {

		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT id_usuario FROM \"USUARIO\" " + "WHERE email_usuario = '" + email + "'";

		ResultSet rs = st.executeQuery(sql);

		if (rs.next()) {
			if (rs.getString(1).equals(id_user))
				return true;
			else
				return false;

		}

		else

			return true;
	}

	// Verificando se todos os campos obrigatórios foram preenchidos
	public boolean validateMandatoryData(String email, String senha, String nome, String apelido, String cnpj,
			String endereco, String cidade, String estado) {

		if (email.replaceAll(" ", "").equals("") || senha.replaceAll(" ", "").equals("")
				|| nome.replaceAll(" ", "").equals("") || apelido.replaceAll(" ", "").equals("")
				|| cnpj.replaceAll(" ", "").equals("") || endereco.replaceAll(" ", "").equals("")
				|| cidade.replaceAll(" ", "").equals("") || estado.replaceAll(" ", "").equals(""))
			return false;

		return true;

	}

}
