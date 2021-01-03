package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.notifica.NotificaBean;
import model.notifica.NotificaDAO;
import model.segnalazione.SegnalazioneBean;
import model.segnalazione.SegnalazioneDAO;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;


class testSegnalazione {

	private SegnalazioneDAO dao;
	private SegnalazioneBean segnalazione;
	
	private UtenteDAO daoTest;
	private UtenteBean beanTest;
	
	private NotificaDAO daoNotTest;
	private NotificaBean beanNotTest;
	
	int id;
	
	@BeforeEach
	protected void setUp() {
		dao = new SegnalazioneDAO();
		daoTest = new UtenteDAO();
		beanTest = new UtenteBean("test@test.com", "Test", "test", "TestTest", Ruolo.cliente, true, "", "test");
		daoTest.doSave(beanTest);
		segnalazione = new SegnalazioneBean("Segnalazione", "Test","test@test.com");
		daoNotTest = new NotificaDAO();
		beanNotTest = new NotificaBean("TESTING", "TESTING");
		
		id = dao.doSave(segnalazione);
	}

	@Test
	void testInserimentoNuovaSegnalazione() {
		assertNotEquals(-1,id);
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
	void testRicercaTramiteID() {
		SegnalazioneBean bean = new SegnalazioneBean();
		assertNotEquals(bean, dao.doRetrieveByKey(id));
	}
	@Test
	void testEliminazioneTramiteEmail() {
		assertEquals(true, dao.doDeleteByEmail(segnalazione.getUtenteEmail()));
	}
	@Test
	void testEliminazioneTramiteID() {
		assertEquals(true, dao.doDelete(id));
	}
	@Test
	void testRisoluzioneSegnalazione() {
		int id1 = daoNotTest.doSave(beanNotTest);
		assertEquals(true, dao.risolvi(segnalazione, beanNotTest));
		daoNotTest.doDelete(id1);
	}

	@AfterEach
	protected void tearDown() {
		dao.doDelete(id);
		daoTest.doDelete(beanTest.getEmail());
	}
}
