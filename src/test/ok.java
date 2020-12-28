package test;

import model.postazione.PostazioneBean;
import model.postazione.PostazioneDAO;

public class ok {

	public static void main(String[] args) {
		PostazioneDAO dao=new PostazioneDAO();
		System.out.println(dao.doSave(new PostazioneBean("PS5")));

	}

}
