package test.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.ModificaDatiPersonali;

import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;

class TestCase_ModificaDatiPersonali extends Mockito{

	private ModificaDatiPersonali servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private UtenteDAO dao;
	private UtenteBean utente=new UtenteBean("test@funisa.com","Test","Test","Test",Ruolo.cliente,true,"ABCD123","MarioRossi01?");

	@BeforeEach
	void setUp() throws Exception {
		servlet = new ModificaDatiPersonali();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		dao=new UtenteDAO();
		dao.doSave(utente);
	}
	
	@Test
	void TC_ModificaDatiPersonali_1() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("nome", "");
		request.addParameter("cognome","Verdi");
		request.addParameter("username","BrunoVerdi1");
		String message = "La modifica non va a buon fine poiché il campo nome è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaDatiPersonali_2() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("nome", "Bruno1");
		request.addParameter("cognome","Verdi");
		request.addParameter("username","BrunoVerdi1");
		String message = "La modifica non va a buon fine poiché il campo nome non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaDatiPersonali_3() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("nome", "Bruno");
		request.addParameter("cognome","");
		request.addParameter("username","BrunoVerdi1");
		String message = "La modifica non va a buon fine poiché il campo cognome è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaDatiPersonali_4() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("nome", "Bruno");
		request.addParameter("cognome","Verdi1");
		request.addParameter("username","BrunoVerdi1");
		String message = "La modifica non va a buon fine poiché il campo cognome non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaDatiPersonali_5() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("nome", "Bruno");
		request.addParameter("cognome","Verdi");
		request.addParameter("username","");
		String message = "La modifica non va a buon fine poiché il campo username è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaDatiPersonali_6() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("nome", "Bruno");
		request.addParameter("cognome","Verdi");
		request.addParameter("username","BrunoVerdi01!");
		String message = "La modifica non va a buon fine poiché il campo username non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaDatiPersonali_7() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("nome", "Bruno");
		request.addParameter("cognome","Verdi");
		request.addParameter("username","Test");
		String message = "La modifica non va a buon fine poiché l'username è già presente nel database";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaDatiPersonali_8() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("nome", "Bruno");
		request.addParameter("cognome","Verdi");
		request.addParameter("username","BrunoVerdi1");
		String message = "La modifica va a buon fine";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		dao.doDelete(utente.getEmail());
		servlet=null;
		dao=null;
		request=null;
		response=null;
	}
	
	
}
