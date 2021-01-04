package control.gestioneCategoria;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.categoria.CategoriaBean;
import model.categoria.CategoriaDAO;

/**
 * Servlet implementation class CategoriaDaModificare
 */
@WebServlet("/CategoriaDaModificare")
public class CategoriaDaModificare extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CategoriaDAO dao = new CategoriaDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaDaModificare() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		CategoriaBean categoria= dao.doRetrieveByKey(request.getParameter("nome"));
		request.getSession().setAttribute("categoria",categoria);
		request.getSession().setAttribute("nomecategoria",request.getParameter("nome"));
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/titolare/modificaCategoriaForm.jsp"));
		


		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
