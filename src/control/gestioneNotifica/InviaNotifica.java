package control.gestioneNotifica;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.notifica.NotificaBean;
import model.notifica.NotificaDAO;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;


@WebServlet("/InviaNotifica")
public class InviaNotifica extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NotificaDAO notificaDAO = new NotificaDAO();   
    private UtenteDAO utenteDAO = new UtenteDAO();   
    
    public void setMock(NotificaDAO dao) {
    	this.notificaDAO = dao;
    }
    
    public InviaNotifica() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String descrizione = request.getParameter("descrizione");
		session.setAttribute("errorType",null);
		session.setAttribute("error", null);
		if(descrizione.length() == 0) {
			request.setAttribute("errorTest","L'invio della notifica non va a buon fine poich� il campo descrizione � vuoto");
			session.setAttribute("errorType", "descrizione");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
		}
		else {
			if(descrizione.length() > 200) {
				request.setAttribute("errorTest","L'invio della notifica non va a buon fine poich� il campo descrizione ha una lunghezza maggiore di 200");
				session.setAttribute("errorType", "descrizione");
				session.setAttribute("error", "Campo vuoto");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
			}
			else {
				request.setAttribute("errorTest","L'invio della notifica va a buon fine");
				session.setAttribute("errorType", "validoDati");
				session.setAttribute("error", "notifica correttamente inviata");
				
				NotificaBean notifica = new NotificaBean();
				notifica.setDescrizione(descrizione);
				notificaDAO.doSaveNotificaUtente(notifica, (ArrayList<UtenteBean>)utenteDAO.doRetrieveAllByRuolo(Ruolo.cliente.name()));
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
				
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  {
		try {
			doGet(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
