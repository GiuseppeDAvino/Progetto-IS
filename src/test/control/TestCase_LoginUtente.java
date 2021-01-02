package test.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.gestioneUtente.Login;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;

public class TestCase_LoginUtente extends Mockito {
	private Login servlet;
	private UtenteDAO dao = new UtenteDAO();
	private UtenteBean mario=new UtenteBean("mariorossi@funisa.com","Mario","Rossi","Mrossi01",Ruolo.cliente,true,"","MarioRossi01?");
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@BeforeEach
	public void setUp() {
		dao.doDelete(mario.getEmail());
		dao.doSave(mario);
		servlet = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void TC_LoginUtente_1() throws ServletException, IOException {
		request.addParameter("email", "");
		request.addParameter("password", "MarioRossi01?");
		String message = "Il login non va a buon fine poiché il campo email è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	public void TC_LoginUtente_2() throws ServletException, IOException {
		request.addParameter("email", "marior@funisa.com");
		request.addParameter("password", "MarioRossi01?");
		String message = "Il login non va a buon fine poiché l'email non è presente nel db";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_LoginUtente_3() throws ServletException, IOException {
		request.addParameter("email", "mariorossi@funisa.com");
		request.addParameter("password", "");
		String message = "Il login non va a buon fine poiché il campo password è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	public void TC_LoginUtente_4() throws ServletException, IOException {
		request.addParameter("email", "mariorossi@funisa.com");
		request.addParameter("password", "MarioRossi0?");
		String message = "Il login non va a buon fine poiché l'email e la password non combaciano";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	

	@Test
	public void TC_LoginUtente_5() throws ServletException, IOException {
		request.addParameter("email", "mariorossi@funisa.com");
		request.addParameter("password", "MarioRossi01?");
		String message = "Il login viene effettuato correttamente";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}

	@AfterEach
	public void tearDown() {
		dao.doDelete(mario.getEmail());
		servlet = null;
		request = null;
		response = null;
	}

	/*
	 * @Test public void TC_LoginUtente_1() throws ServletException, IOException {
	 * request.addParameter("email", "email"); request.addParameter("password",
	 * "Password01!"); request.addParameter("test", "true"); String message =
	 * "Email non presente nel database"; IllegalArgumentException exception =
	 * assertThrows(IllegalArgumentException.class, () -> { servlet.doPost(request,
	 * response); }); assertEquals(message, exception.getMessage()); }
	 */

}