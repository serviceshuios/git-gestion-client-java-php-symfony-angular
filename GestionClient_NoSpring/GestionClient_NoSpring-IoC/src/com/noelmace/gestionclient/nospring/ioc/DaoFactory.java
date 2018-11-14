package com.noelmace.gestionclient.nospring.ioc;

import com.noelmace.gestionclient.nospring.business.data.dao.DAO;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceConnectionException;
import com.noelmace.gestionclient.nospring.business.entities.BonDeCommande;
import com.noelmace.gestionclient.nospring.business.entities.Client;
import com.noelmace.gestionclient.nospring.hibernate.dao.BdcDAOHibernate;
import com.noelmace.gestionclient.nospring.hibernate.dao.ClientDAOHibernate;
import com.noelmace.gestionclient.nospring.jdbc.dao.BdcDAOJdbc;
import com.noelmace.gestionclient.nospring.jdbc.dao.ClientDAOJdbc;

/**
 * Fabrique pour les DAO
 * 
 * Permet de répondre à la suppression de dépendances aux classes concrêtes de dao dans les services, en "délocalisant" et centralisant l'instanciation
 * de celles-ci. Ainsi, tout changement de composant (passage de Jdbc à Hibernate et inversement) ne décessitera que la changement de deux
 * lignes de code dans cette classe, plutôt que de nombreuses instanciation dans de nombreuses classes différents.
 * 
 * @author Noël Macé (noelmace.com)
 *
 */
public abstract class DaoFactory {
	
	private static DAO<Client> clientDao;
	private static DAO<BonDeCommande> bdcDao;
	
	/**
	 * Génération d'une instance de classe concrête implémentant DAO<Client>
	 * @return l'instance
	 * @throws PersistenceConnectionException
	 */
	public static DAO<Client> getClientDao() throws PersistenceConnectionException {
		// cette vérification permet de garantir que cet élément soit un singleton
		if(clientDao == null){
			// pour passer d'un composant de persistance à l'autre, il suffira de remplacer la ligne de code suivante par celle commentée ci-dessous
			//clientDao = new ClientDAOHibernate();
			clientDao = new ClientDAOJdbc();
		}
		return clientDao;
	}
	
	/**
	 * Génération d'une instance de classe concrête implémentant DAO<BonDeCommande>
	 * @return l'instance
	 * @throws PersistenceConnectionException
	 */
	public static DAO<BonDeCommande> getBdcDao() throws PersistenceConnectionException {
		// cette vérifiaction permet de garantir que cet élément soit un singleton
		if(bdcDao == null){
			// pour passer d'un composant de persistance à l'autre, il suffira de remplacer la ligne de code suivante par celle commentée ci-dessous
			//bdcDao = new BdcDAOHibernate();
			bdcDao = new BdcDAOJdbc();

		}
		return bdcDao;
	}
	
	

}
