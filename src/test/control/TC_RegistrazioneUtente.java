package test.control;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.Registrazione;
import model.utente.UtenteDAO;

class TC_RegistrazioneUtente extends Mockito {
	private Registrazione servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private UtenteDAO dao;
	@BeforeEach
	public void setUp() {
		servlet = new Registrazione();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		dao=new UtenteDAO();
	}

	@Test
	public void TC_RegistrazioneUtente_1() throws ServletException, IOException {
		request.addParameter("nome", "");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "robertobianchi@funisa.com");
		request.addParameter("username", "Rbianchi01");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "RobertoBianchi01?");
		String message = "La registrazione non va a buon fine poichè il campo nome è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_2() throws ServletException, IOException {
		request.addParameter("nome", "Roberto1");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "robertobianchi@funisa.com");
		request.addParameter("username", "Rbianchi01");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "RobertoBianchi01?");
		String message = "La registrazione non va a buon fine poichè il campo nome non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_3() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "");
		request.addParameter("email", "robertobianchi@funisa.com");
		request.addParameter("username", "Rbianchi01");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "RobertoBianchi01?");
		String message = "La registrazione non va a buon fine poichè il campo cognome è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_4() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "Bianchi1");
		request.addParameter("email", "robertobianchi@funisa.com");
		request.addParameter("username", "Rbianchi01");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "RobertoBianchi01?");
		String message = "La registrazione non va a buon fine poichè il campo cognome non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_5() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "");
		request.addParameter("username", "Rbianchi01");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "RobertoBianchi01?");
		String message = "La registrazione non va a buon fine poichè il campo email è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_6() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "@funisa.com");
		request.addParameter("username", "Rbianchi01");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "RobertoBianchi01?");
		String message = "La registrazione non va a buon fine poichè il campo email non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_7() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "mariorossi@funisa.com");
		request.addParameter("username", "Rbianchi01");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "RobertoBianchi01?");
		String message = "La registrazione non va a buon fine poichè l'email è già presente nel database";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_8() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "robertobianchi@funisa.com");
		request.addParameter("username", "");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "RobertoBianchi01?");
		String message = "La registrazione non va a buon fine poichè il campo username è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_9() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "robertobianchi@funisa.com");
		request.addParameter("username", "R");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "RobertoBianchi01?");
		String message = "La registrazione non va a buon fine poichè il campo username non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_10() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "robertobianchi@funisa.com");
		request.addParameter("username", "Mrossi01");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "RobertoBianchi01?");
		String message = "La registrazione non va a buon fine poichè l'username è già presente nel database";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_11() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "robertobianchi@funisa.com");
		request.addParameter("username", "Rbianchi01");
		request.addParameter("password", "");
		request.addParameter("confermaPassword", "RobertoBianchi01?");
		String message = "La registrazione non va a buon fine poichè il campo password è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_12() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "robertobianchi@funisa.com");
		request.addParameter("username", "Rbianchi01");
		request.addParameter("password", "Roberto0");
		request.addParameter("confermaPassword", "Roberto0");
		String message = "La registrazione non va a buon fine poichè il campo password non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	public void TC_RegistrazioneUtente_13() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "robertobianchi@funisa.com");
		request.addParameter("username", "Rbianchi01");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "");
		String message = "La registrazione non va a buon fine poichè il campo conferma password è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_14() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "robertobianchi@funisa.com");
		request.addParameter("username", "Rbianchi01");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "RobertoBianchi0");
		String message = "La registrazione non va a buon fine poichè il campo password e il campo conferma password non corrispondono";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_RegistrazioneUtente_15() throws ServletException, IOException {
		request.addParameter("nome", "Roberto");
		request.addParameter("cognome", "Bianchi");
		request.addParameter("email", "robertobianchi@funisa.com");
		request.addParameter("username", "Rbianchi01");
		request.addParameter("password", "RobertoBianchi01?");
		request.addParameter("confermaPassword", "RobertoBianchi01?");
		String message = "La registrazione viene effettuata correttamente";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	

	@AfterEach
	public void tearDown() {
		servlet = null;
		request = null;
		response = null;
		dao.doDelete("robertobianchi@funisa.com");
		dao=null;
	}

}
