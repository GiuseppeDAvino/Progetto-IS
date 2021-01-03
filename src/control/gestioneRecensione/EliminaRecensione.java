package control.gestioneRecensione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.recensione.RecensioneDAO;


@WebServlet("/EliminaRecensione")
public class EliminaRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecensioneDAO recensioneDAO = new RecensioneDAO();
       
    public EliminaRecensione() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		recensioneDAO.doDelete(email);
		session.removeAttribute("recensione");
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/recensisci.jsp"));
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
