package control.gestionePrenotazione;

import java.io.IOException;
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

/**
 * Servlet implementation class PrenotaIntermedia
 */
@WebServlet("/PrenotaIntermedia")
public class PrenotaIntermedia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
	private PostazioneDAO postazioneDAO = new PostazioneDAO();
	private PerifericaDAO perifericaDAO = new PerifericaDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrenotaIntermedia() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/cliente/confermaRegistrazione.jsp"));
		}
		else {
			CategoriaBean categoria = (CategoriaBean) request.getSession().getAttribute("categoria");
			ArrayList<PerifericaBean> periferiche = getPerifericheFromForm(request);

			PrenotazioneBean prenotazione = new PrenotazioneBean();
			String data = (String) session.getAttribute("data");
			String fasciaOraria = (String) session.getAttribute("fasciaOraria");

			prenotazione.setData(data);
			prenotazione.setFasciaOraria(fasciaOraria);
			prenotazione.setUtenteEmail(utente.getEmail());
			prenotazione.setPostazioneId(
			postazioneDAO.postazioneLiberaCategoria(categoria, data, fasciaOraria).getId());
			prenotazione.setPrezzo(calcolaPrezzo(categoria, periferiche));
			prenotazione.setQr("data:image/png;base64,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"); 
			// prenotazione con l'aggiunta di almeno di una periferica
			session.setAttribute("datiPrenotazione", prenotazione);
			session.setAttribute("datiPeriferiche",periferiche);
			session.setAttribute("datiCategorie",categoria);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/cliente/riepilogo.jsp"));
			
			
			
		
		}
		
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}

		/**
		 *  Prende le periferiche che l'utente ha selezionato
		 * @return ArrayList<PerifericaBean> contiene le periferiche selezionate
		 *         dall'utente
		 */
		private ArrayList<PerifericaBean> getPerifericheFromForm(HttpServletRequest request) {
			ArrayList<String> tipi = new ArrayList<String>();
			ArrayList<PerifericaBean> periferiche = new ArrayList<PerifericaBean>();

			tipi = (ArrayList<String>) perifericaDAO.doRetrieveAllTipi();

			for (String s : tipi) {
				PerifericaBean bean = perifericaDAO.doRetrieveByKey(request.getParameter(s)); 
				if(!bean.getNome().equals(""))
					periferiche.add(bean);
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
		 *  calcola il prezzo della prenotazione
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
