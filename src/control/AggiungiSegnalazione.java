package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.notifica.NotificaBean;
import model.notifica.NotificaDAO;
import model.segnalazione.SegnalazioneBean;
import model.segnalazione.SegnalazioneDAO;
import model.utente.UtenteBean;

@WebServlet(urlPatterns = {"/AggiungiSegnalazione","/cliente/AggiungiSegnalazione"})
public class AggiungiSegnalazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NotificaDAO notificaDAO = new NotificaDAO(); 
	private SegnalazioneDAO segnalazioneDAO= new SegnalazioneDAO();
	
    public AggiungiSegnalazione() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SegnalazioneBean segnalazione = new SegnalazioneBean();
		
		request.getSession().getAttribute("utente");
		
		UtenteBean utente = new UtenteBean();
		
		segnalazione.setUtenteEmail(utente.getEmail());
		segnalazione.setDescrizione(request.getParameter("descrizione"));
		segnalazione.setTipo(request.getParameter("tipo"));
		
		segnalazioneDAO.doSave(segnalazione);
		
		NotificaBean notifica = new NotificaBean();
		notifica.setDescrizione("Nuova Segnalazione");
		notifica.setTipo("Segnalazione");
		
		notificaDAO.doSave(notifica);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
