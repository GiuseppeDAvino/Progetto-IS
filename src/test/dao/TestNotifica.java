package test.dao;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import model.notifica.NotificaBean;
import model.notifica.NotificaDAO;
import model.postazione.PostazioneBean;
import model.utente.UtenteBean;
import model.utente.UtenteDAO;
import model.utente.UtenteBean.Ruolo;

class TestNotifica extends TestCase {
	
	private NotificaDAO dao;
	private NotificaBean notifica;
	private UtenteDAO daoTest;
	private UtenteBean beanTest;
	private int id;
	
	@BeforeEach
	protected void setUp() throws Exception {
		dao = new NotificaDAO();
		notifica = new NotificaBean("PROVA", "notifica");
		
		daoTest = new UtenteDAO();
		beanTest = new UtenteBean("test@test.com", "Test", "test", "TestTest", Ruolo.cliente, true, "", "test");
		
		daoTest.doDelete(beanTest.getEmail());
		daoTest.doSave(beanTest);
		id=dao.doSave(notifica);

	}
	@Test
	void testCreazioneNuovaNotifica() {
		assertNotEquals(-1, id);
	}

	@Test
	void testRicercaNotificaSpecifica() {
		PostazioneBean bean = new PostazioneBean();
		assertNotEquals(bean, dao.doRetrieveByKey(id));
	}

	@Test
	void testListaCompletaNotifiche() {
		ArrayList<NotificaBean> collection = new ArrayList<NotificaBean>();
		assertNotEquals(collection, dao.doRetrieveAll());
		
	}
	@Test
	void testSalvataggioNotificaUtente() {
		ArrayList <UtenteBean> utenti = new ArrayList<UtenteBean>();
		utenti.add(beanTest);
		int test = dao.doSaveNotificaUtente(notifica, utenti);
		assertNotEquals(-1, test);
		dao.doDelete(test);
	}
	@Test
	void testEliminazioneNotifica() {
		assertEquals(true, dao.doDelete(id));
	}

	@Test
	void testLetturaNotifica() {
		assertEquals(true, dao.letta(id, beanTest));
	}

	@Test
	void testEliminazioneNotificaUtente() {
		assertEquals(true, dao.doDeleteNotificaUtente(id, beanTest));
	}

	@Test
	void testEliminazioneTotalelNotificheUtente() {
		assertEquals(true, dao.doDeleteAllNotificheUtente(beanTest));
	}
	
	@Test
	void testEliminazioneUltimaNotifica() {
		assertEquals(true, dao.doDeleteUltimaNotifica());
	}


	@AfterEach
	protected void tearDown() throws Exception {
		dao.doDelete(id);
		daoTest.doDelete(beanTest.getEmail());
	}


}
