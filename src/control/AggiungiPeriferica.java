package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;


@WebServlet(urlPatterns = {"/AggiungiPeriferica","/titolare/AggiungiPeriferica"})
public class AggiungiPeriferica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PerifericaDAO perifericaDAO = new PerifericaDAO();
	
    public AggiungiPeriferica() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PerifericaBean periferica = new PerifericaBean();
		periferica.setNome(request.getParameter("nome"));
		periferica.setTipo(request.getParameter("tipo"));
		periferica.setQuantita(Integer.parseInt(request.getParameter("quantita")));
		periferica.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
		
		perifericaDAO.doSave(periferica);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
