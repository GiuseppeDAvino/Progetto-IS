package test.dao;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import model.categoria.CategoriaBean;
import model.categoria.CategoriaDAO;
import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;
import model.postazione.PostazioneBean;
import model.postazione.PostazioneDAO;
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
	private CategoriaDAO categoriaDAO;
	private CategoriaBean categoria;
	PostazioneBean postazione;
	private PostazioneDAO postazioneDAO;
	private int id;
	@BeforeEach
	protected void setUp() throws Exception {
		dao = new PrenotazioneDAO();
		daoTest = new UtenteDAO();
		categoria = new CategoriaBean("test","test",1f,"test","test");
		categoriaDAO = new CategoriaDAO();
		postazioneDAO = new PostazioneDAO();
		categoriaDAO.doSave(categoria);
		postazione = new PostazioneBean(categoria.getNome());
		
		int idP = postazioneDAO.doSave(postazione);
		beanTest = new UtenteBean("test@test.com", "Test", "test", "TestTest", Ruolo.cliente, true, "", "test");
		daoTest.doDelete(beanTest.getEmail());
		daoTest.doSave(beanTest);
		
		prenotazione = new PrenotazioneBean(idP, "2021-02-02", "15-18", "das1das2d1a", "test@test.com", idP, 12);
		id=dao.doSave(prenotazione);
		prenotazione.setId(id);
	}

	@Test
	void testNuovaPrenotazione() {
		assertNotEquals(-1, id);
	}

	@Test
	void testListaTutteLePrenotazioni() {
		ArrayList<PrenotazioneBean> collection = new ArrayList<PrenotazioneBean>();
		assertNotEquals(collection, dao.doRetrieveAll()); 
		System.out.println(dao.doRetrieveAll());
	}
	
	@Test
	void testListaPrenotazioniTramiteMail() {
		ArrayList<PrenotazioneBean> collection = new ArrayList<PrenotazioneBean>();
		assertNotEquals(collection, dao.doRetrieveByEmail(prenotazione.getUtenteEmail()));
	}

	@Test
	void testEliminazionePrenotazione() {
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
		 daoPerTest.doDelete(beanPerTest.getNome());
		 dao.doDelete(test);
		 daoPerTest.doDelete(beanPerTest.getNome());
	}
	
	@AfterEach
	protected void tearDown() throws Exception {
		dao.doDelete(id);
		categoriaDAO.doDelete(categoria.getNome());
		daoTest.doDelete(beanTest.getEmail());
	}

}
