package br.ucsal.sage.reports.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.ucsal.sage.reports.model.Reports;

public class ReportsServices {

	// Capturando o ano de registro do usuário
	public int getRegistrationYear(Connection conexao, String id_user) throws SQLException {

		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT EXTRACT (YEAR FROM data_criacao)\r\n" + "	FROM public.\"USUARIO\" "
				+ "WHERE id_usuario = '" + id_user + "';";

		ResultSet rs = st.executeQuery(sql);

		rs.next();

		return rs.getInt(1);
	}

	// Retornando a lista de coletas desde o ano de registro do usuário até o ano
	// atual
	public ArrayList<Reports> generateListByYear(String id_user, int registration_year, int current_year,
			Connection conexao) throws SQLException {
		ArrayList<Reports> list = new ArrayList<Reports>();

		for (int i = registration_year; i <= current_year; i++) {
			Statement st;
			st = conexao.createStatement();
			String sql = "SELECT SUM(quantidade_coleta)\r\n" + "	FROM public.\"DADOS_COLETA\"\r\n"
					+ "	WHERE EXTRACT(YEAR FROM data_coleta) = " + i + "AND (id_coletora = '" + id_user
					+ "' OR id_escola = '" + id_user + "');";

			ResultSet rs = st.executeQuery(sql);

			rs.next();

			Reports reports = new Reports();
			reports.setAno(i);
			reports.setValor(rs.getDouble(1));

			list.add(reports);
		}

		return list;
	}

}
