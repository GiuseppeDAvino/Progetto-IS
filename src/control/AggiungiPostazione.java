package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.postazione.PostazioneBean;
import model.postazione.PostazioneDAO;
import model.servizio.Validatore;

/**
 * Servlet implementation class AggiungiPostazione
 */
@WebServlet(urlPatterns = { "/AggiungiPostazione", "/titolare/AggiungiPostazione" })
public class AggiungiPostazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PostazioneDAO postazioneDAO = new PostazioneDAO();

	public AggiungiPostazione() {
		super();
	}

	
	/**
	 * Permette di aggiungere una postazione prendendo i dati dal form per l'inserimento di una postazione
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nomeCategoria = request.getParameter("nomeCategoria");
		
		if(nomeCategoria.length() == 0) {
			request.setAttribute("errorTest","L'aggiunta della postazione non va a buon fine poichè il campo nome categoria è vuoto");
			session.setAttribute("error-type", "nomeCategoria");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
		}
		else {
			if(!Validatore.isNomeCategoriaValid(nomeCategoria)) {
				request.setAttribute("errorTest","L'aggiunta della postazione non va a buon fine poichè il campo nome categoria non è presente nel database");
				session.setAttribute("error-type", "nomeCategoria");
				session.setAttribute("error", "Campo vuoto");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
			}
			else {
				request.setAttribute("errorTest","L'aggiunta della postazione va a buon fine");
				session.setAttribute("error-type", null);
				session.setAttribute("error",  null);
				
				PostazioneBean postazione = new PostazioneBean();
				postazione.setCategoria(nomeCategoria);
				postazioneDAO.doSave(postazione);
			}
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		try {
			doGet(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
