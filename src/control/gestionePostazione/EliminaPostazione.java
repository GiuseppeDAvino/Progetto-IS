package control.gestionePostazione;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.postazione.PostazioneBean;
import model.postazione.PostazioneDAO;

/**
 * Servlet implementation class EliminaPostazione
 */
@WebServlet(urlPatterns = {"/EliminaPostazione","/titolare/EliminaPostazione"})
public class EliminaPostazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostazioneDAO postazioneDAO = new PostazioneDAO();
	
    public EliminaPostazione() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
	 * Permette di eliminare una postazione prendendo la chiave dal bottone corrispettivo alla postazione
	 * Se la postazione è stata prenotata almeno una volta viene disattivata 
	 * Se la postazione non è stata mai prenotata viene eliminata
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostazioneBean postazione = new PostazioneBean();
		postazione.setId(Integer.parseInt(request.getParameter("id")));
		if(postazioneDAO.eStataUtilizzata(postazione))
			postazioneDAO.cambiaDisponibilita(postazione);
		else
			postazioneDAO.doDelete(postazione.getId());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
