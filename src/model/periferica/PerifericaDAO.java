package model.periferica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.connessione.DriverManagerConnectionPool;
/**
 * @category Permette di effettuare gli accessi al database della tabella periferica
 * 
 * */
public class PerifericaDAO implements ModelInterface<PerifericaBean, String>{
	/**
	 * @category ritorna, se presente nel database, una periferica con il nome inserito
	 * 
	 * @param nome il nome della periferica da ricercare
	 * */
	@Override
	public PerifericaBean doRetrieveByKey(String nome) throws SQLException {
		PerifericaBean bean=new PerifericaBean();
		String sql="SELECT * FROM periferica WHERE nome=?";
		
		try(Connection con=DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql);){
			statement.setString(1, nome);
			System.out.println("DoRetrieveByKey"+statement);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setTipo(rs.getString("tipo"));
			}
		}
		return bean;

	}

	/**
	 * @category ritorna tutte le periferiche presenti nel database
	 * 
	 * */
	@Override
	public Collection<PerifericaBean> doRetrieveAll() throws SQLException {
		String sql="SELECT * FROM periferica";
		
		ArrayList<PerifericaBean> collection=new ArrayList<PerifericaBean>();
		
		try(Connection con=DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql);){
			System.out.println("DoRetriveAll");
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				PerifericaBean bean=new PerifericaBean();
				
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setTipo(rs.getString("tipo"));
				collection.add(bean);
			}
		}
		return collection;
		
	}
	/**
	 * @category permette di salvare la periferica all'interno del database
	 * */
	@Override
	public void doSave(PerifericaBean bean) throws SQLException {

		String sql="INSERT INTO periferica values(?,?,?,?)";
		try(Connection con =DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql)){
			 statement.setString(1, bean.getTipo());
			 statement.setString(2, bean.getNome());
			 statement.setInt(3, bean.getQuantita());
			 statement.setFloat(4, bean.getPrezzo());
			 System.out.println("doSave="+statement);
			 statement.executeUpdate();
			 con.commit();
		}
	
	}

	/**
	 * @category permette di aggiornare una periferica
	 * 
	 * @param chiave è la chiave per selezionare la riga da aggiornare
	 * */
	@Override
	public void doUpdate(PerifericaBean bean,String nome) throws SQLException {
		String sql="UPDATE periferica SET tipo=?,nome=?,quantita=?,prezzo=? WHERE nome=?";
		
		
		try(Connection con=DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql);){
			statement.setString(1, bean.getTipo());
			statement.setString(2, bean.getNome());
			statement.setInt(3, bean.getQuantita());
			statement.setDouble(4, bean.getPrezzo());
			statement.setString(5, nome);
			System.out.println("doUpdate="+statement);
			statement.executeUpdate();
			con.commit();
		}
		
	}

	/**
	 * @category permette di eliminare una periferica dal database
	 * 
	 * @param chiave è la chiave per selezionare la riga da eliminare
	 * */
	@Override
	public void doDelete(String chiave) throws SQLException {
		String sql="DELETE FROM periferica WHERE nome=?";
		
		try(Connection con=DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql)){
			statement.setString(1, chiave);
			System.out.println("doDelete="+statement);
			statement.executeQuery();
			con.commit();
		}
	}

	
}
