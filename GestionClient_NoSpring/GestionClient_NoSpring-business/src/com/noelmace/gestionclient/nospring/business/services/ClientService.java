package com.noelmace.gestionclient.nospring.business.services;

import java.util.List;

import com.noelmace.gestionclient.nospring.business.data.dao.DAO;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.entities.BonDeCommande;
import com.noelmace.gestionclient.nospring.business.entities.Client;
import com.noelmace.gestionclient.nospring.business.services.exception.ServiceLayerException;


public class ClientService extends AbstractService {
	
	/**
	 * @see com.noelmace.gestionclient.nospring.business.services.AbstractService;
	 */
	public ClientService(){
		super();
	}

	/**
	 * @see com.noelmace.gestionclient.nospring.business.services.AbstractService;
	 * @param daoBdc
	 * @param daoClient
	 */
	public ClientService(DAO<BonDeCommande> daoBdc, DAO<Client> daoClient) {
		super(daoBdc, daoClient);
	}

	/**
	 * création d'un nouveau client
	 * @param nom
	 * @param prenom
	 * @throws ServiceLayerException 
	 * @throws SQLException
	 */
	public Long create(Integer age, String nom, String prenom) throws ServiceLayerException {
		Client client = new Client(age, nom, prenom);
		try {
			daoClient.create(client);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceLayerException(PERSISTENCE_ERROR_MSG, e);
		}
		return client.getId();
	}
	
	/**
	 * Récupération de tous les clients
	 * @return
	 * @throws ServiceLayerException
	 */
	public List<Client> list() throws ServiceLayerException {
		List<Client> rslt = null;
		try {
			rslt = daoClient.findAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceLayerException(PERSISTENCE_ERROR_MSG, e);
		}
		return rslt;
	}

	/**
	 * Suppression d'un client par son identifiant
	 * @param id identifiant (unique) du Client
	 * @throws ServiceLayerException
	 */
	public void delete(Long id) throws ServiceLayerException  {
		Client client;
		try {
			client = daoClient.find(id);
			daoClient.delete(client);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceLayerException(PERSISTENCE_ERROR_MSG, e);
		}
	}

	/**
	 * Mise à jour d'un client pré-existant par son identifiant
	 * @param id identifiant (unique) du Client, permettant de désigner le Client à modifier
	 * @param nom nouveau nom du Client
	 * @param prenom nouveau prenom du Client
	 * @throws ServiceLayerException
	 */
	public void update(Long id, String nom, String prenom) throws ServiceLayerException  {
		try {
			Client client = daoClient.find(id);
			client.setNom(nom);
			client.setPrenom(prenom);
			daoClient.update(client);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceLayerException(PERSISTENCE_ERROR_MSG, e);
		}
	}

	/**
	 * Récupérer un Client par son identifiant
	 * @param id identifiant (unique) du Client
	 * @return le client correspondant
	 * @throws ServiceLayerException
	 */
	public Client get(Long id) throws ServiceLayerException {
		Client client = null;
		try {
			client = daoClient.find(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceLayerException(PERSISTENCE_ERROR_MSG, e);
		}
		return client;
	}

}
