package control.gestioneNotifica;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.notifica.NotificaBean;
import model.notifica.NotificaDAO;
import model.utente.UtenteDAO;
import model.utente.UtenteBean;
import model.utente.UtenteBean.Ruolo;

@WebServlet(urlPatterns = {"/AggiungiNotifica","/cliente/AggiungiNotifica"})
public class AggiungiNotifica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NotificaDAO notificaDAO = new NotificaDAO(); 
	private UtenteDAO utenteDAO = new UtenteDAO(); 
	
    public AggiungiNotifica() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		

		String descrizione = request.getParameter("descrizione");


		
				if(descrizione.length() == 0) {
					request.setAttribute("errorTest","L'aggiunta della notifica non va a buon fine poiché il campo descrizione è vuoto");
					session.setAttribute("error-type", "descrizione");
					session.setAttribute("error", "Lunghezza errata");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
				}
				else {
					if(descrizione.length() > 200) {
						request.setAttribute("errorTest","L'aggiunta della notifica non va a buon fine poiché il campo descrizione ha una lunghezza maggiore di 200");
						session.setAttribute("error-type", "descrizione");
						session.setAttribute("error", "Lunghezza errata");
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
					}
					else {
						request.setAttribute("errorTest","L'aggiunta della segnalazione va a buon fine");
						session.setAttribute("error-type", null);
						session.setAttribute("error", null);
						NotificaBean notifica = new NotificaBean();
						notifica.setDescrizione(descrizione);
						
						notificaDAO.doSaveNotificaUtente(notifica, (ArrayList<UtenteBean>)utenteDAO.doRetrieveAllByRuolo(Ruolo.cliente.name()));
					}
					
				}
			}
		
		
		
	


	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		try {
			doGet(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
