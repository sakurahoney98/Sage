package br.ucsal.sage.collector.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;

import br.ucsal.sage.collector.service.CollectorService;
import br.ucsal.sage.request.model.Request;
import br.ucsal.sage.request.services.RequestServices;
@Controller
public class CollectorController {

	RequestServices services = new RequestServices();
	CollectorService cservices = new CollectorService();

	//Gerando ArrayList com os dados de todos os chamados abertos
	public ArrayList<Request> getAllOpenRequests(Connection conexao) throws SQLException {
		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT id_chamado, id_escola, id_coletor, data_abertura, "
				+ "data_coleta, previsao_coleta, status_chamado, tipo_lixo, "
				+ "cod_verificacao, hora_abertura, sobre_chamado, descricao_status, user_escola.nome_usuario\r\n"
				+ "	FROM public.\"CHAMADO\" as chamado "
				+ "INNER JOIN public.\"STATUS\" ON \"STATUS\".id_status = chamado.status_chamado "
				+ "INNER JOIN public.\"USUARIO\" as user_escola ON user_escola.id_usuario = chamado.id_escola\r\n"
				+ " WHERE status_chamado = 1 ORDER BY data_abertura DESC, id_chamado DESC";

		ResultSet rs = st.executeQuery(sql);

		return services.generateRequestList(rs);

	}

	//Transformando um ArrayList em matriz
	public Object[][] generateObjectArray(ArrayList<Request> array) {

		return cservices.buildObjectArray(array);
	}

}
