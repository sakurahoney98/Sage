package br.ucsal.sage.request.model;

public class Request {

	private String id_chamado;
	private String id_escola;
	private String id_coletor;
	private String nome_escola;
	private String nome_coletor;
	private String data_abertura;
	private String data_coleta;
	private String hora_abertura;
	private String previsao_coleta;
	private String status_chamado;
	private String tipo_lixo;
	private String codigo_verificacao;
	private String sobre_chamado;
	private String disponibilidade;
	private int status_int;

	public int getStatus_int() {
		return status_int;
	}

	public void setStatus_int(int status_int) {
		this.status_int = status_int;
	}

	public String getId_chamado() {
		return id_chamado;
	}

	public void setId_chamado(String id_chamado) {
		this.id_chamado = id_chamado;
	}

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

	public String getNome_escola() {
		return nome_escola;
	}

	public void setNome_escola(String nome_escola) {
		this.nome_escola = nome_escola;
	}

	public String getNome_coletor() {
		return nome_coletor;
	}

	public void setNome_coletor(String nome_coletor) {
		this.nome_coletor = nome_coletor;
	}

	public String getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(String data_abertura) {
		this.data_abertura = data_abertura;
	}

	public String getData_coleta() {
		return data_coleta;
	}

	public void setData_coleta(String data_coleta) {
		this.data_coleta = data_coleta;
	}

	public String getHora_abertura() {
		return hora_abertura;
	}

	public void setHora_abertura(String hora_abertura) {
		this.hora_abertura = hora_abertura;
	}

	public String getPrevisao_coleta() {
		return previsao_coleta;
	}

	public void setPrevisao_coleta(String previsao_coleta) {
		this.previsao_coleta = previsao_coleta;
	}

	public String getStatus_chamado() {
		return status_chamado;
	}

	public void setStatus_chamado(String status_chamado) {
		this.status_chamado = status_chamado;
	}

	public String getTipo_lixo() {
		return tipo_lixo;
	}

	public void setTipo_lixo(String tipo_lixo) {
		this.tipo_lixo = tipo_lixo;
	}

	public String getCodigo_verificacao() {
		return codigo_verificacao;
	}

	public void setCodigo_verificacao(String codigo_verificacao) {
		this.codigo_verificacao = codigo_verificacao;
	}

	public String getSobre_chamado() {
		return sobre_chamado;
	}

	public void setSobre_chamado(String sobre_chamado) {
		this.sobre_chamado = sobre_chamado;
	}

	public String getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

}
