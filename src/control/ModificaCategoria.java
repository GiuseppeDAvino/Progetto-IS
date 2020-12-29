package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.categoria.CategoriaBean;
import model.categoria.CategoriaDAO;
import model.servizio.ConvertitoreImmagine;
import model.servizio.Validatore;


@WebServlet(urlPatterns = {"/ModificaCategoria","/titolare/ModificaCategoria"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
// temporarily stored on disk
maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files

public class ModificaCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    public ModificaCategoria() {
        super();
    }

    
    /**
	 * Permette di modificare una categoria esistente prendendo dati dal form
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoriaBean categoria = new CategoriaBean();
		HttpSession session = request.getSession();
		String nome = request.getParameter("nome");
		String tipoGenerico = request.getParameter("tipoGenerico");
		String descrizione = request.getParameter("descrizione");
		String prezzo = request.getParameter("prezzo");
		String immagine = null;

		if (request.getPart("immagine") == null) {
			request.setAttribute("errorTest",
					"La modifica della categoria non va a buon fine poichè il campo immagine è vuoto");
			session.setAttribute("error-type", "immagine");
			session.setAttribute("error", "Campo vuoto");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
		} else {
			immagine = ConvertitoreImmagine.converti(request.getPart("immagine"));

			if (nome.length() == 0) {
				request.setAttribute("errorTest",
						"La modifica della categoria non va a buon fine poichè il campo nome è vuoto");
				session.setAttribute("error-type", "nome");
				session.setAttribute("error", "Campo vuoto");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
			} else {
				if (nome.length() > 40) {
					request.setAttribute("errorTest",
							"La modifica della categoria non va a buon fine poichè il campo nome ha una lunghezza maggiore a 40");
					session.setAttribute("error-type", "nome");
					session.setAttribute("error", "Lunghezza errata");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
				} else {
					if (tipoGenerico.length() == 0) {
						request.setAttribute("errorTest",
								"La modifica della categoria non va a buon fine poichè il campo tipoGenerico è vuoto");
						session.setAttribute("error-type", "tipoGenerico");
						session.setAttribute("error", "Campo vuoto");
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
					}

					else {
						if (tipoGenerico.length() > 30) {
							request.setAttribute("errorTest",
									"La modifica della categoria non va a buon fine poichè il campo tipoGenerico ha una lunghezza maggiore a 30");
							session.setAttribute("error-type", "tipoGenerico");
							session.setAttribute("error", "Lunghezza errata");
							response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
						} else {
							if (descrizione.length() == 0) {
								request.setAttribute("errorTest",
										"La modifica della categoria non va a buon fine poichè il campo descrizione è vuoto");
								session.setAttribute("error-type", "descrizione");
								session.setAttribute("error", "Campo vuoto");
								response.sendRedirect(
										response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
							} else {
								if (descrizione.length() > 100) {
									request.setAttribute("errorTest",
											"La modifica della categoria non va a buon fine poichè il campo descrizione ha una lunghezza maggiore di 100");
									session.setAttribute("error-type", "descrizione");
									session.setAttribute("error", "Lunghezza errata");
									response.sendRedirect(
											response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
								} else {
									if (prezzo.length() == 0) {
										request.setAttribute("errorTest",
												"La modifica della categoria non va a buon fine poichè il campo prezzo è vuoto");
										session.setAttribute("error-type", "prezzo");
										session.setAttribute("error", "Campo vuoto");
										response.sendRedirect(
												response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
									}

									else {
										if (!Validatore.validaPrezzo(prezzo)) {
											request.setAttribute("errorTest",
													"La modifica della categoria non va a buon fine poichè il campo prezzo non rispetta il formato");
											session.setAttribute("error-type", "prezzo");
											session.setAttribute("error", "Formato errato");
											response.sendRedirect(
													response.encodeRedirectURL(request.getContextPath() + "/user.jsp"));
										} else {
											request.setAttribute("errorTest",
													"La modifica della categoria va a buon fine");
											session.setAttribute("error-type", null);
											session.setAttribute("error", null);

											categoria.setNome(nome);
											categoria.setTipoGenerico(tipoGenerico);
											categoria.setDescrizione(descrizione);
											categoria.setPrezzo(Float.parseFloat(prezzo));
											categoria.setImmagine(immagine);

											categoriaDAO.doUpdate(categoria, categoria.getNome());
										}
									}
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
