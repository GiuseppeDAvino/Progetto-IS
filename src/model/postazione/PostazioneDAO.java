package model.postazione;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.categoria.CategoriaBean;
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
		String sql = "SELECT*FROM postazione p WHERE p.isDisponibile=1 AND p.id NOT IN(SELECT postazione.id FROM postazione p,prenotazione pr WHERE\r\n" + 
				"p.id=pr.postazioneId AND pr.dataPrenotazione=? AND pr.fasciaOraria=?)";
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
	 * @category Permette di cambiare la disponibilit‡ di una certa postazione
	 * 
	 * @param postazione la postazione da cui cambiare la disponibilit‡
	 */
	public void cambiaDisponibilit‡(PostazioneBean postazione) throws SQLException {
		String sql = "UPDATE postazione SET isDisponibile='?' WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("cambiaDisponibilit‡ della postazione " + postazione.getId());
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
	public boolean doSave(PostazioneBean bean) throws SQLException {
		String sql = "INSERT INTO postazione(nomeCategoria) VALUES(?)";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoSave" + statement);
			statement.setString(1, bean.getCategoria());
			statement.executeUpdate();
			con.commit();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @category permette di modificare la categoria di una postazione
	 * 
	 * @param bean   bean da cui verr‡ presa la categoria
	 * 
	 * @param chiave l'id della postazione da modificare
	 */
	@Override
	public boolean doUpdate(PostazioneBean bean, Integer chiave) throws SQLException {
		String sql = "UPDATE postazione SET nomeCategoria=? WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoUpdate" + statement);
			statement.setString(1, bean.getCategoria());
			statement.setInt(2, chiave);
			statement.executeUpdate();
			con.commit();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @category permette di cancellare la postazione
	 * 
	 * @param chiave l'id della postazione da cancellare
	 */
	@Override
	public boolean doDelete(Integer chiave) throws SQLException {

		String sql = "DELETE FROM postazione WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoDelete" + statement);
			statement.setInt(1, chiave);
			statement.executeUpdate();
			con.commit();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * @category Indica se una postazione Ë stata prenotata almeno una volta
	 * 
	 * @param postazione postazione da controllare
	 * */
	public boolean ËStataUtilizzata(PostazioneBean postazione) throws SQLException {
		String sql="SELECT * FROM postazione p where ? NOT IN(\r\n" + 
				"	SELECT pr.postazioneId FROM prenotazione pr)";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("ËStataUtilizzata" + statement);
			statement.setInt(1, postazione.getId());
			ResultSet rs=statement.executeQuery();
			if(rs.next())
				return true;
		}
		return false;
	}
	/**
	 * @category Ritorna una postazione libera che ha un certo nome di categoria
	 * 
	 * @param categoria categoria da cui prendere il nome
	 * 
	 * @param data data da prenotare
	 * @param fasciaOraria fascia oraria da prenotare
	 * */
	 
	public PostazioneBean postazioneLiberaCategoria(CategoriaBean categoria,String data,String fasciaOraria)  throws SQLException{
		PostazioneBean postazione=new PostazioneBean();
		String sql="SELECT * FROM postazione p,categoria c \r\n" + 
				"            WHERE p.isDisponibile=1 AND p.nomeCategoria=? AND p.id NOT IN(\r\n" + 
				"                    SELECT p.id FROM postazione p,prenotazione pr WHERE \r\n" + 
				"				    p.id=pr.postazioneId AND pr.dataPrenotazione=? AND pr.fasciaOraria=?)";
		
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("postazioneLiberaCategoria" + statement);
			statement.setString(1, categoria.getNome());
			statement.setString(2, data);
			statement.setString(3, fasciaOraria);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				postazione.setId(rs.getInt("id"));
				postazione.setCategoria(rs.getString("nomeCategoria"));
				postazione.setDisponibile(rs.getBoolean("isDisponibile"));
			}
		}
		return postazione;
	}


}
