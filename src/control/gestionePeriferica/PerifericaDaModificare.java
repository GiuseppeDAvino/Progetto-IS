package control.gestionePeriferica;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;

/**
 * Servlet implementation class PerifericaDaModificare
 */
@WebServlet("/PerifericaDaModificare")
public class PerifericaDaModificare extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PerifericaDAO dao = new PerifericaDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerifericaDaModificare() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		PerifericaBean periferica= dao.doRetrieveByKey(request.getParameter("nome"));
		request.getSession().setAttribute("periferica",periferica);
		request.getSession().setAttribute("nomeperiferica",request.getParameter("nome"));
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/ModificaPerifericaForm.jsp"));
		


		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
