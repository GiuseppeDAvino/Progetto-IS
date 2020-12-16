package model.segnalazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.connessione.DriverManagerConnectionPool;

public class SegnalazioneDAO implements ModelInterface<SegnalazioneBean, Integer> {

	/**
	 * @category Ritorna una segnalazione in base all'id
	 * 
	 * @param id id della segnalazione
	 */
	@Override
	public SegnalazioneBean doRetrieveByKey(Integer id) throws SQLException {
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
		}
		return bean;
	}

	/**
	 * @category Ritorna una segnalazione in base all'utente
	 * 
	 * @param email email dell'utente
	 */
	public SegnalazioneBean doRetrieveByUser(String email) throws SQLException {
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
		}
		return bean;
	}

	/**
	 * @category Ritorna tutte le segnalazioni
	 * 
	 */
	@Override
	public Collection<SegnalazioneBean> doRetrieveAll() throws SQLException {
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
		}
		return collection;
	}

	/**
	 * @category Salva una categoria nel database
	 * 
	 * @param bean Segnalazione da inserire
	 */
	@Override
	public void doSave(SegnalazioneBean bean) throws SQLException {
		String sql = "INSERT INTO segnalazione(tipo,descrizione,utenteEmail) VALUES(?,?,?)";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, bean.getTipo());
			statement.setString(2, bean.getDescrizione());
			statement.setString(3, bean.getUtenteEmail());
			System.out.println("doSave=" + statement);
			statement.executeUpdate();
			con.commit();

		}

	}

	@Override
	public void doUpdate(SegnalazioneBean bean, Integer chiave) throws SQLException {
		// TODO Auto-generated method stub

	}

	/**
	 * @category Elimina una postazione
	 * 
	 * @param id id della postazione da eliminare
	 */
	@Override
	public void doDelete(Integer id) throws SQLException {
		String sql = "DELETE FROM segnalazione WHERE id=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, id);
			System.out.println("doDelete=" + statement);
			statement.executeUpdate();
			con.commit();
		}
	}

}
