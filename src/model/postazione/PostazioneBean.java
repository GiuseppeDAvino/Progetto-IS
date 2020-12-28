package model.postazione;

public class PostazioneBean {
	private int id;
	private boolean disponibile;
	private String categoria;

	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PostazioneBean() {
		this.id = 0;
		this.disponibile = true;
	}

	public PostazioneBean(String categoria) {
		super();
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public boolean isDisponibile() {
		return disponibile;
	}

	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}

	@Override
	public String toString() {
		return "Postazione [id=" + id + ", disponibile=" + disponibile + "]";
	}

}
