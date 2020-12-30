package model.servizio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.categoria.CategoriaDAO;
import model.utente.UtenteDAO;

public final class Validatore {
	private static UtenteDAO utenteDAO = new UtenteDAO();
	private static CategoriaDAO categoriaDAO = new CategoriaDAO();
	
	/**
	 * @category controlla il nome/cognome inserito
	 * 
	 * @param stringa stringa da controllare
	 */
	public static Boolean validaNomeCognome(String stringa) {
		String regex = "[a-zA-Z ‘אטלעש]{3,30}";
		return stringa.matches(regex);
	}

	/**
	 * @category controlla l'username inserito
	 * 
	 * @param username username da controllare
	 */
	public static Boolean validaUsername(String username) {
		String regex = "[a-zA-Z  _0-9]{3,30}";
		return username.matches(regex);
	}

	/**
	 * @category controlla l' email inserito
	 * 
	 * @param email email da controllare
	 */
	public static Boolean validaEmail(String email) {
		String regex = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
		return email.matches(regex);
	}

	/**
	 * @category controlla la passwordinserito
	 * 
	 * @param password password da controllare
	 */
	public static Boolean validaPassword(String password) {
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\" + "d)(?=.*[@$!%*?&])[A-Za-z\\" + "d@$!%*?&]{8,}$";
		return password.matches(regex);
	}

	/**
	 * @category Controlla se l'email ט presente nel db
	 * @param email email da controllare
	 * @return <code>true</code> se l'email non ט presente nel db<br>
	 * 			<code>false</code> altrimenti
	 * */
	public static boolean isEmailValid(String email) {
		if (utenteDAO.doRetrieveByKey(email).getEmail().equals("")) {
			return true;
		}
		return false;

	}

	public static boolean isUsernameValid(String username) {
		if (utenteDAO.esisteUsername(username))
			return false;
		return true;

	}

	public static boolean isPasswordValid(String password, String confPassword) {

		if (password.equals(confPassword))
			return true;
		else
			return false;
	}
	
	public static boolean validaData(String data) {
		String regex = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
		return data.matches(regex);
	}
	
	public static boolean validaPrezzo(String prezzo) {
		String regex = "[0-9]{1,2}\\.?[0-9]{0,2}";
		return prezzo.matches(regex);
	}
	
	public static boolean validaQuantita(String quantita) {
		String regex = "^([0-9]{0,1}([1-9][0-9]){0,2})$";
		return quantita.matches(regex);
	}
	
	public static boolean isFasciaOrariaValid(String fasciaOraria) {
		if(fasciaOraria.equals("8/10") || fasciaOraria.equals("10/12") || fasciaOraria.equals("12/14") || fasciaOraria.equals("14/16") || fasciaOraria.equals("16/18") || fasciaOraria.equals("18/20") || fasciaOraria.equals("20/22")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isTipoValid(String tipoGenerico) {
		if(categoriaDAO.doRetrieveByTipoGenerico(tipoGenerico).getTipoGenerico().equals("")) 
			return false;
		else
			return true;
	}
	
	public static boolean isDataAntecedente(String data) {
		try {
			Date dataInserita = new SimpleDateFormat("yyyy-MM-dd").parse(data);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date date = new Date();
		    String str = dateFormat.format(date);
		    Date dataCorrente = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		    
		    if(dataInserita.before(dataCorrente)) {
		    	return true;
		    }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	public static boolean isNomeCategoriaValid(String nomeCategoria) {
		if(categoriaDAO.doRetrieveByKey(nomeCategoria).getNome().equals("")) 
			return false;
		return true;
			
			
	}
}
