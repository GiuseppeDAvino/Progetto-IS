package test.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.ConfermaRecuperoPassword;
import control.RichiestaRecuperoPassword;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;

class TC_CodiceRecPassword extends Mockito {
	private ConfermaRecuperoPassword servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private UtenteDAO dao;
	private UtenteBean utente=new UtenteBean("test@funisa.com","Test","Test","Test",Ruolo.cliente,true,"ABCD123","Test01?");

	@BeforeEach
	void setUp() throws Exception {
		servlet = new ConfermaRecuperoPassword();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		dao=new UtenteDAO();
		dao.doSave(utente);
	}

	

	@Test
	void TC_RichiestaRecPassword_1() {
		request.addParameter("codiceVerifica","");
		String message = "Inserimento del codice di verifica non va a buon fine poichè il campo codice è vuoto";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	void TC_RichiestaRecPassword_2() {
		request.addParameter("codiceVerifica","123");
		request.getSession().setAttribute("emailCodice",utente.getEmail());
		String message = "Inserimento del codice di verifica non va a buon fine poichè il codice non è associato alla sua email";
		servlet.doPost(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	@Test
	void TC_RichiestaRecPassword_3() {
		request.addParameter("codiceVerifica",utente.getCodiceVerifica());
		request.getSession().setAttribute("emailCodice",utente.getEmail());
		String message = "Inserimento del codice di verifica è avvenuto con successo e si può procedere con la creazione di una nuova password";
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
