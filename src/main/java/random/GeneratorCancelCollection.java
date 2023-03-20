package random;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import br.ucsal.sage.request.controller.RequestController;

public class GeneratorCancelCollection {
	
RequestController request = new RequestController(); 
	
	
	public void cancelarColeta (Connection conexao, int quantidade) throws SQLException {
		ArrayList <String> chamados = getChamados(conexao);

		Random random = new Random();
		if(!chamados.isEmpty()) {
			if(chamados.size() >= quantidade) {
				
				for(int i = 0; i < quantidade; ) {
					chamados = getChamados(conexao);
					String id_chamado = chamados.get(random.nextInt(chamados.size()));
				
				if(request.cancelRequest(conexao, id_chamado))
					i++;
				}
				System.out.println("Coletas canceladas");
				
			}else
				System.out.println("A quantidade de chamados cadastrados é menor do que a quantidade informada");
			
		}else
			System.out.println("Nenhum chamado cadastrado");
		
		
	}
	
	
	
public ArrayList <String> getChamados(Connection conexao) throws SQLException{
		
		ArrayList <String> chamados = new ArrayList <String>();
		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT id_chamado\r\n" + "	FROM public.\"CHAMADO\" WHERE status_chamado = 2";
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) 
			chamados.add(rs.getString(1));
		
		return chamados;
		
	}

}
