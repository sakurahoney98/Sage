package br.ucsal.sage.collector.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import br.ucsal.sage.request.model.Request;

public class CollectorService {

	// Transformando ArrayList em matriz
	public Object[][] buildObjectArray(ArrayList<Request> array) {

		Object[][] ob = new Object[array.size()][4];
		if (array.size() > 0) {
			int i = 0;
			for (Request r : array) {
				ob[i][0] = r.getId_chamado();
				ob[i][1] = r.getNome_escola();
				ob[i][2] = r.getTipo_lixo();
				LocalDate data = LocalDate.parse(r.getData_abertura());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				ob[i][3] = formatter.format(data);
				i++;
			}
		}
		return ob;
	}

}
