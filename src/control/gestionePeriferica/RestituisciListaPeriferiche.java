package control.gestionePeriferica;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;


@WebServlet(urlPatterns = {"/RestituisciListaPeriferiche","/titolare/RestituisciListaPeriferiche"})
public class RestituisciListaPeriferiche extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PerifericaDAO perifericaDAO = new PerifericaDAO();
	private Gson gson = new Gson();

    public RestituisciListaPeriferiche() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		ArrayList<PerifericaBean> periferiche = (ArrayList<PerifericaBean>) perifericaDAO.doRetrieveAll();
		
		String string = gson.toJson(periferiche);
		response.getWriter().print(string);
		response.getWriter().flush();
		response.setStatus(200);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
