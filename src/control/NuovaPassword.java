package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.utente.UtenteBean;
import model.utente.UtenteDAO;


@WebServlet("/NuovaPassword")
public class NuovaPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO();
	
    public NuovaPassword() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		String password = request.getParameter("password");
		String confermaPassword = request.getParameter("confermaPassword");
		session.setAttribute("utente", utente);
		
		if(!utenteDAO.validaPassword(request.getParameter("password"))) {
			System.out.println("Formato password errato");
			session.setAttribute("error-type", "password");
			session.setAttribute("error", "Formato password errato");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/cambioPassword.jsp"));
			return;
		}
		else if(!password.equals(confermaPassword)) {
			System.out.println("Le password non corrispondono");
			session.setAttribute("error-type", "password");
			session.setAttribute("error", "Le due password non corrispondono");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/cambioPassword.jsp"));
			return;
		}
		
		utente.setPassword(password);
		utenteDAO.doUpdate(utente, utente.getEmail());
		session.removeAttribute("utente");
		session.setAttribute("utente", utente);
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/index.jsp"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
