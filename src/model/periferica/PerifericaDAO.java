package model.periferica;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.connessione.DriverManagerConnectionPool;

/**
 *  Permette di effettuare gli accessi al database della tabella
 *           periferica
 * 
 */   
public class PerifericaDAO implements ModelInterface<PerifericaBean, String> {
	/**
	 *  ritorna, se presente nel database, una periferica con il nome
	 *           inserito
	 * 
	 * @param nome il nome della periferica da ricercare
	 */
	@Override
	public PerifericaBean doRetrieveByKey(String nome) {
		PerifericaBean bean = new PerifericaBean();
		String sql = "SELECT * FROM periferica WHERE nome=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, nome);
			System.out.println("DoRetrieveByKey" + statement);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setTipo(rs.getString("tipo"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 *  ritorna tutte le periferiche presenti nel database
	 * 
	 */
	@Override
	public Collection<PerifericaBean> doRetrieveAll(){
		String sql = "SELECT * FROM periferica";

		ArrayList<PerifericaBean> collection = new ArrayList<PerifericaBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			System.out.println("DoRetrieveAll");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				PerifericaBean bean = new PerifericaBean();

				bean.setNome(rs.getString("nome"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setTipo(rs.getString("tipo"));
				collection.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return collection;
	}
	/**
	 *  ritorna il numero di periferiche prenotate per una data e una fascia oraria
	 * 
	 * @param data La data delle prenotazioni
	 * 
	 * @param fasciaOraria la fascia oraria delle prenotazioni
	 * 
	 * @param nome Il nome della periferica
	 * */
	public int prenotate(Date data,String fasciaOraria,String nome) {
		String sql="select Count(*) as numero from periferica p, prenotazione pr, prenotazione_periferica pp\r\n" + 
				" 	where p.nome=? AND p.nome=pp.perifericaNome AND p.id=pp.prenotazioneId\r\n" + 
				"	AND p.dataPrenotazione=? AND p.fasciaOraria=?";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, nome);
			statement.setDate(2, data);
			statement.setString(3, fasciaOraria);
			System.out.println("prenotate"+statement);
			ResultSet rs=statement.executeQuery();
			rs.next();
			return rs.getInt("numero");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 *  permette di salvare la periferica all'interno del database
	 */
	@Override
	public boolean doSave(PerifericaBean bean) {

		String sql = "INSERT INTO periferica values(?,?,?,?)";
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, bean.getTipo());
			statement.setString(2, bean.getNome());
			statement.setInt(3, bean.getQuantita());
			statement.setFloat(4, bean.getPrezzo());
			System.out.println("doSave=" + statement);
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
	 *  permette di aggiornare una periferica
	 * 
	 * @param chiave è la chiave per selezionare la riga da aggiornare
	 */
	@Override
	public boolean doUpdate(PerifericaBean bean, String nome) {
		String sql = "UPDATE periferica SET tipo=?,nome=?,quantita=?,prezzo=? WHERE nome=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {
			statement.setString(1, bean.getTipo());
			statement.setString(2, bean.getNome());
			statement.setInt(3, bean.getQuantita());
			statement.setDouble(4, bean.getPrezzo());
			statement.setString(5, nome);
			System.out.println("doUpdate=" + statement);
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
	 *  permette di eliminare una periferica dal database
	 * 
	 * @param chiave è la chiave per selezionare la riga da eliminare
	 */
	@Override
	public boolean doDelete(String chiave) {
		String sql = "DELETE FROM periferica WHERE nome=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, chiave);
			System.out.println("doDelete=" + statement);
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
	 *  Restituisce le periferiche disponibili in una certa data ad una certa ora
	 * 
	 * @param data data della prenotazione
	 * @param fasciaOraria fascia oraria della prenotazione
	 * 
	 * @return ArrayList contenente {@link PerifericaBean} con quantità disponibile in quel momento >0 
	 * */
	public Collection<PerifericaBean> perifericheDisponibili(String data,String fasciaOraria) {
		String sql="SELECT p.nome, (p.quantita-(\r\n" + 
				"SELECT COUNT(*) FROM  prenotazione pr, prenotazione_periferica pp\r\n" + 
				"	 WHERE p.nome=pp.perifericaNome AND pr.id=pp.prenotazioneId \r\n" + 
				"		AND pr.dataPrenotazione=? AND pr.fasciaOraria=?)) as quantitaDisponibile,p.tipo,p.prezzo\r\n" + 
				"    FROM periferica p";
		
		ArrayList<PerifericaBean> collection=new ArrayList<PerifericaBean>();
		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, data);
			statement.setString(2, fasciaOraria);
			System.out.println("perifericheDisponibili"+statement);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				PerifericaBean bean=new PerifericaBean();
				bean.setNome(rs.getString("nome"));
				bean.setQuantita(rs.getInt("quantitaDisponibile"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setTipo(rs.getString("tipo"));
				if(bean.getQuantita()>0)
					collection.add(bean);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		} 
		return collection;
	}
	
	public Collection<String> doRetrieveAllTipi() {

		String sql = "SELECT DISTINCT tipo FROM periferica";

		ArrayList<String> collection = new ArrayList<String>();

		try (Connection con = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);) {

			System.out.println("DoRetrieveAllTipiGenerici");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				PerifericaBean bean = new PerifericaBean();
				bean.setTipo(rs.getString("tipo"));
				collection.add(bean.getTipo());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return collection;
	}
}
