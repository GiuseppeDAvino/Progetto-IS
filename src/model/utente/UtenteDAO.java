package model.utente;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.connessione.DriverManagerConnectionPool;
import model.utente.UtenteBean.Ruolo;

/**
 * @category Permette di effettuare gli accessi al database della tabella utente
 * 
 */
public class UtenteDAO implements ModelInterface<UtenteBean, String> {
	/**
	 * @category ritorna, se presente nel database, una utente con l'email inserito
	 * 
	 * @param email l'e-mail dell'utente da ricercare
	 */
	@Override
	public UtenteBean doRetrieveByKey(String email) throws SQLException {
		UtenteBean bean = new UtenteBean();
		String sql = "SELECT * FROM utente WHERE email=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, email);
			System.out.println("DoRetrieveByKey=" + statement);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getBytes("passw"));
				bean.setStato(rs.getBoolean("stato"));
				bean.setCodiceVerifica(rs.getString("codiceVerifica"));

				switch (rs.getString("ruolo")) {

				case "cliente":
					bean.setRuolo(Ruolo.cliente);
					break;

				case "titolare":
					bean.setRuolo(Ruolo.titolare);
					break;

				case "gestore":
					bean.setRuolo(Ruolo.gestore);
					break;
				}

			}
			return bean;
		}

	}

	/**
	 * @category ritorna tutte gli utenti presenti nel database
	 * 
	 */
	@Override
	public Collection<UtenteBean> doRetrieveAll() throws SQLException {
		String sql = "SELECT * FROM utente";

		ArrayList<UtenteBean> collection = new ArrayList<UtenteBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoRetrieveAll");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				UtenteBean bean = new UtenteBean();
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("passw"));
				bean.setStato(rs.getBoolean("stato"));
				bean.setCodiceVerifica(rs.getString("codiceVerifica"));
				switch (rs.getString("ruolo")) {

				case "cliente":
					bean.setRuolo(Ruolo.cliente);
					break;

				case "titolare":
					bean.setRuolo(Ruolo.titolare);
					break;

				case "gestore":
					bean.setRuolo(Ruolo.gestore);
					break;
				}
				collection.add(bean);
			}

		}
		return collection;

	}

	/**
	 * @category ritorna tutti gli utenti in ordine di numero di prenotazioni
	 * 
	 */
	public Collection<UtenteBean> doRetrieveAllPerPrenotazioni() throws SQLException {
		String sql = "select u.email,u.nome,u.cognome,u.username from utente u,prenotazione p where u.email=p.utenteEmail order by(\r\n"
				+ "	Select count(*) from utente u,prenotazione p where u.email=p.utenteEmail group by u.email)";

		ArrayList<UtenteBean> collection = new ArrayList<UtenteBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoRetrieveAll");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				UtenteBean bean = new UtenteBean();
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("passw"));
				bean.setStato(rs.getBoolean("stato"));
				bean.setCodiceVerifica(rs.getString("codiceVerifica"));
				switch (rs.getString("ruolo")) {

				case "cliente":
					bean.setRuolo(Ruolo.cliente);
					break;

				case "titolare":
					bean.setRuolo(Ruolo.titolare);
					break;

				case "gestore":
					bean.setRuolo(Ruolo.gestore);
					break;
				}
				collection.add(bean);
			}

		}
		return collection;

	}

	/**
	 * @category permette di salvare l'utente all'interno del database <br>
	 * 
	 */
	@Override
	public void doSave(UtenteBean bean) throws SQLException {
		String sql = "INSERT INTO utente VALUES(?,?,?,?,?,?,?,?,?)";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, bean.getEmail());
			statement.setString(2, bean.getNome());
			statement.setString(3, bean.getCognome());
			statement.setString(4, bean.getUsername());
			statement.setBytes(5, bean.getPassword());
			statement.setString(6, bean.getRuolo().name());
			statement.setBoolean(7, false);
			statement.setString(8, bean.getCodiceVerifica());
			statement.setString(9, bean.getImg());
			System.out.println("doSave=" + statement);
			statement.executeUpdate();
			con.commit();
		}
	}

	/**
	 * @category permette di aggiornare l'utente all'interno del database <br>
	 * 
	 * @param bean  l'utente da cambiare
	 * @param email l'email dell'utente da modificare
	 * 
	 */
	@Override
	public void doUpdate(UtenteBean bean, String email) throws SQLException {
		String sql = "UPDATE utente SET nome=?,cognome=?,username=?,passw=?,ruolo=?,stato=?,codiceVerifica=?,immagine=? WHERE email=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, bean.getNome());
			statement.setString(2, bean.getCognome());
			statement.setString(3, bean.getUsername());
			statement.setBytes(4, bean.getPassword());
			statement.setString(5, bean.getRuolo().name());
			statement.setBoolean(6, bean.isStato());
			statement.setString(7, bean.getCodiceVerifica());
			statement.setString(8, bean.getImg());
			statement.setString(9, email);
			System.out.println("doUpdate=" + statement);
			statement.executeUpdate();
			con.commit();
		}

	}

	/**
	 * @category permette di eliminare l'utente all'interno del database <br>
	 * 
	 * @param email l'email dell'utente da cambiare
	 */
	@Override
	public void doDelete(String email) throws SQLException {
		String sql = "DELETE FROM utente WHERE email=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, email);
			System.out.println("doUpdate=" + statement);
			statement.executeUpdate();
			con.commit();
		}

	}

	/**
	 * @category Ritorna la password codificata
	 * 
	 * @param password la password da codificare
	 */

	private byte[] codificaPassword(String password) throws NoSuchAlgorithmException {

		MessageDigest md;
		md = MessageDigest.getInstance("SHA-256");
		byte arr[] = md.digest(password.getBytes());
		return arr;

	}

	/**
	 * @category Controlla se l'email e la password corrispondono ad un utente nel
	 *           db
	 * 
	 * @param email    email dell'utente
	 * 
	 * @param password password dell'utente
	 * 
	 * @return ritorna true se i parametri corrispondono ad un utente nel db, false
	 *         altrimenti
	 */
	public boolean controllaEmailPassword(String email, String password) {
		String sql = "SELECT * FROM utente WHERE email=? AND password=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, email);
			statement.setBytes(2, codificaPassword(password));
			System.out.println("ControllaEmailPassword" + statement);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @category Controlla se l'email inserita esiste già nel db
	 * 
	 * @param email email da controllare
	 * 
	 * @return ritorna true se l'email esiste nel db, false altrimenti
	 */
	public boolean esisteEmail(String email) {
		String sql = "SELECT * FROM utente WHERE email=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, email);
			System.out.println("esisteEmail" + statement);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * @category Controlla se l'username inserito esiste già nel db
	 * 
	 * @param username username da controllare
	 * 
	 * @return ritorna true se l'username esiste nel db, false altrimenti
	 */
	public boolean esisteUsername(String username) {
		String sql = "SELECT * FROM utente WHERE username=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, username);
			System.out.println("esisteUsername" + statement);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * @category controlla il nome/cognome inserito
	 * 
	 * @param stringa stringa da controllare
	 */
	public Boolean validaNomeCognome(String stringa) {
		String regex = "[a-zA-Z ‘àèìòù]{3,40}";
		return stringa.matches(regex);
	}

	/**
	 * @category controlla l'username inserito
	 * 
	 * @param username username da controllare
	 */
	public Boolean validaUsername(String username) {
		String regex = "[a-zA-Z ‘àèìòù0-9]{3,40}";
		return username.matches(regex);
	}

	/**
	 * @category controlla l' email inserito
	 * 
	 * @param email email da controllare
	 */
	public Boolean validaEmail(String email) {
		String regex = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
		return email.matches(regex);
	}

	/**
	 * @category controlla la passwordinserito
	 * 
	 * @param password password da controllare
	 */
	public Boolean validaPassword(String password) {
		String regex = "[a-zA-Z0-9]{7,20}";
		return password.matches(regex);
	}
}
