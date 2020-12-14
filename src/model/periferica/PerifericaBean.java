package model.periferica;

public class PerifericaBean {
	private String tipo;
	private String nome;
	private int quantita;
	private float prezzo;
	
	public PerifericaBean() {
		this.tipo = "";
		this.nome = "";
		this.quantita = 0;
		this.prezzo = 0;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	@Override
	public String toString() {
		return "Periferica [tipo=" + tipo + ", nome=" + nome + ", quantita=" + quantita + ", prezzo=" + prezzo + "]";
	}
	
}
