package model.postazione;

public class PostazioneBean {
	private int id;
	private boolean disponibile;
	
	public PostazioneBean() {
		this.id = 0;
		this.disponibile = true;
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
