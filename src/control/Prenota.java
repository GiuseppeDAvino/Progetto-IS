package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession(false);
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		if(!isInSessionUtente(utente)) {
			session.setAttribute("isPressedPrenota", 1);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp")); }
		else if(!isCliente(utente)) 
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));
		else if(utente.isStato() == false) {
			session.setAttribute("isPressedPrenota", 1);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/confermaRegistrazione.jsp"));
		}
		else {
			CategoriaBean categoria = (CategoriaBean) request.getSession().getAttribute("categoria");
			ArrayList<PerifericaBean> periferiche = getPerifericheFromForm(request);

			PrenotazioneBean prenotazione = new PrenotazioneBean();
			String data = (String) session.getAttribute("data");
			String fasciaOraria = (String) session.getAttribute("fasciaOraria");

			try {
				prenotazione.setData(data);
				prenotazione.setFasciaOraria(fasciaOraria);
				prenotazione.setUtenteEmail(utente.getEmail());
				prenotazione.setPostazioneId(
				postazioneDAO.postazioneLiberaCategoria(categoria, data, fasciaOraria).getId());
				prenotazione.setPrezzo(calcolaPrezzo(categoria, periferiche));
				System.out.println(prenotazione.getPrezzo());
				prenotazione.setQr(""); // TODO gestire il QRcode
				// prenotazione con l'aggiunta di almeno di una periferica
				prenotazioneDAO.prenotaConPeriferiche(prenotazione, periferiche);
				ArrayList<PrenotazioneBean> prenotazioni = (ArrayList<PrenotazioneBean>) prenotazioneDAO.doRetrieveAll();
				PrenotazioneBean bean = prenotazioni.get(prenotazioni.size() - 1);
				request.getSession().setAttribute("prenotazione",bean);
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

		tipi = (ArrayList<String>) perifericaDAO.doRetrieveAllTipi();

		for (String s : tipi) {
			periferiche.add(perifericaDAO.doRetrieveByKey(request.getParameter(s)));
			System.out.println("request " + request.getParameter(s));
		}
		
		for(PerifericaBean p: periferiche)
			System.out.println("periferica " + p);
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
