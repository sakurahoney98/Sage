package br.ucsal.sage.requester.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;

import br.ucsal.sage.requester.services.RequesterServices;
import br.ucsal.sage.user.model.User;
@Controller
public class RequesterController {

	RequesterServices services = new RequesterServices();
	
	
	//Pegando a posição da escola no ranking de reciclagem
	public int getRankinPosition(Connection conexao, String id_user) throws SQLException {

		Statement st;
		st = conexao.createStatement();

		String sql = "SELECT id_usuario FROM public.\"USUARIO\" WHERE tipo_usuario = 1 ORDER BY quantidade_total DESC, id_usuario ";

		ResultSet rs = st.executeQuery(sql);

		return services.getPosition(rs, id_user);
	}

	
	
	//Gerando a lista de escolas que mais reciclam
	public ArrayList<User> getRequesterRanking(Connection conexao) throws SQLException {

		Statement st;
		st = conexao.createStatement();

		String sql = "SELECT nome_usuario, apelido_usuario, quantidade_total FROM public.\"USUARIO\" WHERE tipo_usuario = 1 ORDER BY quantidade_total DESC, id_usuario LIMIT 10";

		ResultSet rs = st.executeQuery(sql);

		return services.getRanking(rs);
	}
	
	public Object [][] generateObjectArray (ArrayList<User> array){
		
		
		return services.buildObjectArray(array);
	}

}
