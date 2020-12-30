package control;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.utente.UtenteBean;
import model.utente.UtenteDAO;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtenteDAO utenteDAO = new UtenteDAO();

	public Login() {
		super();

	}

	/**
	 * Permette di effettuare il login prendendo i dati dal form di login
	 * controllando i campi e restituendo errori nel caso siano errati Nel caso di
	 * dati corretti inserisce i dati nella sessione
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		try {
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			if (email.length() == 0) {
				request.setAttribute("errorTest", "Il login non va a buon fine poiché il campo email è vuoto");
				session.setAttribute("errorType", "email");
				session.setAttribute("error", "Campo vuoto");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp"));
				
			} else{
					if(pass.length()==0) {
						request.setAttribute("errorTest", "Il login non va a buon fine poiché il campo password è vuoto");
						session.setAttribute("errorType", "password");
						session.setAttribute("error", "Campo vuoto");
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp"));	
					}
					else {
						UtenteBean utente = utenteDAO.doRetrieveByKey(email);
						if(utente.getEmail().equals("")) {//Controllo se l'email non è presente nel db
							request.setAttribute("errorTest", "Il login non va a buon fine poiché l'email non è presente nel db");
							session.setAttribute("errorType", "email");
							session.setAttribute("error", "Email non presente nel database");
							response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp"));
						}
						else {
							MessageDigest md = MessageDigest.getInstance("SHA-256");
							byte curr[] = md.digest(pass.getBytes());//Password inserita hashata in SHA-256
							byte user[] = utente.getPassword();//Password dell'utente
							if(Arrays.compare(curr, user)!=0) {
								request.setAttribute("errorTest", "Il login non va a buon fine poiché l'email e la password non combaciano");
								session.setAttribute("errorType", "password");
								session.setAttribute("error", "Email o password errate");
								response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp"));
							}
							else {
								request.setAttribute("errorTest", "Il login viene effettuato correttamente");
								session.setAttribute("utente", utente);
								session.setAttribute("errorType", null);
								session.setAttribute("error", null);
								if (utente.isStato() == false) {
									response.sendRedirect(
											response.encodeRedirectURL(request.getContextPath() + "/confermaRegistrazione.jsp"));
								} else if (session.getAttribute("isPressedPrenota") == null) {
									response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));
								} else if ((Integer) session.getAttribute("isPressedPrenota") == 1) {
									response.sendRedirect(
											response.encodeRedirectURL(request.getContextPath() + "/dettagliCategoria.jsp"));
								}
							}//Chiusura else corretto
						}//Chiusura else email presente nel db
					}//Chiusura else password non vuota
			}//Chiusura else email non vuota
		}//Chiusura try
			catch (Exception e) {
				e.printStackTrace();
			}		
	}//Chiusura doGet
		

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);

	}
}
