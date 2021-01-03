package model;

import java.sql.SQLException;
import java.util.Collection;

/**
 * 
 * 
 *
 * L'interfaccia per la creazione di tutti i model
 *
 * @param <T> Il tipo di bean a cui il model fa riferimento
 * @param <U> La chiave da utilizzare per identificare unovacemnte un bean all'inetrno del database
 */
public interface ModelInterface <T,U>{
	/**
	 * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean
	 * @param type La chiave primaria dell'elemento della tabella a cui facciamo riferimento
	 * @return Il bean dell'elemento preso dal database 
	 * @throws SQLException
	 */
	public T doRetrieveByKey(U type) throws SQLException;
	
	/**
	 * Metodo da utilizzare per prelevare tutte le entry di un elemento in una tabella
	 * @param order l'ordine in cui fare restiturire gli elementi ASC|DESC (ancora non funzionante)
	 * @return Una collezione di bean contenenti tutte le entry non duplicate della tabella 
	 * @throws SQLException
	 */
	public Collection<T> doRetrieveAll() throws SQLException;
	
	/**
	 * Metodo utilizzato per salvare i valori contenuti in un bean all'interno di una tabella
	 * @param bean il bean da salvare nel database
	 * @throws SQLException
	 */
	public int doSave(T bean) throws SQLException;
	
	/**
	 * Metodo utilizzato per aggiornare i valori di un bean all'interno del database
	 * @param bean il bean da aggiornare
	 * @throws SQLException
	 */
	public boolean doUpdate(T bean,U chiave) throws SQLException;
	
	/**
	 * Metodo utilizzato per eliminare una riga identificata da un bean all'interno del databse
	 * @param bean il bean contenente i valori utilizzati per eliminare la riga dal database
	 * @throws SQLException
	 */
	public boolean doDelete(U chiave) throws SQLException;
	
	
}