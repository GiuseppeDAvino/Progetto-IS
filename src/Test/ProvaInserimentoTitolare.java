package Test;
import java.sql.SQLException;

import model.utente.UtenteBean;
import model.utente.UtenteBean.Ruolo;
import model.utente.UtenteDAO;

public class ProvaInserimentoTitolare {

	public static void main(String[] args) {
		UtenteBean utente=new UtenteBean("titolare@titolare.com", "Titolare", "cognome", "user", Ruolo.titolare, true,"12364","titolare");
		UtenteDAO utenteDAO=new UtenteDAO();
		try {
			utenteDAO.doSave(utente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
