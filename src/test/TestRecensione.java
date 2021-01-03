package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.recensione.RecensioneBean;
import model.recensione.RecensioneDAO;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;

class TestRecensione {

	private UtenteDAO daoTest;
	private UtenteBean beanTest;
	
	private RecensioneDAO dao;
	private RecensioneBean recensione;
		
	private int b;
	@BeforeEach
	void setUp() throws Exception {
		daoTest = new UtenteDAO();
		dao = new RecensioneDAO();
		
		beanTest = new UtenteBean("test@test.com", "Test", "test", "TestTest", Ruolo.cliente, true, "", "test");
		daoTest.doDelete(beanTest.getEmail());
		daoTest.doSave(beanTest);
		
		recensione = new RecensioneBean("PROVA", 5, false, beanTest.getEmail());
		dao.doDelete(beanTest.getEmail());
		b = dao.doSave(recensione);
	}

	@Test
	void testRicercaRecensioneTramiteMail() {
		RecensioneBean bean = new RecensioneBean();
		assertNotEquals(bean, dao.doRetrieveByKey(recensione.getUtenteEmail()));
	}

	@Test
	void testListaCompletaRecensioni() {
		ArrayList<RecensioneBean> collection = new ArrayList<RecensioneBean>();
		assertNotEquals(collection, dao.doRetrieveAll());
	}

	@Test
	void testInserimentoRecensione() {
		assertNotEquals(-1, b);
	}

	@Test
	void testAggiornamentoRecensione() {
		assertEquals(true, dao.doUpdate(recensione, beanTest.getEmail()));
	}

	@Test
	void testEliminazioneRecensione() {
		assertEquals(true, dao.doDelete(beanTest.getEmail()));
	}

	@Test
	void testApprovazioneRecensione() {
		assertEquals(true, dao.approva(beanTest.getEmail()));
	}

	@AfterEach
	void tearDown() throws Exception {
		dao.doDelete(beanTest.getEmail());
		daoTest.doDelete(beanTest.getEmail());

	}


}
