package test;

import java.util.ArrayList;

import model.prenotazione.PrenotazioneBean;
import model.prenotazione.PrenotazioneDAO;

public class ok {
	
	public static void main(String[] args) {
		PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
		PrenotazioneBean prenotazione = new PrenotazioneBean(1, "2021-02-02", "15-18", "das1das2d1a", "titolare@titolare.com", 1, 12);
		ArrayList<PrenotazioneBean> p = (ArrayList<PrenotazioneBean>) prenotazioneDAO.doRetrieveAll();
		prenotazioneDAO.doSave(prenotazione);
		for (int i = 0; i < p.size(); i++) {
			System.out.println(p.get(i));
		}
		
		System.out.println(prenotazioneDAO.doRetrieveByKey(1));
	}

}
