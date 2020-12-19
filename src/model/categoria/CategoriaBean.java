package model.categoria;

/**
 * @category Classe che rappresenta una categoria
 * 
 * @param nome         il nome della categoria ad esempio "PC fascia alta"
 * @param tipoGenerico il tipo generico della categoria, ad esempio "PC"
 * @param descrizione  una descrizione della categoria
 * @param prezzo       il prezzo della postazione con questa categoria
 * 
 */
public class CategoriaBean {
	private String nome;
	private String tipoGenerico;
	private String descrizione;
	private float prezzo;
	private String immagine;

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

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

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public boolean isEmpty() {
		if (nome.equals(""))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Categoria [nome=" + nome + ", tipoGenirico=" + tipoGenerico + ", descrizione=" + descrizione
				+ ", prezzo=" + prezzo +", img="+immagine+ "]";
	}

}
