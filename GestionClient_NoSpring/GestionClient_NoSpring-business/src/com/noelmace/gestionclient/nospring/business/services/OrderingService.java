package com.noelmace.gestionclient.nospring.business.services;

import java.sql.SQLException;
import java.util.List;

import com.noelmace.gestionclient.nospring.business.data.dao.DAO;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.entities.*;
import com.noelmace.gestionclient.nospring.business.services.exception.ServiceLayerException;


/**
 * @author Noël Macé (noelmace.com)
 *
 * Service de gestion des commandes
 */
public class OrderingService extends AbstractService {
	
	/**
	 * @see com.noelmace.gestionclient.nospring.business.services.AbstractService;
	 */
	public OrderingService(){
		super();
	}

	/**
	 * @see com.noelmace.gestionclient.nospring.business.services.AbstractService;
	 * @param daoBdc
	 * @param daoClient
	 */
	public OrderingService(DAO<BonDeCommande> daoBdc, DAO<Client> daoClient) {
		super(daoBdc, daoClient);
	}

	/**
	 * Création d'une nouvelle commande
	 * @param clientId id (unique) du client auquel associer la commande
	 * dans le cas où cet Id ne corresponde à aucun client, le bon de commande ne sera associé à aucun client
	 * @param ref référence du bon de commande
	 * @param price prix du bon de commande
	 * @throws SQLException 
	 */
	// TODO : gestion d'exeception
	public Long makeOrder(Long clientId, String ref, Double price) throws ServiceLayerException{
		Client client = null;
		Long id = null;
		try {
			BonDeCommande bdc = new BonDeCommande(ref, price, null);
			client = daoClient.find(clientId);
			if(client != null){
				bdc.setClient(client);
				daoBdc.create(bdc);
				id = bdc.getId();
			} else {
				throw new ServiceLayerException("il n'existe pas de client pour cet id");
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceLayerException(PERSISTENCE_ERROR_MSG, e);
		}
		return id;
	}

	/**
	 * Récupération de tous les bons de commande
	 * @return liste des bons de commande
	 * @throws ServiceLayerException
	 */
	public List<BonDeCommande> list() throws ServiceLayerException {
		List<BonDeCommande> rslt = null;
		try {
			rslt = daoBdc.findAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceLayerException(PERSISTENCE_ERROR_MSG, e);
		}
		return rslt;
	}

	/**
	 * Suppression d'un bon de commande
	 * @param id identifiant (unique) du bon de commande
	 * @throws ServiceLayerException
	 */
	public void delete(Long id) throws ServiceLayerException {
		BonDeCommande bdc = null;
		try {
			bdc = daoBdc.find(id);
			daoBdc.delete(bdc);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceLayerException(PERSISTENCE_ERROR_MSG, e);
		}
	}

	/**
	 * Mise à jour d'un bon de commande pré-existant
	 * @param id identifiant (unique) du bon de commande
	 * @param ref nouvelle référence du bon de commande
	 * @param price nouveau prix du bon de commande
	 * @throws ServiceLayerException
	 */
	public void update(Long id, String ref, Double price) throws ServiceLayerException {
		BonDeCommande bdc = null;
		try {
			bdc = daoBdc.find(id);
			bdc.setRef(ref);
			bdc.setPrice(price);
			daoBdc.update(bdc);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceLayerException(PERSISTENCE_ERROR_MSG, e);
		}
	}
}
