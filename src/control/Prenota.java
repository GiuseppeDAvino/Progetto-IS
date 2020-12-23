package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.categoria.CategoriaBean;
import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;
import model.postazione.PostazioneDAO;
import model.prenotazione.PrenotazioneBean;
import model.prenotazione.PrenotazioneDAO;
import model.utente.UtenteBean;

@WebServlet(urlPatterns = { "/Prenota", "/cliente/Prenota" })
public class Prenota extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
	private PostazioneDAO postazioneDAO = new PostazioneDAO();
	private PerifericaDAO perifericaDAO = new PerifericaDAO();

	public Prenota() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
		if (!isInSessionUtente(utente))
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Login.jsp"));
		else if (!isCliente(utente))
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Index.jsp"));

		else {
			CategoriaBean categoria = (CategoriaBean) request.getSession().getAttribute("categoria");
			ArrayList<PerifericaBean> periferiche = getPerifericheFromForm(request);

			PrenotazioneBean prenotazione = new PrenotazioneBean();
			String data = (String) request.getSession().getAttribute("data");
			String fasciaOraria = (String) request.getSession().getAttribute("fasciaOraria");

			try {
				prenotazione.setData(data);
				prenotazione.setFasciaOraria(fasciaOraria);
				prenotazione.setUtenteEmail(utente.getEmail());
				prenotazione.setPostazioneId(
						postazioneDAO.postazioneLiberaCategoria(categoria, data, fasciaOraria).getId());
				prenotazione.setPrezzo(calcolaPrezzo(categoria, periferiche));
				prenotazione.setQr(""); // TODO gestire il QRcode
				System.out.println("PROVA PRENOTAZIONE" + prenotazione.getUtenteEmail());
				// prenotazione con l'aggiunta di almeno di una periferica
				// prenotazioneDAO.prenotaConPeriferiche(prenotazione, periferiche);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cliente/prova.jsp"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @category Prende le periferiche che l'utente ha selezionato
	 * @return ArrayList<PerifericaBean> contiene le periferiche selezionate
	 *         dall'utente
	 */
	private ArrayList<PerifericaBean> getPerifericheFromForm(HttpServletRequest request) {
		ArrayList<String> tipi = new ArrayList<String>();
		ArrayList<PerifericaBean> periferiche = new ArrayList<PerifericaBean>();

		try {
			tipi = (ArrayList<String>) perifericaDAO.doRetrieveAllTipi();

			for (String s : tipi) {
				periferiche.add(perifericaDAO.doRetrieveByKey(request.getParameter(s)));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return periferiche;
	}

	private boolean isInSessionUtente(UtenteBean utente) {
		if (utente == null) {
			return false;
		}
		return true;
	}

	private boolean isCliente(UtenteBean utente) {
		if (utente.getRuolo().name().equals("cliente")) {
			return true;
		}
		return false;
	}
	/**
	 * @category calcola il prezzo della prenotazione
	 * @param categoria categoria della postazione da prenotare
	 * @param periferiche periferiche da prenotare
	 * */
	private float calcolaPrezzo(CategoriaBean categoria, ArrayList<PerifericaBean> periferiche) {
		float tot = 0.0f;
		tot += categoria.getPrezzo();
		for (PerifericaBean perifericaBean : periferiche) {
			tot += perifericaBean.getPrezzo();
		}
		return tot;
	}
}
