package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.servizio.RandomString;
import model.servizio.Utility;
import model.utente.UtenteBean;
import model.utente.UtenteBean.Ruolo;
import model.utente.UtenteDAO;


@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO();
	private RandomString randomString;

    public Registrazione() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteBean utente = new UtenteBean();
		String email = request.getParameter("email");
		if(emailIsValid(email)) {
			utente.setEmail(email);
		}
		utente.setNome(request.getParameter("nome"));
		utente.setCognome(request.getParameter("cognome"));
		String username = request.getParameter("username");
		if(usernameIsValid(username)) {
			utente.setUsername(username);
		}
		utente.setRuolo(Ruolo.cliente);
		String password = request.getParameter("password");
		String confPassword = request.getParameter("confPassword");
		if(passwordIsValid(password, confPassword)) {
			utente.setPassword(password);
		}
		
		try {
			randomString = new RandomString(7,new Random());
			String codiceVerifica = randomString.nextString();
			utente.setCodiceVerifica(codiceVerifica);
			utenteDAO.doSave(utente);
			UtenteBean prov = utenteDAO.doRetrieveByKey(email);
			request.getSession().setAttribute("utente", prov);
			Utility.sendMail(email, codiceVerifica);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/confermaRegistrazione.jsp"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private boolean emailIsValid(String email) {
		try {
			if(utenteDAO.doRetrieveByKey(email) == null)
				return false;
			else
				return true;
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	private boolean usernameIsValid(String username) {
		try {
			if(utenteDAO.doRetrieveByKey(username) == null)
				return false;
			else
				return true;
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	private boolean passwordIsValid(String password, String confPassword) {
			if(password.equals(confPassword))
				return false;
			else
				return true;
	}

}
