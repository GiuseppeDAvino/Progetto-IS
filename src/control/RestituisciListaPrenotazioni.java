package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.prenotazione.PrenotazioneBean;
import model.prenotazione.PrenotazioneDAO;

/**
 * Servlet implementation class RestituisciListaPrenotazioni
 */
@WebServlet(urlPatterns = {"/RestituisciListaPrenotazioni","/titolare/RestituisciListaPrenotazioni"})
public class RestituisciListaPrenotazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestituisciListaPrenotazioni() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ArrayList<PrenotazioneBean> prenotazioni=(ArrayList<PrenotazioneBean>) prenotazioneDAO.doRetrieveAll();
		
		String string = gson.toJson(prenotazioni);
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
