package model.servizio;

import model.utente.UtenteDAO;

public final class Validatore {
	private static UtenteDAO utenteDAO = new UtenteDAO();

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
}
