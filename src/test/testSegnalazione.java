package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.segnalazione.SegnalazioneBean;
import model.segnalazione.SegnalazioneDAO;


class testSegnalazione {

	private SegnalazioneDAO dao;
	private SegnalazioneBean segnalazione;
	
	@BeforeEach
	protected void setUp() {
		dao = new SegnalazioneDAO();
		segnalazione = new SegnalazioneBean("Segnalazione", "Test","titolare@titolare.com");
		dao.doSave(segnalazione);
	}

	@Test
	void testInserimentoNuovaSegnalazione() {
		assertEquals(true, dao.doSave(segnalazione));
	}
	@Test
	void testRicercaSegnalazioneInBaseAllaEmail() {
		SegnalazioneBean bean = new SegnalazioneBean();
		assertNotEquals(bean, dao.doRetrieveByUser(segnalazione.getUtenteEmail()));
	}
	@Test
	void testRicercaSegnalazioniGeneriche() {
		ArrayList<SegnalazioneBean> collection = new ArrayList<SegnalazioneBean>();
		assertNotEquals(collection, dao.doRetrieveAll());
	}

	@Test
	void testEliminazioneTramiteEmail() {
		assertEquals(true, dao.doDeleteByEmail(segnalazione.getUtenteEmail()));
	}

	@AfterEach
	protected void tearDown() {
		dao.doDeleteByEmail(segnalazione.getUtenteEmail());
	}
}
