package br.ucsal.sage.requester.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ucsal.sage.user.model.User;

public class RequesterServices {
	
	
	// Verificando a posição da escola no ranking de reciclagem
	public int getPosition(ResultSet rs, String id_user) throws SQLException {

		rs.next();

		int pos = 1;

		while (!rs.getString(1).equals(id_user)) {
			pos++;
			rs.next();
		}

		return pos;
	}

	
	//Gerando lista das 10 escolas que mais reciclam
	public ArrayList<User> getRanking(ResultSet rs) throws SQLException {

		ArrayList<User> list = new ArrayList<User>();

		for (int i = 0; i < 10 && rs.next(); i++) {
			User user = new User();

			user.setApelido(rs.getString(2));
			user.setNome(rs.getString(1));
			user.setQuantidade_reciclada(rs.getDouble(3));

			list.add(user);
		}

		return list;

	}
	
	
	public Object [][] buildObjectArray (ArrayList<User> array){
		Object [][] ob = new Object [][]{
            {null, null},
            {null, null},
            {null, null},
            {null, null},
             {null, null},
            {null, null},
            {null, null},
            {null, null},
             {null, null},
            {null, null}
        } ; 
        int i = 0;
        for (User u : array) {
        	ob [i][0] = u.getNome();
        	String formatar_double = String.valueOf(u.getQuantidade_reciclada());
			
			formatar_double = formatar_double.substring(0, formatar_double.indexOf(".") + 2);
        	ob [i][1] = String.valueOf(formatar_double);
        	i++;
        }
		
		return ob;
	}

}
