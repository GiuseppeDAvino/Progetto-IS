package control.gestioneNotifica;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.notifica.NotificaBean;
import model.notifica.NotificaDAO;

/**
 * Servlet implementation class RestituisciListaPrenotazioni
 */
@WebServlet(urlPatterns = {"/RestituisciListaNotifiche","/titolare/RestituisciListaNotifiche"})
public class RestituisciListaNotifiche extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotificaDAO notificaDAO = new NotificaDAO();
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestituisciListaNotifiche() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ArrayList<NotificaBean> notifiche=(ArrayList<NotificaBean>) notificaDAO.doRetrieveAll();
		
		String string = gson.toJson(notifiche);
		response.getWriter().print(string);
		response.getWriter().flush();
		response.setStatus(200);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
