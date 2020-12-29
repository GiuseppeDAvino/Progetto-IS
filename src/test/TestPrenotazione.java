package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.prenotazione.PrenotazioneBean;
import model.prenotazione.PrenotazioneDAO;

class TestPrenotazione {

	private PrenotazioneDAO dao;
	private PrenotazioneBean prenotazione;
	@BeforeEach
	void setUp() throws Exception {
		dao = new PrenotazioneDAO();
		//prenotazione = new PrenotazioneBean(id, data, fasciaOraria, qr, utenteEmail, postazioneId, prezzo)
	}


	@Test
	void testRicercaPrenotazioneSpecifica() {
		PrenotazioneBean bean = new PrenotazioneBean();
		assertNotEquals(bean, dao.doRetrieveByKey(1));
	}

	@Test
	void testRicercaTutteLePrentazioni() {
		ArrayList<PrenotazioneBean> collection = new ArrayList<PrenotazioneBean>();
		assertNotEquals(collection, dao.doRetrieveAll());
	}

//	@Test
//	void testTerminaPrenotazione() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testDoSave() {
//		fail("Not yet implemented");
//	}

/*	@Test
	void testDoDelete() {
		fail("Not yet implemented");
	}
*/
	@Test
	void testPrenotaConPeriferiche() {
		fail("Not yet implemented");
	}
	@AfterEach
	void tearDown() throws Exception {
	}

}
