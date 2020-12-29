package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.postazione.PostazioneBean;
import model.postazione.PostazioneDAO;

class TestPostazione { 
	private PostazioneDAO dao;
	private PostazioneBean postazioneConCategoriaNonEsistente;
	private PostazioneBean postazioneConCategoriaEsistente;
	
	@BeforeEach
	protected void SetUp() throws Exception {
		dao = new PostazioneDAO();
		postazioneConCategoriaNonEsistente = new PostazioneBean("Nintendo");
		postazioneConCategoriaEsistente = new PostazioneBean("PROVA");
	}

//	@Test
//	void testCambiaDisponibilit‡() {
//		fail("Not yet implemented");
//	}

	@Test
	void testInserimentoPostazioneConCategoriaNonEsistente() throws SQLException {
		assertEquals(false, dao.doSave(postazioneConCategoriaNonEsistente));
	}
	@Test
	void testInserimentoPostazioneConCategoriaEsistente() throws SQLException {
		assertEquals(true, dao.doSave(postazioneConCategoriaEsistente));
	} 
	@Test
	void testListaPostazioniPresenti() {
		ArrayList<PostazioneBean> collection = new ArrayList<PostazioneBean>();
		assertNotEquals(collection, dao.doRetrieveAll());
	}
//	@Test
//	void testRicercaPostazioneTramiteIdConCategoriaEsistente() throws SQLException {
//		assertEquals(postazioneConCategoriaEsistente, dao.doRetrieveByKey(postazioneConCategoriaEsistente.getId()));
//	}

//	@Test
//	void testAggiornaPostazione() {
//		fail("Not yet implemented");
//	}

	@Test
	void test»StataUtilizzata() throws SQLException {
		assertEquals(true, dao.ËStataUtilizzata(postazioneConCategoriaEsistente));
	}


//	@Test
//	void testListaTutteLePostazioniLibere() {
//		fail("Not yet implemented");
//	}	

	@After
	protected void tearDown() throws SQLException {
		dao.doDelete(postazioneConCategoriaEsistente.getId());
	}

}
