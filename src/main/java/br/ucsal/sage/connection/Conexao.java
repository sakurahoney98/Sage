package br.ucsal.sage.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private String url;
	private String usuario;
	private String senha;
	private Connection conexao;

	public Conexao() {
		// Dados de conexão
		url = "jdbc:postgresql://localhost:5432/SAGE";
		usuario = "postgres";
		senha = "postgres";

		// Tentando fazer conexão com o banco de dados
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conexão estabelecida");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Connection getConexao() {
		return conexao;
	}

	public static void main(String[] args) {
		Conexao con = new Conexao();
		con.getConexao();
	}

}
