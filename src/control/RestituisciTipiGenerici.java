package control;

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


@WebServlet("/RestituisciTipiGenerici")
public class RestituisciTipiGenerici extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private Gson gson = new Gson();

    public RestituisciTipiGenerici() {
        super();
 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			ArrayList<CategoriaBean> categorie = (ArrayList<CategoriaBean>) categoriaDAO.doRetrieveAllTipiGenerici();
			
			String string = gson.toJson(categorie);
			response.getWriter().print(string);
			response.getWriter().flush();
			response.setStatus(200);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
