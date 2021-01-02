package test.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.gestionePostazione.AggiungiPostazione;
import model.postazione.PostazioneBean;
import model.postazione.PostazioneDAO;
import model.servizio.Validatore;

class TestCase_AggiuntaPostazione extends Mockito {
	
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	@Mock
	private PostazioneDAO dao;
	
	@InjectMocks
	private AggiungiPostazione servlet;
	private PostazioneBean postazione = new PostazioneBean("PC FASCIA ALTA");
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	
	@Test
	void TC_AggiuntaCategoria_1() {
		request.addParameter("nomeCategoria","");

		String message = "L'aggiunta della postazione non va a buon fine poich� il campo nome categoria � vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaCategoria_2() {
		request.addParameter("nomeCategoria","AUTO");

		String message = "L'aggiunta della postazione non va a buon fine poich� il campo nome categoria non � presente nel database";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaCategoria_3() {
		request.addParameter("nomeCategoria",postazione.getCategoria());
		String message = "L'aggiunta della postazione va a buon fine";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		servlet=null;
		dao=null;
		request=null;
		response=null;
	}

}
