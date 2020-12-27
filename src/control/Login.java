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
	 * Permette di effettuare il login prendendo i dati dal form di login controllando i campi e restituendo errori nel caso siano errati
	 * Nel caso di dati corretti inserisce i dati nella sessione
	 * */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		try {
			UtenteBean utente = utenteDAO.doRetrieveByKey(request.getParameter("email"));
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String str = request.getParameter("password");
			byte curr[] = md.digest(str.getBytes());
			byte user[] = utente.getPassword();

			if (utenteDAO.doRetrieveByKey(request.getParameter("email")).getEmail().equals("")) {
				session.setAttribute("errorType", "emailDB");
				session.setAttribute("error", "Email non presente nel database");

				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp"));
			} else if (Arrays.compare(curr, user) == 0) {
				session.setAttribute("utente", utente);
				session.setAttribute("errorType", null);
				session.setAttribute("error", null);
				if(utente.isStato() == false) {
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/confermaRegistrazione.jsp"));
				}
				else if((Integer)session.getAttribute("isPressedPrenota")==1) {
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/dettagliCategoria.jsp"));
					} 
					else 
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/index.jsp"));
					
			} else {
				session.setAttribute("errorType", "wrongCred");
				session.setAttribute("error", "Password errata");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp"));
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
