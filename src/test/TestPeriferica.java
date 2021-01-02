package test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.TestCase;
import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;

public class TestPeriferica extends TestCase {

	private PerifericaDAO dao;
	private PerifericaBean perifericaEsistente;
	private PerifericaBean perifericaNonEsistente;
	
	@BeforeEach
	protected void setUp() throws Exception {
		dao = new PerifericaDAO();
		perifericaEsistente = new PerifericaBean("tastiera","TestEsistente",5,2);
		perifericaNonEsistente = new PerifericaBean("mouse", "Trust", 5, 21);
		dao.doSave(perifericaEsistente);
	}
	@Test
	public void testRicercaPerifericaEsistente() throws SQLException {
		assertEquals(perifericaEsistente.getNome(), dao.doRetrieveByKey(perifericaEsistente.getNome()).getNome());
	} 
	@Test
	public void testRicercaPerifericaNonEsistente() throws SQLException {
		assertEquals("", dao.doRetrieveByKey(perifericaNonEsistente.getNome()).getNome());
	}
	@Test
	public void testInserimentoPerifericaEsistente() throws SQLException {
			assertEquals(false, dao.doSave(perifericaEsistente));
	}
	@Test
	public void testInserimentoPerifericaNonEsistente() throws SQLException {
		assertEquals(true, dao.doSave(perifericaNonEsistente));
	}
	@Test
	public void testModificaPerifericaEsistente() throws SQLException {
		assertEquals(true,dao.doUpdate(perifericaEsistente, perifericaEsistente.getNome()));
	}
	@Test
	public void testListaPerifericheEsistentiDivisePerTipi() throws SQLException {		
		ArrayList<PerifericaBean> collection = new ArrayList<PerifericaBean>();		
		assertNotEquals(collection, dao.doRetrieveAllTipi()); 
	}
	@Test
	public void testListaPerifericheEsistenti() {		
		ArrayList<PerifericaBean> collection = new ArrayList<PerifericaBean>();		
		assertNotEquals(collection, dao.doRetrieveAll()); 
	}

	 @After
	  @Override
	  protected void tearDown() throws Exception {
	    dao.doDelete(perifericaNonEsistente.getNome());
	    dao.doDelete(perifericaEsistente.getNome());
	  }
}
