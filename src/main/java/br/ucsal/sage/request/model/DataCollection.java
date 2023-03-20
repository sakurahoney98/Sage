package br.ucsal.sage.request.model;

public class DataCollection {

	private String id_escola;
	private String id_coletor;
	private String id_chamado;
	private String data_coleta;
	private double quantidade;

	public String getId_escola() {
		return id_escola;
	}

	public void setId_escola(String id_escola) {
		this.id_escola = id_escola;
	}

	public String getId_coletor() {
		return id_coletor;
	}

	public void setId_coletor(String id_coletor) {
		this.id_coletor = id_coletor;
	}

	public String getId_chamado() {
		return id_chamado;
	}

	public void setId_chamado(String id_chamado) {
		this.id_chamado = id_chamado;
	}

	public String getData_coleta() {
		return data_coleta;
	}

	public void setData_coleta(String data_coleta) {
		this.data_coleta = data_coleta;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

}
