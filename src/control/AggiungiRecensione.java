package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.notifica.NotificaBean;
import model.notifica.NotificaDAO;
import model.recensione.RecensioneBean;
import model.recensione.RecensioneDAO;
import model.utente.UtenteBean;


@WebServlet(urlPatterns = {"/AggiungiRecensione","/cliente/AggiungiRecensione"})
public class AggiungiRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NotificaDAO notificaDAO = new NotificaDAO(); 
	private RecensioneDAO recensioneDAO= new RecensioneDAO();
    public AggiungiRecensione() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RecensioneBean recensione = new RecensioneBean();
		
		request.getSession().getAttribute("utente");
		
		UtenteBean utente = new UtenteBean();
		
		recensione.setUtenteEmail(utente.getEmail());
		recensione.setDescrizione(request.getParameter("descrizione"));
		recensione.setValutazione(Integer.parseInt(request.getParameter("valutazione")));
		
		try {
			recensioneDAO.doSave(recensione);
			
			NotificaBean notifica = new NotificaBean();
			notifica.setDescrizione("Nuova Recensione");
			notifica.setTipo("Recensione");
			
			notificaDAO.doSave(notifica);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
