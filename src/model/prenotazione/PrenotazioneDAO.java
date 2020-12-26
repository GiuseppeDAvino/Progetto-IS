package model.prenotazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.connessione.DriverManagerConnectionPool;
import model.notifica.NotificaBean;
import model.periferica.PerifericaBean;
import model.postazione.PostazioneBean;

public class PrenotazioneDAO implements ModelInterface<PrenotazioneBean, Integer> {
	/**
	 * @category ritorna, se presente nel database, una prenotazione con l'id
	 *           inserito
	 * 
	 * @param id l'id della prenotazione da ricercare
	 */
	@Override
	public PrenotazioneBean doRetrieveByKey(Integer id) throws SQLException {
		PrenotazioneBean bean = new PrenotazioneBean();
		String sql = "Select * FROM prenotazione WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setInt(1, id);
			System.out.println("DoRetrieveByKey" + statement);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setData(rs.getString("dataPrenotazione"));
				bean.setFasciaOraria(rs.getString("fasciaOraria"));
				bean.setId(rs.getInt("id"));
				bean.setQr(rs.getString("qr"));
				bean.setPostazioneId(rs.getInt("postazioneId"));
				bean.setUtenteEmail(rs.getString("utenteEmail"));
				bean.setPrezzo(rs.getFloat("prezzo"));
			}
		}
		return bean;
	}

	/**
	 * @category ritorna tutte le prenotazioni presenti nel database
	 * 
	 */
	@Override
	public Collection<PrenotazioneBean> doRetrieveAll() throws SQLException {
		String sql = "SELECT * FROM prenotazione";
		ArrayList<PrenotazioneBean> collection = new ArrayList<PrenotazioneBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoRetriveAll" + statement);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				PrenotazioneBean bean = new PrenotazioneBean();
				bean.setData(rs.getString("dataPrenotazione"));
				bean.setFasciaOraria(rs.getString("fasciaOraria"));
				bean.setId(rs.getInt("id"));
				bean.setQr(rs.getString("qr"));
				bean.setPostazioneId(rs.getInt("postazioneId"));
				bean.setUtenteEmail(rs.getString("utenteEmail"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				collection.add(bean);
			}
		}
		return collection;

	}

	/**
	 * @category Termina la prenotazione disattivando il qr
	 * 
	 * @param bean Prenotazione da terminare
	 */

	public void terminaPrenotazione(PrenotazioneBean bean) throws SQLException {
		String sql = "UPDATE prenotazione SET qr='' WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("terminaPrenotazione" + statement);
			statement.setInt(1, bean.getId());
			statement.executeUpdate();
			con.commit();
		}
	}

	/**
	 * <br>
	 * NON UTILIZZARE QUESTO METODO</br>
	 * 
	 * @category Inserisce una prenotazione nel database
	 * 
	 * @param bean Prenotazione da inserire
	 */
	@Override
	public void doSave(PrenotazioneBean bean) throws SQLException {
		String sql = "INSERT INTO prenotazione(dataPrenotazione,fasciaOraria,qR,utenteEmail,postazioneId,prezzo) VALUES(?,?,?,?,?,?)";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("doSave" + statement);
			statement.setString(1, bean.getData());
			statement.setString(2, bean.getFasciaOraria());
			statement.setString(3, bean.getQr());
			statement.setString(4, bean.getUtenteEmail());
			statement.setInt(5, bean.getPostazioneId());
			statement.setFloat(6, bean.getPrezzo());
			statement.executeUpdate();
			con.commit();
		}

	}

	@Override
	public void doUpdate(PrenotazioneBean bean, Integer chiave) throws SQLException {
		// TODO Auto-generated method stub

	}

	/**
	 * @category Elimina una prenotazione dal database
	 * 
	 * @param id id della prenotazione da cancellare
	 */
	@Override
	public void doDelete(Integer id) throws SQLException {
		String sql = "DELETE FROM prenotazione WHERE id=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("doDelete " + statement);
			statement.setInt(1, id);
			statement.executeUpdate();
			con.commit();
		}

	}

	/**
	 * @category Salva una prenotazione con le annesse periferiche
	 * 
	 * @param prenotazione la prenotazione da salvare
	 * 
	 * @param periferiche  le periferiche che vengono aggiunte alla prenotazione
	 */
	public void prenotaConPeriferiche(PrenotazioneBean prenotazione, ArrayList<PerifericaBean> periferiche)
			throws SQLException {
		doSave(prenotazione);
		ArrayList<PrenotazioneBean> prenotazioni = (ArrayList<PrenotazioneBean>) doRetrieveAll();
		PrenotazioneBean prenotazioneId = prenotazioni.get(prenotazioni.size() - 1);
		String sql = "INSERT INTO prenotazione_periferica VALUES(?,?)";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			for (PerifericaBean perifericaBean : periferiche) {
				System.out.println("addPeriferiche " + statement);
				statement.setInt(1, prenotazioneId.getId());
				statement.setString(2, perifericaBean.getNome());
				statement.executeUpdate();
				con.commit();
			}

		}
	}

}
