package control.gestioneUtente;

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


@WebServlet("/VerificaUtente")
public class VerificaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO();
       

    public VerificaUtente() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codiceVerifica = request.getParameter("codiceVerifica");
		HttpSession session = request.getSession(false);
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		if(utenteDAO.controllaEmailCodice(utente.getEmail(), codiceVerifica)) {
			try {
				utenteDAO.changeState(utente.getEmail());
				utente.setStato(true);
				utente.setCodiceVerifica("");
				request.getSession().setAttribute("utente", utente);
				if((Integer) session.getAttribute("isPressedPrenota")==null) {
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/index.jsp"));
				} 
				else {
					if((Integer) session.getAttribute("isPressedPrenota")==1)
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/dettagliCategoria.jsp"));
				}
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/confermaRegistrazione.jsp"));
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	

}
