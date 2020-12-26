package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.categoria.CategoriaBean;
import model.categoria.CategoriaDAO;


@WebServlet("/DettagliCategoria")
public class DettagliCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoriaDAO categoriaDAO = new CategoriaDAO();  

    public DettagliCategoria() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	
		String nome = (String) request.getParameter("nome");
		try {
			CategoriaBean categoria = categoriaDAO.doRetrieveByKey(nome);
			request.getSession().setAttribute("categoria", categoria);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/dettagliCategoria.jsp"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
