package control.gestioneSegnalazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.notifica.NotificaBean;
import model.notifica.NotificaDAO;
import model.segnalazione.SegnalazioneDAO;


@WebServlet("/EliminaSegnalazione")
public class EliminaSegnalazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
	private NotificaDAO notificaDAO = new NotificaDAO();
       
    public EliminaSegnalazione() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		notificaDAO.doSaveNotificaUtente(new NotificaBean("La tua segnalazione è stata risolta", ""), segnalazioneDAO.doRetrieveByKey(Integer.parseInt(request.getParameter("id"))).getUtenteEmail());
		segnalazioneDAO.doDelete(Integer.parseInt(request.getParameter("id")));
		
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
