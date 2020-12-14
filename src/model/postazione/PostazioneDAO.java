package model.postazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ModelInterface;
import model.connessione.DriverManagerConnectionPool;
/**
 * @category Permette di effettuare gli accessi al database della tabella postazione
 * 
 * */
public class PostazioneDAO implements ModelInterface<PostazioneBean, Integer>{
	/**
	 * @category ritorna, se presente nel database, una postazione con l'id inserito
	 * 
	 * @param id l'id della postazione da ricercare
	 * */
	@Override
	public PostazioneBean doRetrieveByKey(Integer id) throws SQLException {
		PostazioneBean bean=new PostazioneBean();
		String sql="SELECT * FROM periferica WHERE id=?"; 
		try(Connection con=DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql)){
			statement.setInt(1, id);
			System.out.println("DoRetrieveByKey"+statement);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setCategoria(rs.getString("nomeCategoria"));
				bean.setDisponibile(rs.getBoolean("isDisponibile"));
			}
		}
		return bean;
	}

	/**
	 * @category ritorna tutte le postazioni presenti nel database
	 * 
	 * */
	@Override
	public Collection<PostazioneBean> doRetrieveAll() throws SQLException {
		String sql="SELECT * FROM postazione";
		ArrayList<PostazioneBean> collection=new ArrayList<PostazioneBean>();
		
		try(Connection con=DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql);){
			System.out.println("DoRetriveAll"+statement);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				PostazioneBean bean=new PostazioneBean();
				bean.setId(rs.getInt("id"));
				bean.setCategoria(rs.getString("nomeCategoria"));
				bean.setDisponibile(rs.getBoolean("isDisponibile"));
			}
		}
		return collection;

	}
	
	/**
	 * @category Permette di cambiare la disponibilit� di una certa postazione
	 * 
	 * @param postazione la postazione da cui cambiare la disponibilit�
	 * */
	public void cambiaDisponibilit�(PostazioneBean postazione) throws SQLException{
		String sql="UPDATE postazione SET isDisponibile='?' WHERE id=?";
		try(Connection con=DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql);){
			System.out.println("cambiaDisponibilit� della postazione "+postazione.getId());
			statement.setBoolean(1,!postazione.isDisponibile());
			statement.setInt(2, postazione.getId());
			statement.executeUpdate();
			con.commit();
		}
	}
	
	/**
	 * @category permette di salvare la postazione all'interno del database
	 * 
	 * @param bean bisogna passare un bean contenente la categoria
	 * */
	@Override
	public void doSave(PostazioneBean bean) throws SQLException {
		String sql="INSERT INTO postazione(nomeCategoria) VALUES(?)";
		try(Connection con=DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql);){
			System.out.println("DoSave"+statement);
			statement.setString(1, bean.getCategoria());
			statement.executeUpdate();
			con.commit();
		}
		
	}

	/**
	 * @category permette di modificare la categoria di una postazione
	 * 
	 * @param bean bean da cui verr� presa la categoria
	 * 
	 * @param chiave l'id della postazione da modificare
	 * */
	@Override
	public void doUpdate(PostazioneBean bean, Integer chiave) throws SQLException {
		String sql="UPDATE postazione SET nomeCategoria=? WHERE id=?";
		try(Connection con=DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql);){
			System.out.println("DoUpdate"+statement);
			statement.setString(1,bean.getCategoria());
			statement.setInt(2, chiave);
			statement.executeUpdate();
			con.commit();
		}
		
	}

	@Override
	public void doDelete(Integer chiave) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
