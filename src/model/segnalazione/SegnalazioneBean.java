package model.segnalazione;

public class SegnalazioneBean {
	private int id;
	private String tipo;
	private String descrizione;
	private String utenteEmail;

	public SegnalazioneBean() {
		super();
	}

	public SegnalazioneBean(String tipo, String descrizione, String utenteEmail) {
		super();
		this.tipo = tipo;
		this.descrizione = descrizione;
		this.utenteEmail = utenteEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUtenteEmail() {
		return utenteEmail;
	}

	public void setUtenteEmail(String utenteEmail) {
		this.utenteEmail = utenteEmail;
	}

}
