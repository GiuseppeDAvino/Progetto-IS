package test.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.AggiungiRecensione;
import model.notifica.NotificaDAO;
import model.recensione.RecensioneDAO;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;

class TC_AggiuntaRecensione extends Mockito {
	private AggiungiRecensione servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private RecensioneDAO recensioneDAO;
	private UtenteBean utente = new UtenteBean("test@funisa.com","Test","Test","Test01",Ruolo.cliente,true,"","Test01?");
	private UtenteDAO utenteDAO;
	private NotificaDAO notificaDAO;

	@BeforeEach
	void setUp() throws Exception {
		servlet = new AggiungiRecensione();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		recensioneDAO=new RecensioneDAO();
		notificaDAO=new NotificaDAO();
		utenteDAO=new UtenteDAO();
		utenteDAO.doSave(utente);
	}
	
	@Test
	void TC_AggiuntaRecensione_1() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("descrizione", "");
		request.addParameter("valutazione", "4");
		String message = "L'aggiunta della recensione non va a buon fine poiché il campo descrizione è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaRecensione_2() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("descrizione", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and");
		request.addParameter("valutazione", "4");
		String message = "L'aggiunta della recensione non va a buon fine poiché il campo descrizione ha una lunghezza maggiore di 200";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaRecensione_3() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("descrizione", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s");
		request.addParameter("valutazione", "");
		String message = "L'aggiunta della recensione non va a buon fine poiché il campo valutazione è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaRecensione_4() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("descrizione", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s");
		request.addParameter("valutazione", "4");
		String message = "L'aggiunta della recensione va a buon fine";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		utenteDAO.doDelete(utente.getEmail());
		recensioneDAO.doDelete(utente.getEmail());
		notificaDAO.doDeleteUltimaNotifica();
		servlet=null;
		utenteDAO=null;
		recensioneDAO=null;
		request=null;
		response=null;
	}


}
