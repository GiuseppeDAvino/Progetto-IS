package model.utente;

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
 * @category Permette di effettuare gli accessi al database della tabella
 *           utente
 * 
 */
public class UtenteDAO implements ModelInterface<UtenteBean, String>{
	/**
	 * @category ritorna, se presente nel database, una utente con l'email inserito
	 * 
	 * @param email l'e-mail dell'utente da ricercare
	 */
	@Override
	public UtenteBean doRetrieveByKey(String email) throws SQLException {
		UtenteBean bean = new UtenteBean();
		String sql="SELECT * FROM utente WHERE email=?";
		

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, email);
			System.out.println("DoRetrieveByKey="+statement);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("passw"));
				bean.setStato(rs.getBoolean("stato"));
				bean.setCodiceVerifica(rs.getString("codiceVerifica"));
				switch(rs.getString("ruolo")) {
				
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
		String sql="SELECT * FROM utente";
		
		ArrayList<UtenteBean> collection=new ArrayList<UtenteBean>();
		

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoRetrieveAll");
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				UtenteBean bean=new UtenteBean();
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("passw"));
				bean.setStato(rs.getBoolean("stato"));
				bean.setCodiceVerifica(rs.getString("codiceVerifica"));
				switch(rs.getString("ruolo")) {
				
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
		String sql="INSERT INTO utente VALUES(?,?,?,?,?,?,?,?)";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, bean.getEmail());
			statement.setString(2, bean.getNome());
			statement.setString(3, bean.getCognome());
			statement.setString(4, bean.getUsername());
			statement.setBytes(5, bean.getPassword());
			statement.setString(6, bean.getRuolo().name());
			statement.setBoolean(7, false);
			statement.setString(8,bean.getCodiceVerifica());
			System.out.println("doSave="+statement);
			statement.executeUpdate();
			con.commit();
			}
	}
	/**
	 * @category permette di aggiornare l'utente all'interno del database <br>
	 * 
	 * @param bean l'utente da cambiare
	 * @param email l'email dell'utente da modificare
	 *          
	 */
	@Override
	public void doUpdate(UtenteBean bean, String email) throws SQLException {
		String sql="UPDATE utente SET nome=?,cognome=?,username=?,passw=?,ruolo=?,stato=?codiceVerifica=? WHERE email=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, bean.getNome());
			statement.setString(2, bean.getCognome());
			statement.setString(3, bean.getUsername());
			statement.setBytes(4, bean.getPassword());
			statement.setString(5, bean.getRuolo().name());
			statement.setBoolean(6, bean.isStato());
			statement.setString(7, bean.getCodiceVerifica());
			statement.setString(8, email);
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
		String sql="DELETE FROM utente WHERE email=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, email);
			System.out.println("doUpdate=" + statement);
			statement.executeUpdate();
			con.commit();
		}

	}

}
