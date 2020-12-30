package test.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.ModificaPassword;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;

class TestCase_ModificaInfAccesso extends Mockito {
	

	private ModificaPassword servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private UtenteDAO dao;
	private UtenteBean utente=new UtenteBean("test@funisa.com","Test","Test","Test",Ruolo.cliente,true,"ABCD123","MarioRossi01?");

	
	@BeforeEach
	void setUp() throws Exception {
		servlet = new ModificaPassword();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		dao=new UtenteDAO();
		dao.doSave(utente);
		
	}
	
	@Test
	void TC_ModificaInfAccesso_1() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("vecchiaPassword", "");
		request.addParameter("nuovaPassword","MarioRossi01!");
		request.addParameter("confermaPassword","MarioRossi01!");
		String message = "La modifica della password non va a buon fine poiché il campo vecchia password è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaInfAccesso_2() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("vecchiaPassword", "MarioRossi01");
		request.addParameter("nuovaPassword","MarioRossi01!");
		request.addParameter("confermaPassword","MarioRossi01!");
		String message = "La modifica della password non va a buon fine poiché il campo vecchia password è errata";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaInfAccesso_3() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("vecchiaPassword", "MarioRossi01?");
		request.addParameter("nuovaPassword","");
		request.addParameter("confermaPassword","MarioRossi01!");
		String message = "La modifica della password non va a buon fine poiché il campo nuova password è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaInfAccesso_4() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("vecchiaPassword", "MarioRossi01?");
		request.addParameter("nuovaPassword","mariorossi");
		request.addParameter("confermaPassword","mariorossi");
		String message = "La modifica della password non va a buon fine poiché il campo nuova password non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaInfAccesso_5() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("vecchiaPassword", "MarioRossi01?");
		request.addParameter("nuovaPassword","MarioRossi01!");
		request.addParameter("confermaPassword","");
		String message = "La modifica della password non va a buon fine poiché il campo conferma password è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaInfAccesso_6() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("vecchiaPassword", "MarioRossi01?");
		request.addParameter("nuovaPassword","MarioRossi01!");
		request.addParameter("confermaPassword","mARIOrOSSI01!");
		String message = "La modifica della password non va a buon fine poiché il campo nuova password e il campo conferma password non corrispondono";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_ModificaInfAccesso_7() {
		request.getSession().setAttribute("utente", utente);
		request.addParameter("vecchiaPassword", "MarioRossi01?");
		request.addParameter("nuovaPassword","MarioRossi01!");
		request.addParameter("confermaPassword","MarioRossi01!");
		String message = "La modifica della password va a buon fine";
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
