package control;

import java.io.IOException;
import java.sql.SQLException;

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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostazioneBean postazione = new PostazioneBean();
		postazione.setId(Integer.parseInt(request.getParameter("id")));
		try {
			if(postazioneDAO.ËStataUtilizzata(postazione))
				postazioneDAO.cambiaDisponibilit‡(postazione);
			else
				postazioneDAO.doDelete(postazione.getId());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		//TODO add redirect
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
