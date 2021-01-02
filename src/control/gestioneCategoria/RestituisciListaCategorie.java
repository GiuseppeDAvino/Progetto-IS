package control.gestioneCategoria;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.categoria.CategoriaBean;
import model.categoria.CategoriaDAO;

/**
 * Servlet implementation class RestituisciListaCategorie
 */
@WebServlet(urlPatterns = {"/RestituisciListaCategorie","/titolare/RestituisciListaCategorie"})
public class RestituisciListaCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private Gson gson = new Gson();
	
    public RestituisciListaCategorie() {
        super();
       
    }

    /**
     * Restituisce una lista di categorie all'interno di un file json
     * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ArrayList<CategoriaBean> categorie = (ArrayList<CategoriaBean>) categoriaDAO.doRetrieveAll();
		String string = gson.toJson(categorie);
		response.getWriter().print(string);
		response.getWriter().flush();
		response.setStatus(200);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
