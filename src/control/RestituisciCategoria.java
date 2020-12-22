package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.categoria.CategoriaBean;
import model.categoria.CategoriaDAO;


@WebServlet(urlPatterns = {"/RestituisciCategoria","/titolare/RestituisciCategoria"})
public class RestituisciCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public RestituisciCategoria() {
        super();

    }


    /**
	 * Restituisce una categoria in un file json
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AOOOOOOOOO" + request.getParameter("nome"));
		String nome = (String) request.getParameter("nome");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}