package model.recensione;

public class RecensioneBean {
	private int id;
	private String tipo;
	private String descrizione;
	
	public RecensioneBean() {
		this.id = 0;
		this.tipo = "";
		this.descrizione = "";
	}
	
	public int getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return "Recensione [id=" + id + ", tipo=" + tipo + ", descrizione=" + descrizione + "]";
	}
}
