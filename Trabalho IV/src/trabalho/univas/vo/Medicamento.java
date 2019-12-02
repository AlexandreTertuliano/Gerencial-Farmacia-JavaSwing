package trabalho.univas.vo;

public class Medicamento {
	
	private String nameMedicamento;
	private String lote;
	private String dataValidade;
	private String quantidade;
	private String valor;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameMedicamento() {
		return nameMedicamento;
	}
	public void setNameMedicamento(String nameMedicamento) {
		this.nameMedicamento = nameMedicamento;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}
