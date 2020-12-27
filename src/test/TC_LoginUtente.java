package test;

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

import control.Login;

public class TC_LoginUtente extends Mockito {
	private Login servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	@BeforeEach
	public void setUp() {
		servlet = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	
	@Test
	public void TC_LoginUtente_1() throws ServletException, IOException {
		request.addParameter("email", "email");
		request.addParameter("password", "Password01!");
		request.addParameter("test", "true");
		String message = "Email non presente nel database";
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exception.getMessage());
	}
	
	
	@Test
	public void TC_LoginUtente_3() throws ServletException, IOException {
		request.addParameter("email", "email");
		request.addParameter("password", "Password01!");
		String message = "Email non presente nel database";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	
	@Test
	public void TC_LoginUtente_2() throws ServletException, IOException {
		request.addParameter("email", "titolare@titolare.com");
		request.addParameter("password", "titolare");
		String message = "OK";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	} 
	
	
	  @AfterEach
	  public void tearDown() {
	     servlet = null;
	     request = null;    
	     response = null;
	   }
}