package model.utente;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class UtenteBean {
	private String email;
	private String nome;
	private String cognome;
	private String username;
	private Ruolo ruolo;
	public enum Ruolo{cliente, titolare, gestore};
	private boolean stato;
	private String codiceVerifica;
	private byte[] password;
	private String img;
	
	public UtenteBean() {
		this.email = "";
		this.nome = "";
		this.cognome = "";
		this.username = "";
		this.stato = false;
		this.codiceVerifica = "";
		this.password = new byte[32];
		this.email = "";
	}
	
	
	
	public UtenteBean(String email, String nome, String cognome, String username, Ruolo ruolo, boolean stato,
			String codiceVerifica, byte[] password, String img) {
		super();
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.ruolo = ruolo;
		this.stato = stato;
		this.codiceVerifica = codiceVerifica;
		this.password = password;
		this.img = img;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCognome() {
		return cognome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public Ruolo getRuolo() {
		return ruolo;
	}
	
	public byte[] getPassword() {
		return password;
	}



	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}



	public boolean isStato() {
		return stato;
	}



	public void setStato(boolean stato) {
		this.stato = stato;
	}



	public String getCodiceVerifica() {
		return codiceVerifica;
	}



	public void setCodiceVerifica(String codiceVerifica) {
		this.codiceVerifica = codiceVerifica;
	}



	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}

	


	/**
	 * Questo metodo è stato creato per codificare una password passata come plain-text in una codificata
	 * utilizzando l'algoritmo SHA-256
	 * @param passwordToEncode la password in chiaro
	 */
	public void setPassword(String passwordToEncode) {
		
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
			byte arr[]=md.digest(passwordToEncode.getBytes());
			this.password=arr;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public String toString() {
		return "UtenteBean [email=" + email + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username
				+ ", ruolo=" + ruolo + ", stato=" + stato + ", codiceVerifica=" + codiceVerifica + ", password="
				+ Arrays.toString(password) + ", img=" + img + "]";
	}
	
	
}
