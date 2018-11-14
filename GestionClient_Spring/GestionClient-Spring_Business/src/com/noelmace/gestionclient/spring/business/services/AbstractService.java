package com.noelmace.gestionclient.spring.business.services;

import com.noelmace.gestionclient.spring.business.dao.DAO;
import com.noelmace.gestionclient.spring.business.dto.Assembler;
import com.noelmace.gestionclient.spring.business.entities.BonDeCommande;
import com.noelmace.gestionclient.spring.business.entities.Client;

public abstract class AbstractService {

	/**
	 * DAO pour les bons de commande
	 * injection à réaliser par mutateur
	 * @see com.noelmace.gestionclient.spring.business.entities.BonDeCommande
	 */
	protected DAO<BonDeCommande> daoBdc;


	/**
	 * DAO pour les clients
	 * injection à réaliser par mutateur
	 * @see com.noelmace.gestionclient.spring.business.entities.Client
	 */
	protected DAO<Client> daoClient;
	
	protected Assembler assembler;

	// ========== Accesseurs et mutateurs ==========

	/**
	 * Mutateur de la DAO Bon de commande, à utiliser pour l'injection de dépendance
	 * @param daoBdc
	 */
	public void setDaoBdc(DAO<BonDeCommande> daoBdc) {
		this.daoBdc = daoBdc;
	}

	/**
	 * Mutateur de la DAO Client, à utiliser pour l'injection de dépendance
	 * @param daoClient
	 */
	public void setDaoClient(DAO<Client> daoClient) {
		this.daoClient = daoClient;
	}
	
	
	// TODO : injection Spring
	public void setAssembler(Assembler assembler) {
		this.assembler = assembler;
	}
}
