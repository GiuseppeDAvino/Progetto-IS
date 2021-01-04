package control.gestioneRecensione;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.recensione.RecensioneBean;
import model.recensione.RecensioneDAO;


@WebServlet("/RestituisciListaRecensioni")
public class RestituisciListaRecensioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecensioneDAO recensioneDAO = new RecensioneDAO();
    private Gson gson = new Gson();
    
    public RestituisciListaRecensioni() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ArrayList<RecensioneBean> prenotazioni=(ArrayList<RecensioneBean>) recensioneDAO.doRetrieveAllNonVerificata();
		
		String string = gson.toJson(prenotazioni);
		response.getWriter().print(string);
		response.getWriter().flush();
		response.setStatus(200);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
