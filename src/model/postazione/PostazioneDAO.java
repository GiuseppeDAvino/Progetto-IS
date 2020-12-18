package model.postazione;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.connessione.DriverManagerConnectionPool;

/**
 * @category Permette di effettuare gli accessi al database della tabella
 *           postazione
 * 
 */
public class PostazioneDAO implements ModelInterface<PostazioneBean, Integer> {
	/**
	 * @category ritorna, se presente nel database, una postazione con l'id inserito
	 * 
	 * @param id l'id della postazione da ricercare
	 */
	@Override
	public PostazioneBean doRetrieveByKey(Integer id) throws SQLException {
		PostazioneBean bean = new PostazioneBean();
		String sql = "SELECT * FROM periferica WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, id);
			System.out.println("DoRetrieveByKey" + statement);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setCategoria(rs.getString("nomeCategoria"));
				bean.setDisponibile(rs.getBoolean("isDisponibile"));
			}
		}
		return bean;
	}

	/**
	 * @category ritorna tutte le postazioni presenti nel database
	 * 
	 */
	@Override
	public Collection<PostazioneBean> doRetrieveAll() throws SQLException {
		String sql = "SELECT * FROM postazione";
		ArrayList<PostazioneBean> collection = new ArrayList<PostazioneBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoRetriveAll" + statement);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				PostazioneBean bean = new PostazioneBean();
				bean.setId(rs.getInt("id"));
				bean.setCategoria(rs.getString("nomeCategoria"));
				bean.setDisponibile(rs.getBoolean("isDisponibile"));
			}
		}
		return collection;

	}
	
	/**
	 * @category ritorna tutte le postazioni libere presenti nel database
	 * 
	 */
	
	public Collection<PostazioneBean> doRetrieveAllLibere(Date data, String fasciaOraria) throws SQLException {
		String sql = "select*from postazione p where p.isDisponibile=TRUE AND p.id NOT IN(select postazione.id from postazione p,prenotazione pr where\r\n" + 
				"	p.id=pr.postazioneId AND pr.data=? AND pr.fasciaOraria=?)";
		ArrayList<PostazioneBean> collection = new ArrayList<PostazioneBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoRetriveAll" + statement);
			statement.setDate(1, data);
			statement.setString(2, fasciaOraria);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				PostazioneBean bean = new PostazioneBean();
				bean.setId(rs.getInt("id"));
				bean.setCategoria(rs.getString("nomeCategoria"));
				bean.setDisponibile(rs.getBoolean("isDisponibile"));
			}
		}
		return collection;

	}

	/**
	 * @category Permette di cambiare la disponibilità di una certa postazione
	 * 
	 * @param postazione la postazione da cui cambiare la disponibilità
	 */
	public void cambiaDisponibilità(PostazioneBean postazione) throws SQLException {
		String sql = "UPDATE postazione SET isDisponibile='?' WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("cambiaDisponibilità della postazione " + postazione.getId());
			statement.setBoolean(1, !postazione.isDisponibile());
			statement.setInt(2, postazione.getId());
			statement.executeUpdate();
			con.commit();
		}
	}

	/**
	 * @category permette di salvare la postazione all'interno del database
	 * 
	 * @param bean bisogna passare un bean contenente la categoria
	 */
	@Override
	public void doSave(PostazioneBean bean) throws SQLException {
		String sql = "INSERT INTO postazione(nomeCategoria) VALUES(?)";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoSave" + statement);
			statement.setString(1, bean.getCategoria());
			statement.executeUpdate();
			con.commit();
		}

	}

	/**
	 * @category permette di modificare la categoria di una postazione
	 * 
	 * @param bean   bean da cui verrà presa la categoria
	 * 
	 * @param chiave l'id della postazione da modificare
	 */
	@Override
	public void doUpdate(PostazioneBean bean, Integer chiave) throws SQLException {
		String sql = "UPDATE postazione SET nomeCategoria=? WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoUpdate" + statement);
			statement.setString(1, bean.getCategoria());
			statement.setInt(2, chiave);
			statement.executeUpdate();
			con.commit();
		}

	}

	/**
	 * @category permette di cancellare la postazione
	 * 
	 * @param chiave l'id della postazione da cancellare
	 */
	@Override
	public void doDelete(Integer chiave) throws SQLException {

		String sql = "DELETE FROM postazione WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoDelete" + statement);
			statement.setInt(1, chiave);
			statement.executeUpdate();
			con.commit();
		}
	}


}
