package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;


@WebServlet(urlPatterns = {"/RestituisciListaPerifericheLibere","/titolare/RestituisciListaPerifericheLibere"})
public class RestituisciListaPerifericheLibere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PerifericaDAO perifericaDAO = new PerifericaDAO();
	private Gson gson = new Gson();
       

    public RestituisciListaPerifericheLibere() {
        super();
  
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			String data = (String) request.getSession().getAttribute("data");
			String fasciaOraria = (String) request.getSession().getAttribute("fasciaOraria");
			ArrayList<PerifericaBean> periferiche = (ArrayList<PerifericaBean>) perifericaDAO.perifericheDisponibili(data, fasciaOraria);
			
			String string = gson.toJson(periferiche);
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
