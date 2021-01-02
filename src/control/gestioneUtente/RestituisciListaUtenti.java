package control.gestioneUtente;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;

@WebServlet(urlPatterns = {"/RestituisciListaUtenti","/titolare/RestituisciListaUtenti"})
public class RestituisciListaUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO();
	private Gson gson = new Gson();

    public RestituisciListaUtenti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ArrayList<UtenteBean> utenti=(ArrayList<UtenteBean>) utenteDAO.doRetrieveAllByRuolo(Ruolo.cliente.name());
		
		String string = gson.toJson(utenti);
		response.getWriter().print(string);
		response.getWriter().flush();
		response.setStatus(200);
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
