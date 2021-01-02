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

import control.gestionePeriferica.AggiungiPeriferica;
import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;

class TestCase_AggiuntaPeriferica extends Mockito{
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	@Mock
	private PerifericaDAO dao;
	
	@InjectMocks
	private AggiungiPeriferica servlet;
	
	private PerifericaBean periferica = new PerifericaBean("GLORIOUS MODEL D","MOUSE",5,5);
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	void TC_AggiuntaPeriferica_1() {
		request.addParameter("nome","");
		request.addParameter("tipo",periferica.getTipo());
		request.addParameter("quantita","5");
		request.addParameter("prezzo","5");
		String message = "L'aggiunta della periferica non va a buon fine poiché il campo nome è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaPeriferica_2() {
		request.addParameter("nome","GLORIOUS MODEL D GLORIOUS MODEL D GLORIOUS MODEL D");
		request.addParameter("tipo",periferica.getTipo());
		request.addParameter("quantita","5");
		request.addParameter("prezzo","5");
		String message = "L'aggiunta della periferica non va a buon fine poiché il campo nome ha una lunghezza maggiore a 40";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaPeriferica_3() {
		request.addParameter("nome",periferica.getNome());
		request.addParameter("tipo","");
		request.addParameter("quantita","5");
		request.addParameter("prezzo","5");
		String message = "L'aggiunta della periferica non va a buon fine poiché il campo tipo è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaPeriferica_4() {
		request.addParameter("nome",periferica.getNome());
		request.addParameter("tipo","MOUSE MOUSE MOUSE MOUSE MOUSE MOUSE");
		request.addParameter("quantita","5");
		request.addParameter("prezzo","5");
		String message = "L'aggiunta della periferica non va a buon fine poiché il campo tipo ha una lunghezza maggiore a 30";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaPeriferica_5() {
		request.addParameter("nome",periferica.getNome());
		request.addParameter("tipo",periferica.getTipo());
		request.addParameter("quantita","");
		request.addParameter("prezzo","5");
		String message = "L'aggiunta della periferica non va a buon fine poiché il campo quantità è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaPeriferica_6() {
		request.addParameter("nome",periferica.getNome());
		request.addParameter("tipo",periferica.getTipo());
		request.addParameter("quantita","5u");
		request.addParameter("prezzo","5");
		String message = "L'aggiunta della periferica non va a buon fine poiché il campo quantità non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}

	@Test
	void TC_AggiuntaPeriferica_7() {
		request.addParameter("nome",periferica.getNome());
		request.addParameter("tipo",periferica.getTipo());
		request.addParameter("quantita","5");
		request.addParameter("prezzo","");
		String message = "L'aggiunta della periferica non va a buon fine poiché il campo prezzo è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaPeriferica_8() {
		request.addParameter("nome",periferica.getNome());
		request.addParameter("tipo",periferica.getTipo());
		request.addParameter("quantita","5");
		request.addParameter("prezzo","-10euro");
		String message = "L'aggiunta della periferica non va a buon fine poiché il campo prezzo non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_AggiuntaPeriferica_9() {
		request.addParameter("nome",periferica.getNome());
		request.addParameter("tipo",periferica.getTipo());
		request.addParameter("quantita","5");
		request.addParameter("prezzo","10");
		String message = "L'aggiunta della periferica va a buon fine";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		request=null;
		response=null;
	}
	
	

}
