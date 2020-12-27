package test;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.periferica.PerifericaBean;
import model.periferica.PerifericaDAO;

public class TestPeriferica extends TestCase {

	private PerifericaDAO dao;
	private PerifericaBean perifericaEsistente;
	private PerifericaBean perifericaNonEsistente;
	
	@Before
	protected void setUp() throws Exception {
		dao = new PerifericaDAO();
		perifericaEsistente = new PerifericaBean("tastiera","Razer chroma",5,2);
		perifericaNonEsistente = new PerifericaBean("mouse", "Trust", 5, 21);
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

	 @After
	  @Override
	  protected void tearDown() throws Exception {
	    dao.doDelete(perifericaNonEsistente.getNome());
	  }
}
