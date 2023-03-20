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

public class GeneratorAssumeRequest {
	
	RewriteMethods request = new RewriteMethods();
	
	public void assumirColeta(Connection conexao, int quantidade) throws SQLException {
		
		
		Random random = new Random();
		ArrayList <String> coletoras = getColetoras(conexao);
		ArrayList <Request> chamados_abertos = getChamadosAbertos(conexao);
		
		if(!coletoras.isEmpty()) {
			if(chamados_abertos.size() >= quantidade) {
				for(int i = 0; i < quantidade;) {
					coletoras = getColetoras(conexao);
					chamados_abertos = getChamadosAbertos(conexao);
					
					int posicao = random.nextInt(chamados_abertos.size());
					
					String id_coletor = coletoras.get(random.nextInt(coletoras.size()));
					String id_chamado = chamados_abertos.get(posicao).getId_chamado();
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					
					LocalDate previsao_data = LocalDate.parse(chamados_abertos.get(posicao).getData_abertura());
					
					previsao_data = previsao_data.plusDays(5);
					
					String data = previsao_data.format(formatter);
					
					if(request.takeRequest(conexao, id_chamado, id_coletor, data)) 
						i++;
					
					
					
				}
				
				System.out.println("Chamados assumidos");
			}else
				System.out.println("A quantidade informada é maior do que a quantidade de chamados abertos");
			
		}else
			System.out.println("Nenhuma coletora cadastrada");
		
		
	}
	
	
	
	public ArrayList <String> getColetoras(Connection conexao) throws SQLException{
		ArrayList <String> coletoras = new ArrayList <String>();
		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT id_usuario\r\n" + "	FROM public.\"USUARIO\" WHERE tipo_usuario = 2;";
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) 
			coletoras.add(rs.getString(1));
		
		return coletoras;
			
		}
	
	public ArrayList <Request> getChamadosAbertos(Connection conexao) throws SQLException{
		ArrayList <Request> chamados_abertos = new ArrayList <Request>();
		Statement st;
		st = conexao.createStatement();
		String sql = "SELECT id_chamado, data_abertura\r\n" + "	FROM public.\"CHAMADO\" WHERE status_chamado = 1;";
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			Request r = new Request();
			r.setId_chamado(rs.getString(1));
			r.setData_abertura(rs.getString(2));
			chamados_abertos.add(r);
		}
		return chamados_abertos;
			
		}

}
