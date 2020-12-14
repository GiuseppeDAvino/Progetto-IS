package model.notifica;

public class NotificaBean {
	private int id;
	
	public NotificaBean(String descrizione, String tipo) {
		this.descrizione = descrizione;
		this.tipo = tipo;
	}


	private String descrizione;
	private String tipo;
	
	public NotificaBean() {
		this.id = 0;
		this.descrizione = "";
		this.tipo = "";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	@Override
	public String toString() {
		return "Notifica [id=" + id + ", descrizione=" + descrizione + ", tipo=" + tipo + "]";
	}
	
}
