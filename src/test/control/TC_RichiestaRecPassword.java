package test.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.Registrazione;
import control.RichiestaRecuperoPassword;
import model.utente.UtenteDAO;

class TC_RichiestaRecPassword {
	private RichiestaRecuperoPassword servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private UtenteDAO dao;
	@BeforeEach
	void setUp() throws Exception {
		servlet = new RichiestaRecuperoPassword();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		dao=new UtenteDAO();
	}

	@Test
	void TC_RichiestaRecPassword_1() {
		request.addParameter("email", "");
		String message = "La richiesta per il recupero password non va a buon fine poiché il campo email è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	void TC_RichiestaRecPassword_2() {
		request.addParameter("email", "@funisa.com");
		String message = "La richiesta per il recupero password non va a buon fine poiché il campo email non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	void TC_RichiestaRecPassword_3() {
		request.addParameter("email", "mario@funisa.com");
		String message = "La richiesta per il recupero password non va a buon fine poiché il campo email non esiste nel database";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	void TC_RichiestaRecPassword_4() {
		request.addParameter("email", "mariorossi@funisa.com");
		String message = "La richiesta per il recupero password va a buon fine e si può procedere con il recupero della password";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}

	@AfterEach
	void tearDown() throws Exception {
		servlet=null;
		request=null;
		response=null;
		dao.cambiaCodice("", "mariorossi@funisa.com");
		dao=null;
	}

}
