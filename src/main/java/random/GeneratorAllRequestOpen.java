package random;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import br.ucsal.sage.collector.controller.CollectorController;
import br.ucsal.sage.request.model.Request;

public class GeneratorAllRequestOpen {
	
	CollectorController colector = new CollectorController();
	
	public void retornarTodosChamadosAbertos(Connection conexao) throws SQLException {
		
		
		ArrayList <Request> lista  = colector.getAllOpenRequests(conexao);
		
		
		
		for(Request r : lista) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			 
			LocalDate data2 = LocalDate.parse(r.getData_abertura());
			
			System.out.println(r.getId_chamado() + " | " + r.getNome_escola() + " | " + r.getTipo_lixo() + " | " + data2.format(formatter) + " " + r.getHora_abertura());
		}
		
		
	}

	
	
	


}
