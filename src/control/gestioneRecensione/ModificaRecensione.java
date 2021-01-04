package control.gestioneRecensione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.notifica.NotificaBean;
import model.notifica.NotificaDAO;
import model.recensione.RecensioneBean;
import model.recensione.RecensioneDAO;
import model.utente.UtenteBean;


@WebServlet(urlPatterns = {"/ModificaRecensione","/cliente/ModificaRecensione"})
public class ModificaRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NotificaDAO notificaDAO = new NotificaDAO(); 
	private RecensioneDAO recensioneDAO= new RecensioneDAO();
       
 
    public ModificaRecensione() {
        super();
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("errorType", null);
		session.setAttribute("error", null);
		RecensioneBean recensione = new RecensioneBean();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		String email = utente.getEmail();
		String descrizione = request.getParameter("descrizione");
		String valutazione = request.getParameter("valutazione");
		
		if(descrizione.length() == 0) {
			request.setAttribute("errorTest","L'aggiunta della recensione non va a buon fine poiché il campo descrizione è vuoto");
			session.setAttribute("errorType", "descrizione");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cliente/recensisci.jsp"));
		}
		else {
			if(descrizione.length() > 200) {
				request.setAttribute("errorTest","L'aggiunta della recensione non va a buon fine poiché il campo descrizione ha una lunghezza maggiore di 200");
				session.setAttribute("errorType", "descrizione");
				session.setAttribute("error", "Lunghezza errata");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cliente/recensisci.jsp"));
			}
			else {
				if(valutazione == null) {
					request.setAttribute("errorTest","L'aggiunta della recensione non va a buon fine poiché il campo valutazione è vuoto");
					session.setAttribute("errorType", "valutazione");
					session.setAttribute("error", "Campo vuoto");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cliente/recensisci.jsp"));
				}
				else {
					request.setAttribute("errorTest","L'aggiunta della recensione va a buon fine");
					session.setAttribute("errorType", "validoDati");
					session.setAttribute("error", "Recensione modificata");
					
					recensione.setUtenteEmail(email);
					recensione.setDescrizione(descrizione);
					recensione.setValutazione(Integer.parseInt(valutazione));
					session.removeAttribute("recensione");
					session.setAttribute("recensione", recensione);
					recensioneDAO.doUpdate(recensione, utente.getEmail());
					
					NotificaBean notifica = new NotificaBean();
					notifica.setDescrizione("Nuova Recensione");
					notifica.setTipo("Recensione");
					
					notificaDAO.doSave(notifica);
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cliente/recensisci.jsp"));
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
