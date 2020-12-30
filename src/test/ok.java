package test;

import model.notifica.NotificaDAO;
import model.postazione.PostazioneBean;
import model.postazione.PostazioneDAO;
import model.servizio.Validatore;

public class ok {
	
	public static void main(String[] args) {
		NotificaDAO notificaDAO = new NotificaDAO();
		System.out.println(notificaDAO.doRetrieveAll());
	}

}
