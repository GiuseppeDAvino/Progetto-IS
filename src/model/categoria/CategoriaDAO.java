package model.categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.connessione.DriverManagerConnectionPool;

/**
 * @category Permette di effettuare gli accessi al database della tabella
 *           categoria
 * 
 */
public class CategoriaDAO implements ModelInterface<CategoriaBean, String> {

	/**
	 * @category ritorna, se presente nel database, una categoria col nome inserito
	 * 
	 * @param nome il nome della categoria da ricercare
	 */
	@Override
	public CategoriaBean doRetrieveByKey(String nome) {
		CategoriaBean bean = new CategoriaBean();
		String sql = "SELECT * FROM categoria WHERE nome=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {

			statement.setString(1, nome);
			System.out.println("DoRetriveByKey=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setTipoGenerico(rs.getString("tipoGenerico"));
				bean.setImmagine(rs.getString("immagine"));
			}
			return bean;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * @category ritorna, se presente nel database, una categoria col tipoGenerico inserito
	 * 
	 * @param nome il tipoGenerico della categoria da ricercare
	 */
	public CategoriaBean doRetrieveByTipoGenerico(String tipoGenerico) {
		CategoriaBean bean = new CategoriaBean();
		String sql = "SELECT * FROM categoria WHERE tipoGenerico=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {

			statement.setString(1, tipoGenerico);
			System.out.println("DoRetriveByKey=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setTipoGenerico(rs.getString("tipoGenerico"));
				bean.setImmagine(rs.getString("immagine"));
			}
			return bean;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * @category ritorna tutte le categorie presenti nel database
	 */
	@Override
	public Collection<CategoriaBean> doRetrieveAll() {

		String sql = "SELECT * FROM categoria";

		ArrayList<CategoriaBean> collection = new ArrayList<CategoriaBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {

			System.out.println("DoRetriveAll");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				CategoriaBean bean = new CategoriaBean();

				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setTipoGenerico(rs.getString("tipoGenerico"));
				bean.setImmagine(rs.getString("immagine"));
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
	 * @category permette di salvare la categoria all'interno del database
	 */
	@Override
	public boolean doSave(CategoriaBean bean) {

		String sql = "INSERT INTO categoria values(?,?,?,?,?)";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {

			statement.setString(1, bean.getNome());
			statement.setString(2, bean.getTipoGenerico());
			statement.setDouble(3, bean.getPrezzo());
			statement.setString(4, bean.getDescrizione());
			statement.setString(5, bean.getImmagine());
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
	public boolean doUpdate(CategoriaBean bean, String chiave) {
		String sql = "UPDATE categoria SET nome=?,tipoGenerico=?,prezzo=?,descrizione=?,immagine=? WHERE nome=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {

			statement.setString(1, bean.getNome());
			statement.setString(2, bean.getTipoGenerico());
			statement.setDouble(3, bean.getPrezzo());
			statement.setString(4, bean.getDescrizione());
			statement.setString(5, bean.getImmagine());
			statement.setString(6, chiave);

			System.out.println("doUpdate=" + statement);
			statement.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @category permette di eliminare una categoria dal database
	 * 
	 * @param chiave è la chiave per selezionare la riga da eliminare
	 */
	@Override
	public boolean doDelete(String chiave) {
		String sql = "DELETE FROM categoria WHERE nome=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, chiave);
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
	 * @category Permette di recuperare le categorie aventi almeno una postazione
	 *           libera in una certa
	 * 
	 * @param data         data della prenotazione
	 * 
	 * @param fasciaOraria fascia oraria della prenotazione
	 * 
	 * @param tipoGenerico tipo della categoria
	 */
	public Collection<CategoriaBean> categorieConPostazioniLibere(String data, String fasciaOraria, String tipoGenerico) {
		String sql = " SELECT * FROM postazione p, categoria c \r\n"
				+ "            WHERE p.isDisponibile=1 AND p.nomeCategoria=c.nome AND c.tipoGenerico=? AND p.id NOT IN(\r\n"
				+ "                    SELECT p.id FROM postazione p,prenotazione pr WHERE \r\n"
				+ "				    p.id=pr.postazioneId AND pr.dataPrenotazione=? AND pr.fasciaOraria=?)";
		ArrayList<CategoriaBean> collection = new ArrayList<CategoriaBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, tipoGenerico);
			statement.setString(2, data);
			statement.setString(3, fasciaOraria);
			System.out.println("categorieConPostazioniLibere" + statement);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				CategoriaBean bean = new CategoriaBean();

				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setTipoGenerico(rs.getString("tipoGenerico"));
				bean.setImmagine(rs.getString("immagine"));
				collection.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return collection;

	}

	public Collection<CategoriaBean> doRetrieveAllTipiGenerici() throws SQLException {

		String sql = "SELECT DISTINCT tipoGenerico FROM categoria";

		ArrayList<CategoriaBean> collection = new ArrayList<CategoriaBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {

			System.out.println("DoRetriveAllTipiGenerici");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				CategoriaBean bean = new CategoriaBean();
				bean.setTipoGenerico(rs.getString("tipoGenerico"));
				collection.add(bean);
			}
		}
		return collection;
	}
}
