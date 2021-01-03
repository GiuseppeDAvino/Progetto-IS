package control.gestioneUtente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.utente.UtenteBean;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
//temporarily stored on disk
maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files



@WebServlet("/PaginaUtente")
public class PaginaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public PaginaUtente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("utente") == null) {
			String url = response.encodeURL("login.jsp");
			response.sendRedirect(url);
		}
		else {
			String url = response.encodeURL("user.jsp");
			response.sendRedirect(url);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
