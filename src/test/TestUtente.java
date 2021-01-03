package test;


import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.TestCase;
import model.utente.UtenteBean;
import model.utente.UtenteBean.Ruolo;
import model.utente.UtenteDAO;

public class TestUtente extends TestCase {

  private UtenteDAO dao;
  private UtenteBean utenteEsistente;
  private UtenteBean utenteNonEsistente;
  private int buleano;
  
  @BeforeEach
  protected void setUp() throws Exception {
   
    dao= new UtenteDAO();
    utenteEsistente=new UtenteBean("titol@titolare.com","titolare","titolare","titolare",Ruolo.titolare,true,"","titolare");
    buleano = dao.doSave(utenteEsistente);
    utenteNonEsistente=new UtenteBean("nonesisto@esistenza.com","nonEsisto","nonEsisto","nonEsisto",Ruolo.cliente,false,"null","nonEsisto");
  }
  
  @Test
  public void testInserimentoUtenteEsistente() {
    assertNotSame(-1, buleano);
  }
  @Test 
  public void testInserimentoUtenteNonEsistente() {
    assertEquals(0, dao.doSave(utenteNonEsistente));
  }

  @Test
  public void testAccessoUtenteEsistente() { 
	  UtenteBean bean = dao.doRetrieveByKey(utenteEsistente.getEmail());
	  assertEquals(true,Arrays.compare(bean.getPassword(), utenteEsistente.getPassword())==0); 
  }
  @Test
  public void testAccessoUtenteNonEsistente() {  
	  UtenteBean bean = dao.doRetrieveByKey(utenteNonEsistente.getEmail());	  
	  assertEquals(false,Arrays.compare(bean.getPassword(), utenteNonEsistente.getPassword())==0); 
  }
  @Test
  public void testModificaCredenzialiUtenteEsistente() {
    assertEquals(true, dao.doUpdate(utenteEsistente,utenteEsistente.getEmail()));
  }
  @Test
  public void testListaCompletaUtenti() {
	  ArrayList<UtenteBean> collection = new ArrayList<UtenteBean>(); 
	  assertNotEquals(collection, dao.doRetrieveAll());  
  }
  @Test
  public void testEsisteUsernameUtenteEsistente() {
	  assertEquals(true, dao.esisteUsername(utenteEsistente.getUsername()));
  }
  @Test
  public void testEsisteUsernameUtenteNonEsistente() {
	  assertEquals(false, dao.esisteUsername(utenteNonEsistente.getUsername()));
  }
  @Test
  public void testEsisteMailUtenteEsistente() {
	  assertEquals(true, dao.esisteEmail(utenteEsistente.getEmail()));
  }
  @Test
  public void testEsisteMailUtenteNonEsistente() {
	  assertEquals(false, dao.esisteEmail(utenteNonEsistente.getEmail()));
  }
  @Test
  public void testListaCompletaUtentiDivisiPerPrenotazioni() {
	  ArrayList<UtenteBean> collection = new ArrayList<UtenteBean>(); 
	  assertNotEquals(collection, dao.doRetrieveAllPerPrenotazioni());  
  }
  //Reimposto il db allo stato originale
  @AfterEach
  protected void tearDown() throws Exception {
    dao.doDelete(utenteNonEsistente.getEmail());
    dao.doDelete(utenteEsistente.getEmail());
  }
}