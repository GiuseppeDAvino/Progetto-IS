package model.notifica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.connessione.DriverManagerConnectionPool;
import model.utente.UtenteBean;

/**
 *  Permette di effettuare gli accessi al database della tabella
 *           notifica
 * 
 */
public class NotificaDAO implements ModelInterface<NotificaBean, Integer> {
	/**
	 *  ritorna, se presente nel database, una notifica con l'id inserito
	 * 
	 * @param id l'id della notifica da ricercare
	 */
	@Override
	public NotificaBean doRetrieveByKey(Integer id) {
		NotificaBean bean = new NotificaBean();
		String sql = "SELECT * FROM notifica WHERE id=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {

			statement.setInt(1, id);
			System.out.println("DoRetrieveByKey=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setTipo(rs.getString("tipo"));
			}
			return bean;
		} catch (SQLException e) {
		
			e.printStackTrace();
			return null;
		}

	}

	/**
	 *  ritorna tutte le notifiche presenti nel database
	 * 
	 */
	@Override
	public Collection<NotificaBean> doRetrieveAll() {
		String sql = "SELECT * FROM notifica";

		ArrayList<NotificaBean> collection = new ArrayList<NotificaBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoRetrieveAll");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				NotificaBean bean = new NotificaBean();

				bean.setId(rs.getInt("id"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setTipo(rs.getString("tipo"));
				collection.add(bean);
			}
			return collection;
		} catch (SQLException e) {
	
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *  permette di salvare la notifica all'interno del database <br>
	 *           Non utilizzare questo metodo per salvare le notifiche</br>
	 */
	@Override
	public boolean doSave(NotificaBean bean) {
		String sql = "INSERT INTO notifica(descrizione,tipo) VALUES(?,?)";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, bean.getDescrizione());
			statement.setString(2, bean.getTipo());
			System.out.println("doSave=" + statement);
			statement.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 *  permette di salvare la notifica all'interno del database
	 *           collengandola agli utenti
	 * 
	 * @param notifica è la notifica da inserire dal database
	 * @param utenti   è la lista di utenti a cui verrà collegata la notifica
	 */
	public boolean doSaveNotificaUtente(NotificaBean notifica, ArrayList<UtenteBean> utenti) {
		doSave(notifica);
		ArrayList<NotificaBean> notifiche = (ArrayList<NotificaBean>) doRetrieveAll();
		NotificaBean notificaId = notifiche.get(notifiche.size() - 1);
		String sql = "INSERT INTO notifica_utente VALUES(?,?,?)";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			for (UtenteBean utenteBean : utenti) {
				statement.setInt(1, notificaId.getId());
				statement.setString(2, utenteBean.getEmail());
				statement.setBoolean(3, false);
				statement.executeUpdate();
				con.commit();

			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean doUpdate(NotificaBean bean, Integer chiave) {
		return true;
	}

	/**
	 *  permette di eliminare una notifica dal database
	 * 
	 * @param chiave è la chiave per selezionare la riga da eliminare
	 */
	@Override
	public boolean doDelete(Integer chiave) {
		String sql = "DELETE FROM notifica WHERE id=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, chiave);
			System.out.println("doDelete notifica=" + statement);
			statement.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 *  permette di impostare isRead di uno specifico utente su una
	 *           notifica
	 * 
	 * @param id     id della notifica
	 * @param utente utente su cui deve essere modificato lo stato della notifica
	 */
	public boolean letta(int id, UtenteBean utente) {
		String sql = "UPDATE notifica_utente SET isRead='TRUE' WHERE notificaId=? AND utenteEmail=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, id);
			statement.setString(2, utente.getEmail());
			statement.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 *  permette di eliminare una notifica da un utente
	 * 
	 * @param id     id della notifica
	 * @param utente utente su cui deve essere eliminata la notifica
	 */
	public boolean doDeleteNotificaUtente(int id, UtenteBean utente) {
		String sql = "DELETE FROM notifica_utente WHERE notificaId=? AND utenteEmail=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, id);
			statement.setString(2, utente.getEmail());
			statement.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * permette di eliminare tutte le notifiche di un utente
	 * 
	 * @param utente utente su cui deve essere eliminata la notifica
	 */
	public boolean doDeleteAllNotificheUtente(UtenteBean utente) {
		String sql = "DELETE FROM notifica_utente WHERE utenteEmail=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, utente.getEmail());
			statement.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean doDeleteUltimaNotifica() {
		ArrayList<NotificaBean> notifiche = (ArrayList<NotificaBean>) doRetrieveAll();
		if(notifiche.size() == 0) 
			return false;
		else {
		NotificaBean notificaId = notifiche.get(notifiche.size() - 1);
		if(doDelete(notificaId.getId()))
			return true;
		}
		return false;
	}

	
	
}
