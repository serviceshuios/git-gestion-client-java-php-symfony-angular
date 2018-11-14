package com.noelmace.gestionclient.nospring.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceConnectionException;

/**
 * "fabrique" pour le singleton Connection
 * 
 * Modification par rapport à la précédente version : nous sommes ici passé d'une classe ConnectionManager en singleton,
 * qui contenait une instance de Connection dans son unique instance, à une classe non instanciable, permettant de "fabriquer"
 * le singleton Connection grâce à la méthode getConnection() (devenue statique).
 * 
 * Nous sommes donc passé de ConnectionManager.getInstance().getConnection() à ConnectionManager.getConnection()
 * 
 * @author Noël Macé (noelmace.com)
 *
 */
public class ConnectionManager {
	
	/**
	 * Singleton de connection à la base de données
	 */
	private static Connection connection;
	
	// paramètres de connection
	
	private final static String LOGIN = "java_test";
	private final static String PASSWORD = "test";
	private final static String HOST = "jdbc:mysql://localhost/";
	private final static String DB = "gestionclient";

	/**
	 * Constructeur privé, afin d'empécher l'instanciation de la classe
	 */
	private ConnectionManager() {
		
	}

	/**
	 * getter pour le singleton de connection à la base de donnée
	 * @return Connection à la base
	 * @throws PersistenceConnectionException
	 */
	public static Connection getConnection() throws PersistenceConnectionException {
		if(connection == null){
			try {
				// chargement du driver MySQL
				Class.forName("com.mysql.jdbc.Driver");
				// connection à la base de donnée
				connection = DriverManager.getConnection(HOST + DB, LOGIN, PASSWORD);
			} catch (ClassNotFoundException | SQLException e) {
				// wrapping d'exceptions
				throw new PersistenceConnectionException();
			}
		}
		return connection;
	}

}
