package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.servizio.Validatore;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;

@WebServlet("/NuovaPassword")
public class NuovaPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO();

	public NuovaPassword() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String email=(String) session.getAttribute("emailCodice");
		String password = request.getParameter("password");
		String confermaPassword = request.getParameter("confermaPassword");

		
		
		if(password.length()==0) {
			request.setAttribute("errorTest",
					"La creazione della nuova password non va a buon fine poichè il campo password è vuoto");
			session.setAttribute("error-type", "password");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(
					response.encodeRedirectURL(request.getContextPath() + "/cambioPassword.jsp"));
		}
		else {
			if(confermaPassword.length()==0) {
				request.setAttribute("errorTest",
						"La creazione della nuova password non va a buon fine poichè il campo conferma password è vuoto");
				session.setAttribute("error-type", "confermaPassword");
				session.setAttribute("error", "Campo vuoto");
				response.sendRedirect(
						response.encodeRedirectURL(request.getContextPath() + "/cambioPassword.jsp"));
			}
			else {
				if(!Validatore.validaPassword(password)) {
					request.setAttribute("errorTest",
							"La creazione della nuova password non va a buon fine poichè il campo password non rispetta il formato");
					session.setAttribute("error-type", "password");
					session.setAttribute("error", "Formato non valido");
					response.sendRedirect(
							response.encodeRedirectURL(request.getContextPath() + "/cambioPassword.jsp"));
				}
				else {
					if(!password.equals(confermaPassword)) {
						request.setAttribute("errorTest",
								"La creazione della nuova password non va a buon fine  poichè il campo password e il campo conferma password non corrispondono");
						session.setAttribute("error-type", "confermaPassword");
						session.setAttribute("error", "Le due password non corrispondono");
						response.sendRedirect(
								response.encodeRedirectURL(request.getContextPath() + "/cambioPassword.jsp"));
					}
					else {
						request.setAttribute("errorTest",
								"La creazione della nuova password va a buon fine");
						session.setAttribute("error-type", null);
						session.setAttribute("error", null);
						UtenteBean utente=utenteDAO.doRetrieveByKey(email);
						utente.setPassword(password);
						utenteDAO.doUpdate(utente, utente.getEmail());
						session.setAttribute("utente", utente);
						response.sendRedirect(
								response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));
					}//chiusura else corretto
				}//chiusura else password rispetta formato
			}//chiusura else conferma password non vuota
		}//chiusura else password non vuota
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			 {

		try {
			doGet(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
