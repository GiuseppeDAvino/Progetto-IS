package test.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import control.RestituisciListaCategorieLibere;

class TestCase_FiltraPostazione extends Mockito{
	private RestituisciListaCategorieLibere servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	
	@BeforeEach
	void setUp() throws Exception {
		servlet = new RestituisciListaCategorieLibere();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		
	}
	
	@Test
	void TC_FiltraPostazione_1() {
		request.addParameter("data","");
		request.addParameter("fasciaOraria","16/18");
		request.addParameter("tipoGenerico","PC");
		String message = "La ricerca non va a buon fine poiché il campo data è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_FiltraPostazione_2() {
		request.addParameter("data","21-1-6");
		request.addParameter("fasciaOraria","16/18");
		request.addParameter("tipoGenerico","PC");
		String message = "La ricerca non va a buon fine poiché il campo data non rispetta il formato";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_FiltraPostazione_3() {
		request.addParameter("data","1999-01-06");
		request.addParameter("fasciaOraria","16/18");
		request.addParameter("tipoGenerico","PC");
		String message = "La ricerca non va a buon fine poiché la data inserita è antecedente alla data corrente";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_FiltraPostazione_4() {
		request.addParameter("data","2021-01-15");
		request.addParameter("fasciaOraria","");
		request.addParameter("tipoGenerico","PC");
		String message = "La ricerca non va a buon fine poiché il campo fascia oraria è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_FiltraPostazione_5() {
		request.addParameter("data","2021-01-15");
		request.addParameter("fasciaOraria","22/24");
		request.addParameter("tipoGenerico","PC");
		String message = "La ricerca non va a buon fine poiché la fascia oraria inserita non esiste";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_FiltraPostazione_6() {
		request.addParameter("data","2021-01-15");
		request.addParameter("fasciaOraria","16/18");
		request.addParameter("tipoGenerico","");
		String message = "La ricerca non va a buon fine poiché il campo tipo postazione è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_FiltraPostazione_7() {
		request.addParameter("data","2021-01-15");
		request.addParameter("fasciaOraria","16/18");
		request.addParameter("tipoGenerico","AUTO");
		String message = "La ricerca non va a buon fine poiché il campo tipo postazione non è presente nel database";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void TC_FiltraPostazione_8() {
		request.addParameter("data","2021-01-15");
		request.addParameter("fasciaOraria","16/18");
		request.addParameter("tipoGenerico","PC");
		String message = "La ricerca della postazione va a buon fine";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		servlet=null;
		request=null;
		response=null;
	}
}
