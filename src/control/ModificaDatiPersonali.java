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

@WebServlet("/ModificaDatiPersonali")
public class ModificaDatiPersonali extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO(); 
       
    public ModificaDatiPersonali() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		
		if(nome.length() == 0) {
			request.setAttribute("errorTest","La modifica non va a buon fine poiché il campo nome è vuoto");
			session.setAttribute("error-type", "nome");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
		}
		else {
			if(!Validatore.validaNomeCognome(nome)) {
				request.setAttribute("errorTest","La modifica non va a buon fine poiché il campo nome non rispetta il formato");
				session.setAttribute("error-type", "nome");
				session.setAttribute("error", "Formato non valido");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
			}
			else {
				if(cognome.length() == 0) {
					request.setAttribute("errorTest","La modifica non va a buon fine poiché il campo cognome è vuoto");
					session.setAttribute("error-type", "cognome");
					session.setAttribute("error", "Campo vuoto");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
				}
				else {
					if(!Validatore.validaNomeCognome(cognome)) {
						request.setAttribute("errorTest","La modifica non va a buon fine poiché il campo cognome non rispetta il formato");
						session.setAttribute("error-type", "cognome");
						session.setAttribute("error", "Formato non valido");
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
					}
					else {
						if(username.length() == 0) {
							request.setAttribute("errorTest","La modifica non va a buon fine poiché il campo username è vuoto");
							session.setAttribute("error-type", "username");
							session.setAttribute("error", "Campo vuoto");
							response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
						}
						else {
							if(!Validatore.validaUsername(username)) {
								request.setAttribute("errorTest","La modifica non va a buon fine poiché il campo username non rispetta il formato");
								session.setAttribute("error-type", "username");
								session.setAttribute("error", "Formato non valido");
								response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
							}
							else {
								if(!Validatore.isUsernameValid(username)) {
									request.setAttribute("errorTest", "La modifica non va a buon fine poiché l'username è già presente nel database");
									session.setAttribute("error-type", "username");
									session.setAttribute("error", "Username già esistente nel sistema");
									response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/user.jsp"));
								}
								else {
									request.setAttribute("errorTest", "La modifica va a buon fine");
									session.setAttribute("error-type", null);
									session.setAttribute("error", null);
									
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
