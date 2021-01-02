package test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;
import model.prenotazione.PrenotazioneBean;
import model.prenotazione.PrenotazioneDAO;
import model.utente.UtenteBean;
import model.utente.UtenteBean.Ruolo;
import model.utente.UtenteDAO;

class TestPrenotazione extends TestCase {

	private PrenotazioneDAO dao;
	private PrenotazioneBean prenotazione;
	private UtenteDAO daoTest;
	private UtenteBean beanTest;
	private PerifericaBean beanPerTest;
	private PerifericaDAO daoPerTest;
	private int id;
	@BeforeEach
	protected void setUp() throws Exception {
		dao = new PrenotazioneDAO();
		daoTest = new UtenteDAO();
		
		beanTest = new UtenteBean("test@test.com", "Test", "test", "TestTest", Ruolo.cliente, true, "", "test");
		daoTest.doSave(beanTest);
		
		prenotazione = new PrenotazioneBean(1, "2021-02-02", "15-18", "das1das2d1a", "test@test.com", 1, 12);
		id=dao.doSaveTest(prenotazione);
	}

	@Test
	void testNuovaPrenotazione() {
		int id1=-1;
		assertNotEquals(id1, id);
	}

	@Test
	void testRicercaTutteLePrenotazioni() {
		ArrayList<PrenotazioneBean> collection = new ArrayList<PrenotazioneBean>();
		assertNotEquals(collection, dao.doRetrieveAll()); 
		System.out.println(dao.doRetrieveAll());
	}
	
	@Test
	void testRicercaPrenotazioniTramiteMail() {
		ArrayList<PrenotazioneBean> collection = new ArrayList<PrenotazioneBean>();
		assertNotEquals(collection, dao.doRetrieveByEmail(prenotazione.getUtenteEmail()));
	}

	@Test
	void testDoDelete() {
		assertEquals(true, dao.doDelete(id));
	}
	@Test
	void testTerminaPrenotazione() {
		assertEquals(true, dao.terminaPrenotazione(prenotazione));
	}
	@Test
	void testPrenotaConPeriferiche() {
		 daoPerTest = new PerifericaDAO();
		 beanPerTest = new PerifericaBean("Test", "Testing", 2, 4);
		 daoPerTest.doSave(beanPerTest);
		 ArrayList<PerifericaBean> periferiche = new ArrayList<PerifericaBean>();
		 periferiche.add(beanPerTest);
		 int test= dao.prenotaConPeriferiche(prenotazione, periferiche);
		 assertNotEquals(-1,test);
		 dao.doDelete(test);
		 daoPerTest.doDelete(beanPerTest.getNome());
	}
	
	@AfterEach
	protected void tearDown() throws Exception {
		dao.doDelete(id);
		daoTest.doDelete(beanTest.getEmail());
	}

}
