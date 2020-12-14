package model.prenotazione;

import java.sql.Date;

public class PrenotazioneBean {
	private int id;
	private Date data;
	private String fasciaOraria;
	private String qr;
	private String utenteEmail;
	private int postazioneId;
	
	public PrenotazioneBean() {
		this.id = 0;
		this.data = null;
		this.fasciaOraria = "";
		this.qr = "";
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getData() {
		return data;
	}
	public void setData(Date date) {
		this.data = date;
	}
	public String getFasciaOraria() {
		return fasciaOraria;
	}
	public void setFasciaOraria(String fasciaOraria) {
		this.fasciaOraria = fasciaOraria;
	}

	@Override
	public String toString() {
		return "PrenotazioneBean [prenotazione=" + id + ", data=" + data + ", fasciaOraria=" + fasciaOraria
				+ ", img=" + qr + "]";
	}

}
