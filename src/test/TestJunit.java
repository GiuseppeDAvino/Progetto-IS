package test;

import org.junit.Test;

import model.utente.UtenteBean;

public class TestJunit {
	
	
	@Test
	public void test() {

	}
	
	
	private class dbMock {
		private int eta;
		private String nome;
		public dbMock(int eta, String nome) {
			this.eta = eta;
			this.nome = nome;
		}
		
		public UtenteBean executeQuery() {
			return new UtenteBean();
		}
		
	}
}
