package model.recensione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.connessione.DriverManagerConnectionPool;

public class RecensioneDAO implements ModelInterface<RecensioneBean, String> {

	/**
	 * @category Ricerca la recensione in base all'email dell'utente
	 * 
	 * @param email email dell'utente da ricercare
	 */
	@Override
	public RecensioneBean doRetrieveByKey(String email) throws SQLException {
		RecensioneBean bean = new RecensioneBean();
		String sql = "SELECT * FROM recensione WHERE email=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, email);
			System.out.println("DoRetrieveByKey" + statement);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setUtenteEmail(rs.getString("utenteEmail"));
				bean.setValutazione(rs.getInt("valutazione"));
				bean.setVerificata(rs.getBoolean("verificata"));
			}
		}
		return bean;
	}

	/**
	 * @category Ritorna tutte le recensioni
	 * 
	 */
	@Override
	public Collection<RecensioneBean> doRetrieveAll() throws SQLException {
		String sql = "SELECT * FROM recensione";

		ArrayList<RecensioneBean> collection = new ArrayList<RecensioneBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoRetriveAll");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setUtenteEmail(rs.getString("utenteEmail"));
				bean.setValutazione(rs.getInt("valutazione"));
				bean.setVerificata(rs.getBoolean("verificata"));
				collection.add(bean);
			}
		}
		return collection;
	}

	/**
	 * @category Salva una recensione nel database
	 * 
	 * @param bean Recensione da salvare
	 */
	@Override
	public void doSave(RecensioneBean bean) throws SQLException {
		String sql = "INSER INTO recensione VALUES (?,?,?,?)";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, bean.getDescrizione());
			statement.setInt(2, bean.getValutazione());
			statement.setBoolean(3, false);
			statement.setString(4, bean.getUtenteEmail());

			System.out.println("doSave=" + statement);
			statement.executeUpdate();
			con.commit();
		}

	}

	/**
	 * @category Aggiorna una recensione
	 * 
	 * @param bean  Recensione con contenuto aggiornato
	 * @param email Utente proprietario della recensione da modificare
	 */
	@Override
	public void doUpdate(RecensioneBean bean, String email) throws SQLException {
		String sql = "UPDATE recensione SET descrizione=? WHERE utenteEmail=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, bean.getDescrizione());
			statement.setString(2, email);

			System.out.println("doUpdate=" + statement);
			statement.executeUpdate();
			con.commit();
		}
	}

	/**
	 * @category Cancella una recensione
	 * 
	 * @param email Indica il proprietario della recensione
	 */
	@Override
	public void doDelete(String email) throws SQLException {
		String sql = "DELETE FROM recensione WHERE utenteEmail=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, email);
			System.out.println("doUpdate=" + statement);
			statement.executeUpdate();
			con.commit();
		}
	}

	/**
	 * @category Approva una recensione
	 * 
	 * @param email proprietario della recensione
	 */
	public void approva(String email) throws SQLException {
		String sql = "UPDATE recensione SET verificata='true' WHERE email=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, email);

			System.out.println("doUpdate=" + statement);
			statement.executeUpdate();
			con.commit();
		}
	}
}