package random;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import br.ucsal.sage.requester.controller.RequesterController;

public class GeneratorPositionRanking {
	
	RequesterController requester = new RequesterController();
	public void pegarPosicaoRaking (Connection conexao, int quantidade) throws SQLException {
		ArrayList <String> escolas = getEscolas(conexao);
		Random random = new Random();
		
		if(!escolas.isEmpty()) {
			if(escolas.size() >= quantidade) {
			for (int i = 0; i < quantidade; i++) {
				
				int posicao = random.nextInt(escolas.size());
				
				String id_user = escolas.get(posicao);
				
				System.out.println("Usuário: " + id_user + " | Posição: " + requester.getRankinPosition(conexao, id_user)); 
				
				escolas.remove(posicao);
				
				
			}
			
			
			}else
				System.out.println("A quantidade de escolas cadastradas é menor do que a quantidade informada");
		}else
			System.out.println("Não possui Escolas cadastradas");
		
		
	}
	
	
public ArrayList <String> getEscolas(Connection conexao) throws SQLException{
		
		ArrayList <String> escolas = new ArrayList <String>();
		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT id_usuario\r\n" + "	FROM public.\"USUARIO\" WHERE tipo_usuario = 1;";
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) 
			escolas.add(rs.getString(1));
		
		return escolas;
		
	}

}
