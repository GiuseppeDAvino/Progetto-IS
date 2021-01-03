package control.gestionePrenotazione;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.notifica.NotificaBean;
import model.notifica.NotificaDAO;
import model.prenotazione.PrenotazioneBean;
import model.prenotazione.PrenotazioneDAO;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;

@WebServlet(urlPatterns = {"/EliminaPrenotazione","/titolare/EliminaPrenotazione"})
public class EliminaPrenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
    private NotificaDAO notificaDAO = new NotificaDAO();
    private UtenteDAO utenteDAO = new UtenteDAO();
    public EliminaPrenotazione() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		PrenotazioneBean prenotazione = prenotazioneDAO.doRetrieveByKey(id);
		ArrayList<UtenteBean> utenti = new ArrayList<UtenteBean>();
		if(prenotazione.getQr().equals("")) {
			NotificaBean notifica = new NotificaBean();
			notifica.setDescrizione("LA PRENOTAZIONE DA LEI EFFETTUATA E' STATA ELIMINATA CI SCUSIAMO PER IL DISAGIO");
			notifica.setTipo("ELIMINA PRENOTAZIONE");
			utenti.add(utenteDAO.doRetrieveByKey(prenotazione.getUtenteEmail()));
			notificaDAO.doSaveNotificaUtente(notifica, utenti);
		}
		prenotazioneDAO.doDelete(id);
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
