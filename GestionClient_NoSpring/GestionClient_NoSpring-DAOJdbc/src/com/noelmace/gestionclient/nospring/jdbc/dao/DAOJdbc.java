package com.noelmace.gestionclient.nospring.jdbc.dao;

import java.sql.Connection;

import com.noelmace.gestionclient.nospring.business.data.dao.DAO;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceConnectionException;
import com.noelmace.gestionclient.nospring.jdbc.ConnectionManager;

public abstract class DAOJdbc<T> implements DAO<T> {

	/**
	 * Connection à la base de donnée (sigleton délivré par ConnectionManager)
	 */
	protected Connection cnx;

	/**
	 * Constructeur par défaut, permet de récupérer la connection
	 * essentiellement présent pour la gestion d'exceptions
	 * @throws PersistenceConnectionException
	 */
	public DAOJdbc() throws PersistenceConnectionException {
		cnx = ConnectionManager.getConnection();
	}
	
	
	
	
}
