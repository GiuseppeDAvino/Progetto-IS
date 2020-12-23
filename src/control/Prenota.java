package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.categoria.CategoriaBean;
import model.periferica.PerifericaBean;
import model.postazione.PostazioneDAO;
import model.prenotazione.PrenotazioneBean;
import model.prenotazione.PrenotazioneDAO;
import model.utente.UtenteBean;


@WebServlet(urlPatterns = {"/Prenota","/cliente/Prenota"})
public class Prenota extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
	private PostazioneDAO postazioneDAO = new PostazioneDAO();

    public Prenota() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
		if(!isInSessionUtente(utente)) 
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Login.jsp"));
		else if(!isCliente(utente)) 
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Index.jsp"));
		
		else {
			CategoriaBean categoria = (CategoriaBean) request.getSession().getAttribute("categoria");
			@SuppressWarnings("unchecked")
			ArrayList<PerifericaBean> periferiche = (ArrayList<PerifericaBean>) request.getSession().getAttribute("periferiche");
			
			PrenotazioneBean prenotazione = new PrenotazioneBean();
			String data = (String) request.getSession().getAttribute("data");
			String fasciaOraria = (String) request.getSession().getAttribute("fasciaOraria");
		
			try {
				prenotazione.setData(data);
				prenotazione.setFasciaOraria(fasciaOraria);
				prenotazione.setUtenteEmail(utente.getEmail());
				prenotazione.setPostazioneId(postazioneDAO.postazioneLiberaCategoria(categoria, data, fasciaOraria).getId());
				//prenotazione.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
				prenotazione.setQr(""); // TODO gestire il QRcode
				System.out.println("PROVA PRENOTAZIONE" + prenotazione.getUtenteEmail());
				//prenotazione con l'aggiunta di almeno di una periferica
				//prenotazioneDAO.prenotaConPeriferiche(prenotazione, periferiche);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cliente/prova.jsp"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private boolean isInSessionUtente(UtenteBean utente) {
		if(utente == null) {
			return false;
		}
		return true;
	}
	
	private boolean isCliente(UtenteBean utente) {
		if(utente.getRuolo().name().equals("cliente")) {
			return true;
		}
		return false;
	}

}
