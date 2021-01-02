package control.gestioneUtente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;

/**
 * Servlet implementation class EliminaPostazione
 */
@WebServlet(urlPatterns = {"/EliminaGestore","/titolare/EliminaGestore"})
public class EliminaGestore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO();
	
    public EliminaGestore() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
	 * Permette di eliminare una postazione prendendo la chiave dal bottone corrispettivo alla postazione
	 * Se la postazione è stata prenotata almeno una volta viene disattivata 
	 * Se la postazione non è stata mai prenotata viene eliminata
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		UtenteBean utente=utenteDAO.doRetrieveByKey(email);
		utente.setRuolo(Ruolo.cliente);
		utenteDAO.doUpdate(utente, email);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
