package model.categoria;

public class CategoriaBean {
	private String nome;
	private String tipoGenerico;
	private String descrizione;
	private double prezzo;
	
	public CategoriaBean() {
		this.nome = "";
		this.tipoGenerico = "";
		this.descrizione = "";
		this.prezzo = 0;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipoGenerico() {
		return tipoGenerico;
	}
	public void setTipoGenerico(String tipoGenerico) {
		this.tipoGenerico = tipoGenerico;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	@Override
	public String toString() {
		return "Categoria [nome=" + nome + ", tipoGenirico=" + tipoGenerico + ", descrizione=" + descrizione + ", prezzo=" + prezzo
				+ "]";
	}
	
}
