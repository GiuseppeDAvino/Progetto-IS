package model.prenotazione;

import java.sql.Date;

public class PrenotazioneBean {
	private int id;
	private Date data;
	private String fasciaOraria;
	private String qr;
	private String utenteEmail;
	private int postazioneId;
	private float prezzo;

	public PrenotazioneBean() {
		super();
	}

	public PrenotazioneBean(int id, Date data, String fasciaOraria, String qr, String utenteEmail, int postazioneId,
			float prezzo) {
		super();
		this.id = id;
		this.data = data;
		this.fasciaOraria = fasciaOraria;
		this.qr = qr;
		this.utenteEmail = utenteEmail;
		this.postazioneId = postazioneId;
		this.prezzo = prezzo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getFasciaOraria() {
		return fasciaOraria;
	}

	public void setFasciaOraria(String fasciaOraria) {
		this.fasciaOraria = fasciaOraria;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public String getUtenteEmail() {
		return utenteEmail;
	}

	public void setUtenteEmail(String utenteEmail) {
		this.utenteEmail = utenteEmail;
	}

	public int getPostazioneId() {
		return postazioneId;
	}

	public void setPostazioneId(int postazioneId) {
		this.postazioneId = postazioneId;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
