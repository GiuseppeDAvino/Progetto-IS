package test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import model.prenotazione.PrenotazioneBean;
import model.prenotazione.PrenotazioneDAO;

class TestPrenotazione extends TestCase {

	private PrenotazioneDAO dao;
	private PrenotazioneBean prenotazione;
	@BeforeEach
	protected void setUp() throws Exception {
		dao = new PrenotazioneDAO();
		prenotazione = new PrenotazioneBean(1, "2021-02-02", "15-18", "das1das2d1a", "titolare@titolare.com", 1, 12);
		dao.doSave(prenotazione);
	}

	@Test
	void testNuovaPrenotazione() {
		assertEquals(true, dao.doSave(prenotazione));
	}

	@Test
	void testRicercaTutteLePrenotazioni() {
		ArrayList<PrenotazioneBean> collection = new ArrayList<PrenotazioneBean>();
		assertNotEquals(collection, dao.doRetrieveAll()); 
	}
	@Test
	void testRicercaPrenotazioniTramiteMail() {
		ArrayList<PrenotazioneBean> collection = new ArrayList<PrenotazioneBean>();
		assertNotEquals(collection, dao.doRetrieveByEmail(prenotazione.getUtenteEmail()));
	}
/*
	@Test
	void testDoDelete() {
		assertEquals(true, dao.doDelete(prenotazione.getId()));
	}


	@Test
	void testPrenotaConPeriferiche() {
		
	}
	
	@Test
	void testTerminaPrenotazione() {
		assertEquals(true, dao.terminaPrenotazione(prenotazione));
	}

*/	
	@AfterEach
	protected void tearDown() throws Exception {
		dao.deleteTest(prenotazione.getUtenteEmail());
	}

}
