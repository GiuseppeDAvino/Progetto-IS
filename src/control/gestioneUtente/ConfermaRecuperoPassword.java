package control.gestioneUtente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.utente.UtenteBean;
import model.utente.UtenteDAO;

@WebServlet("/ConfermaRecuperoPassword")
public class ConfermaRecuperoPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO();
       
    public ConfermaRecuperoPassword() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codiceVerifica = request.getParameter("codiceVerifica");
		HttpSession session = request.getSession(true);
		String email=(String) session.getAttribute("emailCodice");
		if(codiceVerifica.length()==0) {
			request.setAttribute("errorTest",
					"Inserimento del codice di verifica non va a buon fine poiché il campo codice è vuoto");
			session.setAttribute("error-type", "codiceVerifica");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(
					response.encodeRedirectURL(request.getContextPath() + "/confermaRecuperoPassword.jsp"));
		}else {
			if(!utenteDAO.controllaEmailCodice(email, codiceVerifica)) {
				request.setAttribute("errorTest",
						"Inserimento del codice di verifica non va a buon fine poiché il codice non è associato alla sua email");
				session.setAttribute("error-type", "codiceVerifica");
				session.setAttribute("error", "Campo vuoto");
				response.sendRedirect(
						response.encodeRedirectURL(request.getContextPath() + "/confermaRecuperoPassword.jsp"));
			}
			else {
				request.setAttribute("errorTest",
						"Inserimento del codice di verifica è avvenuto con successo e si può procedere con la creazione di una nuova password");
				session.setAttribute("error-type", null);
				session.setAttribute("error", null);
				utenteDAO.cambiaCodice("", email);
				
				response.sendRedirect(
						response.encodeRedirectURL(request.getContextPath() + "/cambioPassword.jsp"));
			}//chiusura else codice giusto
		}//chiusura else codice non vuoto
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
