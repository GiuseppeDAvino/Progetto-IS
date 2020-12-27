package inserimentiUtente;
import java.sql.SQLException;

import model.utente.UtenteBean;
import model.utente.UtenteBean.Ruolo;
import model.utente.UtenteDAO;

public class ProvaInserimentoTitolare {

	public static void main(String[] args) {
	
		UtenteDAO utenteDAO=new UtenteDAO();
		UtenteBean titolare=new UtenteBean("titolare@titolare.com", "Titolare", "cognome", "user", Ruolo.titolare, true,"12364","titolare");
		UtenteBean cliente=new UtenteBean("cliente@cliente.com", "Cliente", "cognome", "user", Ruolo.cliente, true,"12364","cliente");
		UtenteBean gestore=new UtenteBean("gestore@gestore.com", "Gestore", "cognome", "user", Ruolo.gestore, true,"12364","gestore");
		utenteDAO.doSave(titolare);
		utenteDAO.doSave(cliente);
		utenteDAO.doSave(gestore);
	}
	
}
