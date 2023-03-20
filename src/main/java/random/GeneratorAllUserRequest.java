package random;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import br.ucsal.sage.request.controller.RequestController;
import br.ucsal.sage.request.model.Request;
import br.ucsal.sage.request.services.RequestServices;

public class GeneratorAllUserRequest {
	RequestController request = new RequestController();
	RequestServices utilities = new RequestServices();
	
	public void retornarTodosChamados(Connection conexao, int quantidade) throws SQLException {
		ArrayList <String> usuarios = getusuarios(conexao);
		
		Random random = new Random();
		if(!usuarios.isEmpty()) {
			if(usuarios.size() >= quantidade) {
				
				for(int i  = 0; i < quantidade; i++) {
					int posicao = random.nextInt(usuarios.size());
					
					ArrayList <Request> lista = request.findRequestByUser(conexao, usuarios.get(posicao));
					
					System.out.println("Usuário: " + usuarios.get(posicao));
					if(utilities.getUserType(conexao, usuarios.get(posicao)) == 1) {
						for(Request r : lista) 
							System.out.println(r.getId_chamado() + " | " + r.getNome_coletor() + " | " + r.getTipo_lixo() + " | " + r.getStatus_chamado() + " | " + r.getData_coleta());
						
					}else {
						for(Request r : lista) 
							System.out.println(r.getId_chamado() + " | " + r.getNome_escola() + " | " + r.getTipo_lixo() + " | " + r.getStatus_chamado() + " | " + r.getData_coleta());
					}
				
					
					System.out.println();
					
					usuarios.remove(posicao);
				}
				
			}else
				System.out.println("Quantidade de usuarios cadastrados é menor do que a quantidade informada");
		}else
			System.out.println("Sem usuários cadastrados");
		
	}
	
	
	
	public ArrayList <String> getusuarios(Connection conexao) throws SQLException{
		ArrayList <String> usuarios = new ArrayList <String>();
		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT id_usuario\r\n" + "	FROM public.\"USUARIO\" ";
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) 
			usuarios.add(rs.getString(1));
		
		return usuarios;
			
		}

}
