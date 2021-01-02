package control.gestionePeriferica;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;
import model.servizio.Validatore;


@WebServlet(urlPatterns = {"/AggiungiPeriferica","/titolare/AggiungiPeriferica"})
public class AggiungiPeriferica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PerifericaDAO perifericaDAO = new PerifericaDAO();
	
    public AggiungiPeriferica() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PerifericaBean periferica = new PerifericaBean();
		HttpSession session = request.getSession();
		String nome = request.getParameter("nome");
		String tipo = request.getParameter("tipo");
		String quantita = request.getParameter("quantita");
		String prezzo = request.getParameter("prezzo");
		
		if(nome.length() == 0) {
			request.setAttribute("errorTest","L'aggiunta della periferica non va a buon fine poiché il campo nome è vuoto");
			session.setAttribute("error-type", "nome");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
		}
		else {
			if(nome.length() > 40) {
				request.setAttribute("errorTest","L'aggiunta della periferica non va a buon fine poiché il campo nome ha una lunghezza maggiore a 40");
				session.setAttribute("error-type", "tipoGenerico");
				session.setAttribute("error", "Lunghezza errata");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
			}
			else {
				if(tipo.length() == 0) {
					request.setAttribute("errorTest","L'aggiunta della periferica non va a buon fine poiché il campo tipo è vuoto");
					session.setAttribute("error-type", "nome");
					session.setAttribute("error", "Campo vuoto");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
				}
				else {
					if(tipo.length() > 30) {
						request.setAttribute("errorTest","L'aggiunta della periferica non va a buon fine poiché il campo tipo ha una lunghezza maggiore a 30");
						session.setAttribute("error-type", "tipoGenerico");
						session.setAttribute("error", "Lunghezza errata");
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
					}
					else {
						if(quantita.length() == 0) {
							request.setAttribute("errorTest","L'aggiunta della periferica non va a buon fine poiché il campo quantità è vuoto");
							session.setAttribute("error-type", "nome");
							session.setAttribute("error", "Campo vuoto");
							response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
						}
						else {
							if(!Validatore.validaQuantita(quantita)) {
								request.setAttribute("errorTest","L'aggiunta della periferica non va a buon fine poiché il campo quantità non rispetta il formato");
								session.setAttribute("error-type", "prezzo");
								session.setAttribute("error", "Formato errato");
								response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
							}
							else {
								if(prezzo.length() == 0) {
									request.setAttribute("errorTest","L'aggiunta della periferica non va a buon fine poiché il campo prezzo è vuoto");
									session.setAttribute("error-type", "nome");
									session.setAttribute("error", "Campo vuoto");
									response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
								}
								else {
									if(!Validatore.validaPrezzo(prezzo)) {
										request.setAttribute("errorTest","L'aggiunta della periferica non va a buon fine poiché il campo prezzo non rispetta il formato");
										session.setAttribute("error-type", "prezzo");
										session.setAttribute("error", "Formato errato");
										response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
									}
									else {
										request.setAttribute("errorTest","L'aggiunta della periferica va a buon fine");
										session.setAttribute("error-type", null);
										session.setAttribute("error", null);
										
										periferica.setNome(nome);
										periferica.setTipo(tipo);
										periferica.setQuantita(Integer.parseInt(quantita));
										periferica.setPrezzo(Float.parseFloat(prezzo));
										
										perifericaDAO.doSave(periferica);
									}
								}
							}
						}
					}
				}
			}
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			doGet(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
