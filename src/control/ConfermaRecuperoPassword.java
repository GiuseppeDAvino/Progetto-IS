package control;

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
		HttpSession session = request.getSession(false);
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		if(utenteDAO.controllaEmailCodice(utente.getEmail(), codiceVerifica)) {
				utente.setCodiceVerifica("");
				session.setAttribute("utente", utente);
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/cambioPassword.jsp"));
		}
		else {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/confermaRecuperoPassword.jsp"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
