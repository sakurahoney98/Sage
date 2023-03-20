package random;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ucsal.sage.requester.controller.RequesterController;
import br.ucsal.sage.user.model.User;

public class GeneratorTop10 {
	
	RequesterController requester = new RequesterController();
	
	public void retornarTop10Escolas(Connection conexao) throws SQLException {
		
		ArrayList <User> lista = requester.getRequesterRanking(conexao);
		int i = 1;
		for(User u : lista) {
			String formatar_double = String.valueOf(u.getQuantidade_reciclada());
			
			formatar_double = formatar_double.substring(0, formatar_double.indexOf(".") + 2);
			
			System.out.println(i + "º - " + u.getNome() + " | " +  formatar_double + "kg");
			i++;
		}
		
		
		
	}

}
