package control;

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
import model.recensione.RecensioneBean;
import model.recensione.RecensioneDAO;
import model.utente.UtenteBean;


@WebServlet(urlPatterns = {"/AggiungiRecensione","/cliente/AggiungiRecensione"})
public class AggiungiRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NotificaDAO notificaDAO = new NotificaDAO(); 
	private RecensioneDAO recensioneDAO= new RecensioneDAO();
    public AggiungiRecensione() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RecensioneBean recensione = new RecensioneBean();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		String email = utente.getEmail();
		String descrizione = request.getParameter("descrizione");
		String valutazione = request.getParameter("valutazione");
		
		if(descrizione.length() == 0) {
			request.setAttribute("errorTest","L'aggiunta della recensione non va a buon fine poichè il campo descrizione è vuoto");
			session.setAttribute("error-type", "descrizione");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/recensisci.jsp"));
		}
		else {
			if(descrizione.length() > 200) {
				request.setAttribute("errorTest","L'aggiunta della recensione non va a buon fine poichè il campo descrizione ha una lunghezza maggiore di 200");
				session.setAttribute("error-type", "descrizione");
				session.setAttribute("error", "Lunghezza errata");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/recensisci.jsp"));
			}
			else {
				if(valutazione.length() == 0) {
					request.setAttribute("errorTest","L'aggiunta della recensione non va a buon fine poichè il campo valutazione è vuoto");
					session.setAttribute("error-type", "valutazione");
					session.setAttribute("error", "Campo vuoto");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/recensisci.jsp"));
				}
				else {
					request.setAttribute("errorTest","L'aggiunta della recensione va a buon fine");
					session.setAttribute("error-type", null);
					session.setAttribute("error", null);
					
					recensione.setUtenteEmail(email);
					recensione.setDescrizione(descrizione);
					recensione.setValutazione(Integer.parseInt(valutazione));
					
					recensioneDAO.doSave(recensione);
					
					NotificaBean notifica = new NotificaBean();
					notifica.setDescrizione("Nuova Recensione");
					notifica.setTipo("Recensione");
					
					notificaDAO.doSave(notifica);
				}
			}
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)  {

		try {
			doGet(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
