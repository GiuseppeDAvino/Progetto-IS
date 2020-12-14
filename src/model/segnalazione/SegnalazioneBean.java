package model.segnalazione;

public class SegnalazioneBean {
	private int id;
	private String descrizione;
	private int valutazione;
	private boolean verificato;
	
	public SegnalazioneBean() {
		this.id = 0;
		this.descrizione = "";
		this.valutazione = 0;
		this.verificato = false;
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
	public int getValutazione() {
		return valutazione;
	}
	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}
	public boolean isVerificato() {
		return verificato;
	}
	public void setVerificato(boolean verificato) {
		this.verificato = verificato;
	}
	
	@Override
	public String toString() {
		return "Segnalazione [id=" + id + ", descrizione=" + descrizione + ", valutazione=" + valutazione
				+ ", verificato=" + verificato + "]";
	}
}
