package Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.notifica.NotificaBean;
import model.notifica.NotificaDAO;
import model.utente.UtenteBean;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Test() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NotificaDAO notificaDAO=new NotificaDAO();
		try {ArrayList<UtenteBean> utenti=new ArrayList<UtenteBean>();
			UtenteBean u=new UtenteBean();
			u.setEmail("email@email.com");
			utenti.add(u);
			notificaDAO.doSaveNotificaUtente(new NotificaBean("PROVA","PROVATIPOletture"), utenti);
			notificaDAO.doDeleteNotificaUtente(4,u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
