package control.gestioneSegnalazione;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.segnalazione.SegnalazioneBean;
import model.segnalazione.SegnalazioneDAO;

/**
 * Servlet implementation class RestituisciListaSegnalazioni
 */
@WebServlet("/RestituisciListaSegnalazioni")
public class RestituisciListaSegnalazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
    private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestituisciListaSegnalazioni() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ArrayList<SegnalazioneBean> prenotazioni=(ArrayList<SegnalazioneBean>) segnalazioneDAO.doRetrieveAll();
		
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
