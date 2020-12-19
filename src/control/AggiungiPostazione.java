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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PostazioneBean postazione = new PostazioneBean();
		postazione.setCategoria(request.getParameter("categoria"));
		try {
			postazioneDAO.doSave(postazione);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// TODO add redirect
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
