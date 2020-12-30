package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.servizio.RandomString;
import model.servizio.Utility;
import model.servizio.Validatore;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;

@WebServlet("/RichiestaRecuperoPassword")
public class RichiestaRecuperoPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO();
	private RandomString randomString;

	public RichiestaRecuperoPassword() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");

		if (email.length() == 0) {
			request.setAttribute("errorTest",
					"La richiesta per il recupero password non va a buon fine poiché il campo email è vuoto");
			session.setAttribute("error-type", "email");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(
					response.encodeRedirectURL(request.getContextPath() + "/richiestaRecuperoPassword.jsp"));
		} else {
			if (!Validatore.validaEmail(email)) {
				request.setAttribute("errorTest",
						"La richiesta per il recupero password non va a buon fine poiché il campo email non rispetta il formato");
				session.setAttribute("error-type", "email");
				session.setAttribute("error", "Formato non valido");
				response.sendRedirect(
						response.encodeRedirectURL(request.getContextPath() + "/richiestaRecuperoPassword.jsp"));
			} else {
				if (Validatore.isEmailValid(email)) {
					request.setAttribute("errorTest",
							"La richiesta per il recupero password non va a buon fine poiché il campo email non esiste nel database");
					session.setAttribute("error-type", "email");
					session.setAttribute("error", "Email non esistente");
					response.sendRedirect(
							response.encodeRedirectURL(request.getContextPath() + "/richiestaRecuperoPassword.jsp"));
				} else {
					request.setAttribute("errorTest",
							"La richiesta per il recupero password va a buon fine e si può procedere con il recupero della password");
					session.setAttribute("errorType", null);
					session.setAttribute("error", null);
					UtenteBean utente = utenteDAO.doRetrieveByKey(email);
					randomString = new RandomString(7, new Random());
					String codiceVerifica = randomString.nextString();
					utenteDAO.cambiaCodice(codiceVerifica, email);
					session.setAttribute("emailCodice", email);
					Utility.sendMail(email, codiceVerifica, 2);
					response.sendRedirect(
							response.encodeRedirectURL(request.getContextPath() + "/confermaRecuperoPassword.jsp"));

				} // chiusura else email presente nel db
			} // chiusura else email valida
		} // chiusura else email non vuota

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
