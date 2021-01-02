package test.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.gestioneUtente.NuovaPassword;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;


class TestCase_CreazioneNuovaPassword extends Mockito {
	
	private NuovaPassword servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private UtenteDAO dao;
	private UtenteBean utente=new UtenteBean("test@funisa.com","Test","Test","Test",Ruolo.cliente,true,"ABCD123","Test01?");
	


	@BeforeEach
	void setUp() throws Exception {
		dao=new UtenteDAO();
		dao.doDelete(utente.getEmail());
		dao.doSave(utente);
		servlet = new NuovaPassword();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		
	}
	
	@Test
	void TC_CreazioneNuovaPassword_1() {
		request.addParameter("password","");
		request.addParameter("confermaPassword","Password01?");
		request.getSession().setAttribute("emailCodice",utente.getEmail());
		String message = "La creazione della nuova password non va a buon fine poich� il campo password � vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_CreazioneNuovaPassword_2() {
		request.addParameter("password","ciao");
		request.addParameter("confermaPassword","ciao");
		request.getSession().setAttribute("emailCodice",utente.getEmail());
		String message = "La creazione della nuova password non va a buon fine poich� il campo password non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_CreazioneNuovaPassword_3() {
		request.addParameter("password","Password01?");
		request.addParameter("confermaPassword","");
		request.getSession().setAttribute("emailCodice",utente.getEmail());
		String message = "La creazione della nuova password non va a buon fine poich� il campo conferma password � vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_CreazioneNuovaPassword_4() {
		request.addParameter("password","Password01?");
		request.addParameter("confermaPassword","Password0?");
		request.getSession().setAttribute("emailCodice",utente.getEmail());
		String message = "La creazione della nuova password non va a buon fine  poich� il campo password e il campo conferma password non corrispondono";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_CreazioneNuovaPassword_5() {
		request.addParameter("password","Password01?");
		request.addParameter("confermaPassword","Password01?");
		request.getSession().setAttribute("emailCodice",utente.getEmail());
		String message = "La creazione della nuova password va a buon fine";
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
