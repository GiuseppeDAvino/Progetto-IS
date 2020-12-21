package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;


@WebServlet(urlPatterns = {"/RestituisciPeriferica","/titolare/RestituisciPeriferica"})
public class RestituisciPeriferica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PerifericaDAO perifericaDAO = new PerifericaDAO();
	private Gson gson = new Gson();
	
    public RestituisciPeriferica() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		try {
			PerifericaBean periferica = perifericaDAO.doRetrieveByKey(request.getParameter("nome"));
			
			String string = gson.toJson(periferica);
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
