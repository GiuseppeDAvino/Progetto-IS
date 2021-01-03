package control.gestioneSegnalazione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		SegnalazioneBean segnalazione = new SegnalazioneBean();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		String email = utente.getEmail();
		String descrizione = request.getParameter("descrizione");
		String tipo = request.getParameter("tipo");
		
		if(tipo.length() == 0) {
			request.setAttribute("errorTest","L'aggiunta della segnalazione non va a buon fine poiché il campo tipo è vuoto");
			session.setAttribute("error-type", "tipo");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cliente/segnalazione.jsp"));
		}
		else {
			if(tipo.length() > 30) {
				request.setAttribute("errorTest","L'aggiunta della segnalazione non va a buon fine poiché il campo tipo ha una lunghezza maggiore di 30");
				session.setAttribute("error-type", "tipo");
				session.setAttribute("error", "Lunghezza errata");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cliente/segnalazione.jsp"));
			}
			else {
				if(descrizione.length() == 0) {
					request.setAttribute("errorTest","L'aggiunta della segnalazione non va a buon fine poiché il campo descrizione è vuoto");
					session.setAttribute("error-type", "descrizione");
					session.setAttribute("error", "Lunghezza errata");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cliente/segnalazione.jsp"));
				}
				else {
					if(descrizione.length() > 200) {
						request.setAttribute("errorTest","L'aggiunta della segnalazione non va a buon fine poiché il campo descrizione ha una lunghezza maggiore di 200");
						session.setAttribute("error-type", "descrizione");
						session.setAttribute("error", "Lunghezza errata");
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cliente/segnalazione.jsp"));
					}
					else {
						request.setAttribute("errorTest","L'aggiunta della segnalazione va a buon fine");
						session.setAttribute("error-type", null);
						session.setAttribute("error", null);
						segnalazione.setUtenteEmail(email);
						segnalazione.setDescrizione(descrizione);
						segnalazione.setTipo(tipo);
						
						segnalazioneDAO.doSave(segnalazione);
						
						NotificaBean notifica = new NotificaBean();
						notifica.setDescrizione("Nuova Segnalazione");
						notifica.setTipo("Segnalazione");
						
						notificaDAO.doSave(notifica);
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cliente/segnalazione.jsp"));
					}
					
				}
			}
		}
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		try {
			doGet(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
