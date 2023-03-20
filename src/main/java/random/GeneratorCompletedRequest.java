package random;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import br.ucsal.sage.request.model.Request;

public class GeneratorCompletedRequest {
	
	RewriteMethods request = new RewriteMethods(); 
	
	
	
	public void coletaRealizada (Connection conexao, int quantidade) throws SQLException {
		ArrayList <Request> chamados = getChamados(conexao);
		Random random = new Random();
		if(!chamados.isEmpty()) {
			if(chamados.size() >= quantidade) {
				
				for(int i = 0; i < quantidade; ) {
					chamados = getChamados(conexao);
					
					Request r = chamados.get(random.nextInt(chamados.size()));
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					 
					LocalDate data2 = LocalDate.parse(r.getPrevisao_coleta());
					 
					String data3 = data2.format(formatter);

				        
				
				if(request.completedRequest(conexao, r.getId_chamado(), r.getCodigo_verificacao(), data3, random.nextDouble(100)))
					i++;
				}
				System.out.println("Coletas completadas");
				
			}else
				System.out.println("A quantidade de chamados cadastrados é menor do que a quantidade informada");
			
		}else
			System.out.println("Nenhum chamado cadastrado");
	}
	
	
	
public ArrayList <Request> getChamados(Connection conexao) throws SQLException{
		
		ArrayList <Request> chamados = new ArrayList <Request>();
		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT id_chamado, previsao_coleta, cod_verificacao\r\n" + "	FROM public.\"CHAMADO\" WHERE status_chamado = 2";
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			Request r = new Request();
			
			r.setId_chamado(rs.getString(1));
			r.setPrevisao_coleta(rs.getString(2));
			r.setCodigo_verificacao(rs.getString(3));
			
			chamados.add(r);
		}
			
		
		return chamados;
		
	}

}
