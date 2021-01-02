package test.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.gestioneRecensione.AggiungiRecensione;
import control.gestioneSegnalazione.AggiungiSegnalazione;
import model.notifica.NotificaDAO;
import model.recensione.RecensioneDAO;
import model.segnalazione.SegnalazioneDAO;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;

class TestCase_AggiuntaSegnalazione extends Mockito {
	private AggiungiSegnalazione servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private SegnalazioneDAO segnalazioneDAO;
	private UtenteBean utente = new UtenteBean("test@funisa.com","Test","Test","Test01",Ruolo.cliente,true,"","Test01?");
	private UtenteDAO utenteDAO;
	private NotificaDAO notificaDAO;
	@BeforeEach
	void setUp() throws Exception {
		servlet = new AggiungiSegnalazione();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		segnalazioneDAO=new SegnalazioneDAO();
		utenteDAO=new UtenteDAO();
		utenteDAO.doSave(utente);
		notificaDAO = new NotificaDAO();
	}
	
	@Test
	void TC_AggiuntaSegnalazione_1() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("descrizione", "");
		request.addParameter("tipo", "Periferica rotta");
		String message = "L'aggiunta della segnalazione non va a buon fine poiché il campo descrizione è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaSegnalazione_2() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("descrizione", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and");
		request.addParameter("tipo", "Periferica rotta");
		String message = "L'aggiunta della segnalazione non va a buon fine poiché il campo descrizione ha una lunghezza maggiore di 200";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaSegnalazione_3() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("descrizione", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s");
		request.addParameter("tipo", "");
		String message = "L'aggiunta della segnalazione non va a buon fine poiché il campo tipo è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaSegnalazione_4() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("descrizione", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s");
		request.addParameter("tipo", "Periferica rotta and typesetting industry");
		String message = "L'aggiunta della segnalazione non va a buon fine poiché il campo tipo ha una lunghezza maggiore di 30";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaSegnalazione_5() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("descrizione", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. ");
		request.addParameter("tipo", "Periferica rotta");
		String message = "L'aggiunta della segnalazione va a buon fine";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		utenteDAO.doDelete(utente.getEmail());
		segnalazioneDAO.doDeleteByEmail(utente.getEmail());
		notificaDAO.doDeleteUltimaNotifica();
		servlet=null;
		utenteDAO=null;
		segnalazioneDAO=null;
		request=null;
		response=null;
	}


}