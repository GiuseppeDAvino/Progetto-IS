package test;

import model.postazione.PostazioneBean;
import model.postazione.PostazioneDAO;
import model.servizio.Validatore;

public class ok {

	public static void main(String[] args) {
		if(!Validatore.validaQuantita("10"))
			System.out.println("aooooooo");

	}

}
