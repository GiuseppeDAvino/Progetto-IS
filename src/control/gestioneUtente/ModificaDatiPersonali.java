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

@WebServlet(urlPatterns = {"/ModificaDatiPersonali","/cliente/ModificaDatiPersonali"})
public class ModificaDatiPersonali extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO(); 
       
    public ModificaDatiPersonali() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("errorType", null);
		session.setAttribute("error", null);
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		
		if(nome.length() == 0) {
			request.setAttribute("errorTest","La modifica non va a buon fine poiché il campo nome è vuoto");
			session.setAttribute("errorType", "nome");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
		}
		else {
			if(!Validatore.validaNomeCognome(nome)) {
				request.setAttribute("errorTest","La modifica non va a buon fine poiché il campo nome non rispetta il formato");
				session.setAttribute("errorType", "nome");
				session.setAttribute("error", "Formato non valido");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
			}
			else {
				if(cognome.length() == 0) {
					request.setAttribute("errorTest","La modifica non va a buon fine poiché il campo cognome è vuoto");
					session.setAttribute("errorType", "cognome");
					session.setAttribute("error", "Campo vuoto");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
				}
				else {
					if(!Validatore.validaNomeCognome(cognome)) {
						request.setAttribute("errorTest","La modifica non va a buon fine poiché il campo cognome non rispetta il formato");
						session.setAttribute("errorType", "cognome");
						session.setAttribute("error", "Formato non valido");
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
					}
					else {
						if(username.length() == 0) {
							request.setAttribute("errorTest","La modifica non va a buon fine poiché il campo username è vuoto");
							session.setAttribute("errorType", "username");
							session.setAttribute("error", "Campo vuoto");
							response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
						}
						else {
							if(!Validatore.validaUsername(username)) {
								request.setAttribute("errorTest","La modifica non va a buon fine poiché il campo username non rispetta il formato");
								session.setAttribute("errorType", "username");
								session.setAttribute("error", "Formato non valido");
								response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
							}
							else {
								if(!Validatore.isUsernameValid(utente,username)) {
									request.setAttribute("errorTest", "La modifica non va a buon fine poiché l'username è già presente nel database");
									session.setAttribute("errorType", "username");
									session.setAttribute("error", "Username già esistente nel sistema");
									response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/user.jsp"));
								}
								else {
									request.setAttribute("errorTest", "La modifica va a buon fine");
									session.setAttribute("errorType", "validoDati");
									session.setAttribute("error", "La modifica dei dati personali è stata effettuata");
									
									utente.setNome(nome);
									utente.setCognome(cognome);
									utente.setUsername(username);
									
									utenteDAO.doUpdate(utente,utente.getEmail());
									session.setAttribute("utente", utente);
									response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/user.jsp"));
									
								}
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
