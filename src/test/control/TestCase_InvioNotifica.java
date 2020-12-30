package test.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.InviaNotifica;
import model.notifica.NotificaDAO;

import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;

class TestCase_InvioNotifica extends Mockito {
	
	private InviaNotifica servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private UtenteBean utente1 = new UtenteBean("test1@funisa.com","Test","Test","Test01",Ruolo.cliente,true,"","Test01?");
	private UtenteBean utente2 = new UtenteBean("test2@funisa.com","Test","Test","Test02",Ruolo.cliente,true,"","Test01?");
	private UtenteDAO utenteDAO;
	private static NotificaDAO notificaDAO;
	
	@BeforeEach
	void setUp() throws Exception {
		servlet = new InviaNotifica();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		utenteDAO= mock(UtenteDAO.class);
		utenteDAO.doSave(utente1);
		utenteDAO.doSave(utente2);
		notificaDAO = mock(NotificaDAO.class);
	}
	
	@Test
	void TC_InvioNotifica_1() {
		request.addParameter("descrizione", "");
		String message = "L'invio della notifica non va a buon fine poiché il campo descrizione è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_InvioNotifica_2() {
		request.addParameter("descrizione", "IL CENTRO RESTERA' CHIUSO IN DATA 25/12/2020 IL CENTRO RESTERA' CHIUSO IN DATA 25/12/2020 IL CENTRO RESTERA' CHIUSO IN DATA 25/12/2020 IL CENTRO RESTERA' CHIUSO IN DATA 25/12/2020 IL CENTRO RESTERA' CHIUSO IN DATA 25/12/2020");
		String message = "L'invio della notifica non va a buon fine poiché il campo descrizione ha una lunghezza maggiore di 200";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_InvioNotifica_3() {
		servlet.setMock(notificaDAO);
		request.addParameter("descrizione", "IL CENTRO RESTERA' CHIUSO IN DATA 25/12/2020");
		String message = "L'invio della notifica va a buon fine";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		servlet=null;
		utenteDAO=null;
		request=null;
		response=null;
	}

	
	
	
	
}
