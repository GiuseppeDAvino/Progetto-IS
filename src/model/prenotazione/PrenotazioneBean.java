package model.prenotazione;

public class PrenotazioneBean {
	private int prenotazione;
	private String data;
	private String fasciaOraria;
	private String img;

	public PrenotazioneBean() {
		this.prenotazione = 0;
		this.data = "";
		this.fasciaOraria = "";
		this.img = "";
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getPrenotazione() {
		return prenotazione;
	}
	
	public void setPrenotazione(int prenotazione) {
		this.prenotazione = prenotazione;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getFasciaOraria() {
		return fasciaOraria;
	}
	public void setFasciaOraria(String fasciaOraria) {
		this.fasciaOraria = fasciaOraria;
	}

	@Override
	public String toString() {
		return "PrenotazioneBean [prenotazione=" + prenotazione + ", data=" + data + ", fasciaOraria=" + fasciaOraria
				+ ", img=" + img + "]";
	}

}
