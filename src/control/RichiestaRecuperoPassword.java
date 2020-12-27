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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		if(utenteDAO.doRetrieveByKey(email).getEmail().equals("")) {
			System.out.println("Email non trovata");
			session.setAttribute("error-type", "email");
			session.setAttribute("error", "Questa email non corrisponde a nessun utente");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/registrazione.jsp"));
			return;
		}
		else {
			UtenteBean utente = utenteDAO.doRetrieveByKey(email);
			randomString = new RandomString(7,new Random());
			String codiceVerifica = randomString.nextString();
			utente.setCodiceVerifica(codiceVerifica);
			utenteDAO.doUpdate(utente, email);
			UtenteBean prov = utenteDAO.doRetrieveByKey(email);
			request.getSession().setAttribute("utente", prov);
			try {
				Utility.sendMail(email, codiceVerifica,2);
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/confermaRecuperoPassword.jsp"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
