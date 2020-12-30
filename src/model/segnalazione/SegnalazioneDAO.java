package model.segnalazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.connessione.DriverManagerConnectionPool;
import model.notifica.NotificaBean;
import model.notifica.NotificaDAO;
import model.utente.UtenteBean;

public class SegnalazioneDAO implements ModelInterface<SegnalazioneBean, Integer> {

	/**
	 *  Ritorna una segnalazione in base all'id
	 * 
	 * @param id id della segnalazione
	 */
	@Override
	public SegnalazioneBean doRetrieveByKey(Integer id) {
		SegnalazioneBean bean = new SegnalazioneBean();
		String sql = "SELECT * FROM segnalazione WHERE id=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {

			statement.setInt(1, id);
			System.out.println("DoRetrieveByKey=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setId(rs.getInt("id"));
				bean.setTipo(rs.getString("tipo"));
				bean.setUtenteEmail(rs.getString("utenteEmail"));
			}
			return bean;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 *  Ritorna una segnalazione in base all'utente
	 * 
	 * @param email email dell'utente
	 */
	public SegnalazioneBean doRetrieveByUser(String email) {
		SegnalazioneBean bean = new SegnalazioneBean();
		String sql = "SELECT * FROM segnalazione WHERE utenteEmail=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {

			statement.setString(1, email);
			System.out.println("DoRetrieveByUser=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setId(rs.getInt("id"));
				bean.setTipo(rs.getString("tipo"));
				bean.setUtenteEmail(rs.getString("utenteEmail"));
			}
			return bean;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 *  Ritorna tutte le segnalazioni
	 * 
	 */
	@Override
	public Collection<SegnalazioneBean> doRetrieveAll() {
		String sql = "SELECT * FROM segnalazione";
		ArrayList<SegnalazioneBean> collection = new ArrayList<SegnalazioneBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {

			System.out.println("DoRetrieveByKey=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				SegnalazioneBean bean = new SegnalazioneBean();
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setId(rs.getInt("id"));
				bean.setTipo(rs.getString("tipo"));
				bean.setUtenteEmail(rs.getString("utenteEmail"));
				collection.add(bean);
			}
			return collection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 *  Salva una segnalazione nel database
	 * 
	 * @param bean Segnalazione da inserire
	 */
	@Override
	public boolean doSave(SegnalazioneBean bean) {
		String sql = "INSERT INTO segnalazione(tipo,descrizione,utenteEmail) VALUES(?,?,?)";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, bean.getTipo());
			statement.setString(2, bean.getDescrizione());
			statement.setString(3, bean.getUtenteEmail());
			System.out.println("doSave=" + statement);
			statement.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean doUpdate(SegnalazioneBean bean, Integer chiave) {
	
		return true;
	}

	/**
	 *  Elimina una segnalazione
	 * 
	 * @param id id della segnalazione da eliminare
	 */
	@Override
	public boolean doDelete(Integer id) {
		String sql = "DELETE FROM segnalazione WHERE id=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, id);
			System.out.println("doDelete=" + statement);
			statement.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Elimina una segnalazione
	 * 
	 * @param email email della segnalazione da eliminare
	 */
	public boolean doDeleteByEmail(String email) {
		String sql = "DELETE FROM segnalazione WHERE utenteEmail=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, email);
			System.out.println("doDelete=" + statement);
			statement.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 *  Salva una notifica per l'utente a cui � stata risolta la
	 *           segnalazione
	 * 
	 * @param segnalazione segnalazione da risolvere
	 * @param notificaBean notifica da inviare
	 */
	public void risolvi(SegnalazioneBean segnalazione, NotificaBean notificaBean) throws SQLException {
		doDelete(segnalazione.getId());
		NotificaDAO notifica = new NotificaDAO();
		UtenteBean utente = new UtenteBean();
		utente.setEmail(segnalazione.getUtenteEmail());
		ArrayList<UtenteBean> array = new ArrayList<UtenteBean>();
		array.add(utente);

		notifica.doSaveNotificaUtente(notificaBean, array);
	}

}
