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
 * @category Permette di effettuare gli accessi al database della tabella categoria
 * 
 * */
public class CategoriaDAO implements ModelInterface<CategoriaBean, String>{

	/**
	 * @category ritorna, se presente nel database, una categoria col nome inserito
	 * 
	 * @param nome il nome della categoria da ricercare
	 * */
	@Override
	public CategoriaBean doRetrieveByKey(String nome) throws SQLException {
		CategoriaBean bean=new CategoriaBean();
		String sql="SELECT * FROM categoria WHERE nome=?";
		
		try(Connection con=DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql);){
			
			statement.setString(1,nome);
			System.out.println("DoRetriveByKey="+statement.toString());
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setTipoGenerico(rs.getString("tipoGenerico"));
				bean.setImmagine(rs.getString("immagine"));
			}
		}
		
		return bean;
		
	}

	
	/**
	 * @category ritorna tutte le categorie presenti nel database
	 * */
	@Override
	public Collection<CategoriaBean> doRetrieveAll() throws SQLException {
		
		String sql="SELECT * FROM categoria";
	
		
		ArrayList<CategoriaBean> collection=new ArrayList<CategoriaBean>();
		
		try(Connection con=DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql);){
			
			System.out.println("DoRetriveAll");
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				CategoriaBean bean=new CategoriaBean();
				
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setTipoGenerico(rs.getString("tipoGenerico"));
				bean.setImmagine(rs.getString("immagine"));
				collection.add(bean);
			}
		}
		return collection;
	}

	/**
	 * @category permette di salvare la categoria all'interno del database
	 * */
	@Override
	public void doSave(CategoriaBean bean) throws SQLException {
		
		String sql="INSERT INTO categoria values(?,?,?,?,?)";
		
		try(Connection con =DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql)){
			
			statement.setString(1, bean.getNome());
			statement.setString(2, bean.getTipoGenerico());
			statement.setDouble(3, bean.getPrezzo());
			statement.setString(4, bean.getDescrizione());
			statement.setString(5, bean.getImmagine());
			System.out.println("doSave="+statement);
			statement.executeUpdate();
			con.commit();
		}
	}

	/**
	 * @category permette di aggiornare una categoria
	 * 
	 * @param chiave è la chiave per selezionare la riga da aggiornare
	 * */
	@Override
	public void doUpdate(CategoriaBean bean, String chiave) throws SQLException {
		
	}

	/**
	 * @category permette di eliminare una categoria dal database
	 * 
	 * @param chiave è la chiave per selezionare la riga da eliminare
	 * */
	@Override
	public void doDelete(String chiave) throws SQLException {
		String sql="DELETE FROM categoria WHERE nome=?";
		
		try(Connection con=DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql)){
			statement.setString(1, chiave);
			System.out.println("doDelete="+statement);
			statement.executeQuery();
			con.commit();
		}
		
	}

	
}
