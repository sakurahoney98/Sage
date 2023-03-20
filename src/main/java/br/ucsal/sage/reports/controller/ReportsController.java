package br.ucsal.sage.reports.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;

import br.ucsal.sage.reports.model.Reports;
import br.ucsal.sage.reports.services.ReportsServices;
@Controller
public class ReportsController {

	ReportsServices services = new ReportsServices();

//Gerando relatório de coleta por ano	
	public ArrayList<Reports> getReportsByYear(Connection conexao, String id_user) throws SQLException {

		int current_year = LocalDate.now().getYear();

		int registration_year = services.getRegistrationYear(conexao, id_user);

		return services.generateListByYear(id_user, registration_year, current_year, conexao);

	}

//Gerando relatório de coleta por mes/ano
	public ArrayList<Reports> getReportsByMonth(Connection conexao, String id_user, int reference_year)
			throws SQLException {
		ArrayList<Reports> list = new ArrayList<Reports>();

		for (int i = 1; i <= 12; i++) {
			Statement st;
			st = conexao.createStatement();
			String sql = "SELECT SUM(quantidade_coleta)\r\n" + "	FROM public.\"DADOS_COLETA\"\r\n"
					+ "	WHERE EXTRACT(MONTH FROM data_coleta) = " + i + " AND EXTRACT(YEAR FROM data_coleta) =  "
					+ reference_year + " AND (id_coletora = '" + id_user + "' OR id_escola = '" + id_user + "');";

			ResultSet rs = st.executeQuery(sql);

			rs.next();

			Reports reports = new Reports();
			reports.setMes(i);
			reports.setValor(rs.getDouble(1));

			list.add(reports);
		}

		return list;

	}

	// Gerando lista de anos desde a criação do usuário até o ano atual
	public ArrayList<Integer> getListYears(Connection conexao, String id_user) throws SQLException {
		ArrayList<Integer> list = new ArrayList();

		int current_year = LocalDate.now().getYear();

		int registration_year = services.getRegistrationYear(conexao, id_user);

		for (int i = registration_year; i <= current_year; i++)
			list.add(i);

		return list;
	}

	public String[] generateArray(ArrayList<Integer> list) {
		String[] array = new String[list.size()];

		for (int i = 0; i < list.size(); i++)
			array[i] = list.get(i).toString();

		return array;
	}

	public String getNameMonth(int month_number) {

		switch (month_number) {

			case 1:
				return "JAN";

			case 2:
				return "FEV";

			case 3:
				return "MAR";

			case 4:
				return "ABR";

			case 5:
				return "MAI";

			case 6:
				return "JUN";

			case 7:
				return "JUL";

			case 8:
				return "AGO";

			case 9:
				return "SET";

			case 10:
				return "OUT";

			case 11:
				return "NOV";

			case 12:
				return "DEZ";

			default:
				throw new IllegalArgumentException("Unexpected value: " + month_number);
		}
	}

}
