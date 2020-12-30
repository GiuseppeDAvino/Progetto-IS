package test.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPart;

import control.AggiungiPostazione;
import model.postazione.PostazioneBean;
import model.postazione.PostazioneDAO;

class TC_AggiuntaPostazione extends Mockito {
	
	private AggiungiPostazione servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private PostazioneDAO dao;
	private PostazioneBean postazione = new PostazioneBean("PC FASCIA ALTA");
	
	
	@BeforeEach
	void setUp() throws Exception {
		servlet = new AggiungiPostazione();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		dao = new PostazioneDAO();
	}
	
	@Test
	void TC_AggiuntaCategoria_1() {
		request.addParameter("nomeCategoria","");

		String message = "L'aggiunta della postazione non va a buon fine poichè il campo nome categoria è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaCategoria_2() {
		request.addParameter("nomeCategoria","AUTO");

		String message = "L'aggiunta della postazione non va a buon fine poichè il campo nome categoria non è presente nel database";
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
		dao.doDeleteByNomeCategoria(postazione.getCategoria());
		servlet=null;
		dao=null;
		request=null;
		response=null;
	}

}
