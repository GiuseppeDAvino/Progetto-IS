package control.gestioneUtente;

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


@WebServlet(urlPatterns = {"/ModificaPassword","/cliente/ModificaPassword"})
public class ModificaPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO();   

    public ModificaPassword() {
        super();
  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		String vecchiaPassword = request.getParameter("vecchiaPassword");
		String nuovaPassword = request.getParameter("nuovaPassword");
		String confermaPassword = request.getParameter("confermaPassword");
		
		if(vecchiaPassword.length() == 0) {
			request.setAttribute("errorTest","La modifica della password non va a buon fine poiché il campo vecchia password è vuoto");
			session.setAttribute("error-type", "vecchiaPassword");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
		}
		else {
			if(!Validatore.isVecchiaPasswordValid(utente.getEmail(), vecchiaPassword)) {
				request.setAttribute("errorTest","La modifica della password non va a buon fine poiché il campo vecchia password è errata");
				session.setAttribute("error-type", "vecchiaPassword");
				session.setAttribute("error", "Password errata");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
			}
			else {
				if(nuovaPassword.length() == 0) {
					request.setAttribute("errorTest","La modifica della password non va a buon fine poiché il campo nuova password è vuoto");
					session.setAttribute("error-type", "nuovaPassword");
					session.setAttribute("error", "Campo vuoto");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
				}
				else {
					if(!Validatore.validaPassword(nuovaPassword)) {
						request.setAttribute("errorTest","La modifica della password non va a buon fine poiché il campo nuova password non rispetta il formato");
						session.setAttribute("error-type", "nuovaPassword");
						session.setAttribute("error", "Formato non valido");
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
					}
					else {
						if(confermaPassword.length() == 0) {
							request.setAttribute("errorTest","La modifica della password non va a buon fine poiché il campo conferma password è vuoto");
							session.setAttribute("error-type", "confermaPassword");
							session.setAttribute("error", "Campo vuoto");
							response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
						}
						else {
							if(!Validatore.isPasswordValid(nuovaPassword, confermaPassword)) {
								request.setAttribute("errorTest","La modifica della password non va a buon fine poiché il campo nuova password e il campo conferma password non corrispondono");
								session.setAttribute("error-type", "confermaPassword");
								session.setAttribute("error", "Le due password non corrispondono");
								response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
							}
							else {
								request.setAttribute("errorTest","La modifica della password va a buon fine");
								session.setAttribute("error-type", null);
								session.setAttribute("error", null);
								
								utente.setPassword(nuovaPassword);
								utenteDAO.doUpdate(utente, utente.getEmail());
								session.setAttribute("utente", utente);
								response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
							}
						}
					}
				}
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
