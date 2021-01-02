package model.postazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.categoria.CategoriaBean;
import model.connessione.DriverManagerConnectionPool;

/**
 *  Permette di effettuare gli accessi al database della tabella
 *           postazione
 * 
 */
public class PostazioneDAO implements ModelInterface<PostazioneBean, Integer> {
	/**
	 *  ritorna, se presente nel database, una postazione con l'id inserito
	 * 
	 * @param id l'id della postazione da ricercare
	 */
	@Override
	public PostazioneBean doRetrieveByKey(Integer id){
		PostazioneBean bean = new PostazioneBean();
		String sql = "SELECT * FROM postazione WHERE id=?";
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
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
			return bean;
		}
	}

	/**
	 *  ritorna tutte le postazioni presenti nel database
	 * 
	 */
	@Override
	public Collection<PostazioneBean> doRetrieveAll(){
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
				collection.add(bean);
			}
			return collection;
		} catch (SQLException e) {
			e.printStackTrace();
			return collection;
		}
		

	}
	
	/**
	 *  ritorna tutte le postazioni libere presenti nel database
	 * 
	 */
	
	public Collection<PostazioneBean> doRetrieveAllLibere(String data, String fasciaOraria){
		String sql = "SELECT*FROM postazione p WHERE p.isDisponibile=1 AND p.id NOT IN(SELECT p.id FROM postazione p,prenotazione pr WHERE\r\n" + 
				"p.id=pr.postazioneId AND pr.dataPrenotazione=? AND pr.fasciaOraria=?)";
		ArrayList<PostazioneBean> collection = new ArrayList<PostazioneBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoRetriveAll" + statement);
			statement.setString(1, data);
			statement.setString(2, fasciaOraria);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				PostazioneBean bean = new PostazioneBean();
				bean.setId(rs.getInt("id"));
				bean.setCategoria(rs.getString("nomeCategoria"));
				bean.setDisponibile(rs.getBoolean("isDisponibile"));
			}
			System.out.println("Lista" +collection);
			return collection;
		} catch (SQLException e) {
			e.printStackTrace();
			return collection;
		}
	}

	/**
	 *  Permette di cambiare la disponibilit� di una certa postazione
	 * 
	 * @param postazione la postazione da cui cambiare la disponibilit�
	 */
	public boolean cambiaDisponibilita(PostazioneBean postazione){
		String sql = "UPDATE postazione SET isDisponibile=? WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("cambiaDisponibilit� della postazione " + postazione.getId());
			statement.setBoolean(1, !postazione.isDisponibile());
			statement.setInt(2, postazione.getId());
			statement.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	} 

	/**
	 *  permette di salvare la postazione all'interno del database
	 * 
	 * @param bean bisogna passare un bean contenente la categoria
	 */
	@Override
	public boolean doSave(PostazioneBean bean) {
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
	 *  permette di modificare la categoria di una postazione
	 * 
	 * @param bean   bean da cui verr� presa la categoria
	 * 
	 * @param chiave l'id della postazione da modificare
	 */
	@Override
	public boolean doUpdate(PostazioneBean bean, Integer id) {
		String sql = "UPDATE postazione SET nomeCategoria=? WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoUpdate" + statement);
			statement.setString(1, bean.getCategoria());
			statement.setInt(2, id);
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
	 *  permette di cancellare la postazione
	 * 
	 * @param chiave l'id della postazione da cancellare
	 */
	@Override
	public boolean doDelete(Integer id) {

		String sql = "DELETE FROM postazione WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoDelete" + statement);
			statement.setInt(1, id);
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
	 * permette di cancellare la postazione
	 * 
	 * @param chiave il nome della categoria della postazione da cancellare
	 */
	public boolean doDeleteByNomeCategoria(String nomeCategoria) {

		String sql = "DELETE FROM postazione WHERE nomeCategoria=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoDelete" + statement);
			statement.setString(1, nomeCategoria);
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
	 *  Indica se una postazione � stata prenotata almeno una volta
	 * 
	 * @param postazione postazione da controllare
	 * @throws SQLException 
	 * */
	public boolean eStataUtilizzata(PostazioneBean postazione){
		String sql="SELECT * FROM postazione p where ? NOT IN(\r\n" + 
				"	SELECT pr.postazioneId FROM prenotazione pr)";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("�StataUtilizzata" + statement);
			statement.setInt(1, postazione.getId());
			ResultSet rs=statement.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 *  Ritorna una postazione libera che ha un certo nome di categoria
	 * 
	 * @param categoria categoria da cui prendere il nome
	 * 
	 * @param data data da prenotare
	 * @param fasciaOraria fascia oraria da prenotare
	 * */
	 
	public PostazioneBean postazioneLiberaCategoria(CategoriaBean categoria,String data,String fasciaOraria){
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
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return postazione;
	}

	
	public int doSaveTest(PostazioneBean bean) {
		String sql = "INSERT INTO postazione(nomeCategoria) VALUES(?)";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			System.out.println("DoSave" + statement);
			statement.setString(1, bean.getCategoria());
			statement.executeUpdate();
			con.commit();
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			con.commit();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}
}
