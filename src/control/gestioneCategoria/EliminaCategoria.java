package control.gestioneCategoria;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.categoria.CategoriaDAO;


@WebServlet(urlPatterns = {"/EliminaCategoria","/titolare/EliminaCategoria"})
public class EliminaCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
       

    public EliminaCategoria() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		categoriaDAO.doDelete(request.getParameter("nome"));
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
