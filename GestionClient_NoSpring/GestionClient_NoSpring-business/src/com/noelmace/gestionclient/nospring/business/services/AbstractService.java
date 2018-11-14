package com.noelmace.gestionclient.nospring.business.services;

import com.noelmace.gestionclient.nospring.business.data.dao.BdcDAO;
import com.noelmace.gestionclient.nospring.business.data.dao.ClientDAO;
import com.noelmace.gestionclient.nospring.business.data.dao.DAO;
import com.noelmace.gestionclient.nospring.business.entities.BonDeCommande;
import com.noelmace.gestionclient.nospring.business.entities.Client;

/**
 * Définition générale des services.
 * Définie l'injection des dao et certaines constantes globales.
 * @author Noël Macé (noelmace.com)
 *
 */
public abstract class AbstractService {
	
	/**
	 * Message d'erreur en cas d'impossibilité de mise en persistance
	 */
	protected static final String PERSISTENCE_ERROR_MSG = "The informations could not be saved";
	
	/**
	 * Message d'erreur en cas d'impossibilité de connection à la persistance de données
	 */
	public static final String PERSISTENCE_CONNECTION_ERROR_MSG = "Connection to persistence solution was impossible";


	/**
	 * DAO pour les bons de commande
	 * injection à réaliser par mutateur ou constructeur
	 * @see com.noelmace.gestionclient.spring.business.entities.BonDeCommande
	 */
	protected DAO<BonDeCommande> daoBdc;


	/**
	 * DAO pour les clients
	 * injection à réaliser par mutateur ou constructeur
	 * @see com.noelmace.gestionclient.spring.business.entities.Client
	 */
	protected DAO<Client> daoClient;
	
	
	
	/**
	 * constructeur par défaut, pour Spring
	 */
	public AbstractService() {
	}

	/**
	 * Constructeur pour injection de dépendance sans Spring (cf projet IoC)
	 * @param daoBdc instance concrète de dao pour les Bons de commande
	 * @param daoClient instance concrète de dao pour les Clients
	 */
	public AbstractService(DAO<BonDeCommande> daoBdc, DAO<Client> daoClient) {
		this.daoBdc = daoBdc;
		this.daoClient = daoClient;
	}

	// ========== Accesseurs et mutateurs ==========

	/**
	 * Mutateur de la DAO Bon de commande, à utiliser pour l'injection de dépendance via spring
	 * @param daoBdc
	 */
	public void setDaoBdc(BdcDAO daoBdc) {
		this.daoBdc = daoBdc;
	}

	/**
	 * Mutateur de la DAO Client, à utiliser pour l'injection de dépendance via spring
	 * @param daoClient
	 */
	public void setDaoClient(ClientDAO daoClient) {
		this.daoClient = daoClient;
	}
	
}
