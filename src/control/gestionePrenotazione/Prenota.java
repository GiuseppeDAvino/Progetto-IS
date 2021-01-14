package control.gestionePrenotazione;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.categoria.CategoriaBean;
import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;
import model.postazione.PostazioneDAO;
import model.prenotazione.PrenotazioneBean;
import model.prenotazione.PrenotazioneDAO;
import model.utente.UtenteBean;

@WebServlet(urlPatterns = { "/Prenota", "/cliente/Prenota" })
public class Prenota extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
	private PostazioneDAO postazioneDAO = new PostazioneDAO();
	private PerifericaDAO perifericaDAO = new PerifericaDAO();

	public Prenota() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrenotazioneBean prenotazione =(PrenotazioneBean) session.getAttribute("datiPrenotazione");		
		ArrayList<PerifericaBean> periferiche = (ArrayList<PerifericaBean>) session.getAttribute("datiPeriferiche");		
		if(prenotazione==null) {
			System.out.println("if 1");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/dettagliCategoria.jsp"));
			
		}
		if(periferiche==null) {
			System.out.println("if 2");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/dettagliCategoria.jsp"));
		}else {
			System.out.println("stampa periferiche " + periferiche);
			prenotazioneDAO.prenotaConPeriferiche(prenotazione, periferiche);
			session.setAttribute("datiCategoria",null);
			session.setAttribute("datiPeriferiche",null);
			session.setAttribute("datiPrenotazione",null);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));
		}}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
}
