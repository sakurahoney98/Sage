package random;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class GeneratorRequest {
	
	
	RewriteMethods request = new RewriteMethods(); 
	
	public void gerarChamado(Connection conexao, int quantidade) throws SQLException {
		
		Random random = new Random();
		ArrayList <String> escolas = getEscolas(conexao);
		
		
		if(!escolas.isEmpty()) {
			
			for (int i = 0; i < quantidade; ) {
				escolas = getEscolas(conexao);
				String id_escola = escolas.get(random.nextInt(escolas.size()));
				
				String dia = random.nextInt(28) + 1 +"";
				if(dia.length() < 2)
					dia = "0" + dia;
				String mes = random.nextInt(12) + 1 +"";
				if(mes.length() < 2)
					mes = "0" + mes;
				String ano = random.nextInt(23) + 2000 +"";
				String data = dia + "/" + mes + "/" + ano;
				
				if(request.createRequest(conexao, id_escola, "Eletrônico", "Descrição do chamado. @ Segunda a sexta 08h as 18h", data))
					i++;
				
				
			}
			
			System.out.println("Chamados gerados");
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
