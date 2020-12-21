package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RestituisciListaPostazioni
 */
@WebServlet("/RestituisciListaPostazioni")
public class RestituisciListaPostazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RestituisciListaPostazioni() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Restituisce una lista di postazioni all'interno di un file json
     * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
