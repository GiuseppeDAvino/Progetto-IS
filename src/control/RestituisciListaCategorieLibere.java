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


@WebServlet(urlPatterns = {"/RestituisciListaCategorieLibere","/titolare/RestituisciListaCategorieLibere"})
public class RestituisciListaCategorieLibere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private Gson gson = new Gson();
	
    public RestituisciListaCategorieLibere() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			System.out.println(request.getParameter("data"));
			System.out.println(request.getParameter("fasciaOraria"));
			System.out.println(request.getParameter("tipoGenerico"));
			
			ArrayList<CategoriaBean> categorie = (ArrayList<CategoriaBean>) categoriaDAO.categorieConPostazioniLibere(request.getParameter("data"), request.getParameter("fasciaOraria"), request.getParameter("tipoGenerico"));
			
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
