package model.notifica;

public class NotificaBean {
	private int id;
	private String descrizione;
	private boolean isRead;
	private String tipo;
	
	public NotificaBean() {
		this.id = 0;
		this.descrizione = "";
		this.isRead = false;
		this.tipo = "";
	}
	
	public int getId() {
		return id;
	}

	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Notifica [id=" + id + ", descrizione=" + descrizione + ", isRead=" + isRead + ", tipo=" + tipo + "]";
	}
	
}
