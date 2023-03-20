package random;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import br.ucsal.sage.reports.controller.ReportsController;
import br.ucsal.sage.reports.model.Reports;

public class GeneratorReportsByMonth {
	
	
	
	
	ReportsController reports = new ReportsController();
	
	
	public void gerarRelatórioPorMes (Connection conexao, int quantidade) throws SQLException {
		ArrayList <String> usuarios = getusuarios(conexao);
		Random random = new Random ();
		
		
		if(!usuarios.isEmpty()) {
			if(usuarios.size() >= quantidade) {
				for(int i = 0; i < quantidade; i++) {
					int posicao = random.nextInt(usuarios.size());
					
					int ano = random.nextInt(23) + 2000;
					
					System.out.println("Usuário: " + usuarios.get(posicao) + " | Ano: " + ano);
					
					for(Reports r : reports.getReportsByMonth(conexao, usuarios.get(posicao), ano)) {
						
						String formatar_double = String.valueOf(r.getValor());
						
						formatar_double = formatar_double.substring(0, formatar_double.indexOf(".") + 2);
						
						System.out.println("Mês: " + r.getMes() + " | Quantidade: " + formatar_double + "kg");
					}
						
					
					usuarios.remove(posicao);
					System.out.println();
				}
				
				
				
			}else
				System.out.println("A quantidade de usuários cadastrados é menor do que a quantidade informada");
		}else
			System.out.println("Nenhum usuário cadastrado");
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
