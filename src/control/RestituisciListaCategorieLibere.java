package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.categoria.CategoriaBean;
import model.categoria.CategoriaDAO;
import model.servizio.Validatore;


@WebServlet("/RestituisciListaCategorieLibere")
public class RestituisciListaCategorieLibere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private Gson gson = new Gson();
	
    public RestituisciListaCategorieLibere() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String data = request.getParameter("data");

		String fasciaOraria = request.getParameter("fasciaOraria");
		String tipoGenerico = request.getParameter("tipoGenerico");
		if(data.length() == 0) {
			request.setAttribute("errorTest","La ricerca non va a buon fine poich� il campo data � vuoto");
			session.setAttribute("error-type", "data");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/provaListaCategorieLibere.jsp"));
		}
		else {
			if(fasciaOraria.length() == 0) {
				request.setAttribute("errorTest","La ricerca non va a buon fine poich� il campo fascia oraria � vuoto");
				session.setAttribute("error-type", "fasciaOraria");
				session.setAttribute("error", "Campo vuoto");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/provaListaCategorieLibere.jsp"));
			}
			else {
				if(tipoGenerico.length() == 0) {
					request.setAttribute("errorTest","La ricerca non va a buon fine poich� il campo tipo postazione � vuoto");
					session.setAttribute("error-type", "tipoGenerico");
					session.setAttribute("error", "Campo vuoto");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/provaListaCategorieLibere.jsp"));
				}
				else {
					if(!Validatore.validaData(data)) {
						
						request.setAttribute("errorTest","La ricerca non va a buon fine poich� il campo data non rispetta il formato");
						session.setAttribute("error-type", "data");
						session.setAttribute("error", "Formato non valido");
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/provaListaCategorieLibere.jsp"));
					}
					else {
						if(Validatore.isDataAntecedente(data)) {
							request.setAttribute("errorTest","La ricerca non va a buon fine poich� la data inserita � antecedente alla data corrente");
							session.setAttribute("error-type", "data");
							session.setAttribute("error", "Data antecedente");
							response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/provaListaCategorieLibere.jsp"));
						}
						else {
							if(!Validatore.isFasciaOrariaValid(fasciaOraria)) {
								request.setAttribute("errorTest","La ricerca non va a buon fine poich� la fascia oraria inserita non esiste");
								session.setAttribute("error-type", "fasciaOraria");
								session.setAttribute("error", "formato non valido");
								response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/provaListaCategorieLibere.jsp"));
							}
							else {
								if(!Validatore.isTipoValid(tipoGenerico)) {
									request.setAttribute("errorTest","La ricerca non va a buon fine poich� il campo tipo postazione non � presente nel database");
									session.setAttribute("error-type", "fasciaOraria");
									session.setAttribute("error", "formato non valido");
									response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/provaListaCategorieLibere.jsp"));
								}
								else {
									request.setAttribute("errorTest","La ricerca della postazione va a buon fine");
									session.setAttribute("error-type", null);
									session.setAttribute("error", null);
									ArrayList<CategoriaBean> categorie = (ArrayList<CategoriaBean>) categoriaDAO.categorieConPostazioniLibere(data, fasciaOraria, tipoGenerico);
									if(categorie.size()>0) {
										request.getSession().setAttribute("data", data);
										request.getSession().setAttribute("fasciaOraria", fasciaOraria);
									}
									String string = gson.toJson(categorie);
									response.getWriter().print(string);
									response.getWriter().flush();
									response.setStatus(200);
								}
								
							}
						}
					}
				}
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
