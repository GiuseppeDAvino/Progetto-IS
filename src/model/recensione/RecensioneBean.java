package model.recensione;

public class RecensioneBean {
	private String descrizione;
	private int valutazione;
	private boolean verificata;
	private String utenteEmail;

	public RecensioneBean(String descrizione, int valutazione, boolean verificata, String utenteEmail) {
		this.descrizione = descrizione;
		if (valutazione > 5)
			this.valutazione = 5;
		else if (valutazione < 0)
			valutazione = 0;
		else
			this.valutazione = valutazione;
		this.verificata = verificata;
		this.utenteEmail = utenteEmail;
	}

	public RecensioneBean() {
		super();
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
		if (valutazione > 5)
			this.valutazione = 5;
		else if (valutazione < 0)
			valutazione = 0;
		else
			this.valutazione = valutazione;
	}

	public boolean isVerificata() {
		return verificata;
	}

	public void setVerificata(boolean verificata) {
		this.verificata = verificata;
	}

	public String getUtenteEmail() {
		return utenteEmail;
	}

	public void setUtenteEmail(String utenteEmail) {
		this.utenteEmail = utenteEmail;
	}

	@Override
	public String toString() {
		return "RecensioneBean [descrizione=" + descrizione + ", valutazione=" + valutazione + ", verificata="
				+ verificata + ", utenteEmail=" + utenteEmail + "]";
	}

}
