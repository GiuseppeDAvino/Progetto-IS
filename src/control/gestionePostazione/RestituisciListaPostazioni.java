package control.gestionePostazione;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.postazione.PostazioneBean;
import model.postazione.PostazioneDAO;

/**
 * Servlet implementation class RestituisciListaPostazioni
 */
@WebServlet(urlPatterns = {"/RestituisciListaPostazioni","/titolare/RestituisciListaPostazioni"})
public class RestituisciListaPostazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PostazioneDAO postazioneDAO = new PostazioneDAO();
	private Gson gson = new Gson();
	
    public RestituisciListaPostazioni() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Restituisce una lista di postazioni all'interno di un file json
     * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ArrayList<PostazioneBean> postazioni = (ArrayList<PostazioneBean>) postazioneDAO.doRetrieveAll();
		String string = gson.toJson(postazioni);
		response.getWriter().print(string);
		response.getWriter().flush();
		response.setStatus(200);
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
