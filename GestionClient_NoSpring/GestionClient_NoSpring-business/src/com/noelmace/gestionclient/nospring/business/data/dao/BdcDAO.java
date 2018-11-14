package com.noelmace.gestionclient.nospring.business.data.dao;

import java.util.List;

import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.entities.BonDeCommande;
import com.noelmace.gestionclient.nospring.business.entities.Client;

/**
 * @author Noël Macé (noelmace.com)
 * 
 * interface spécifique à la DAO pour l'entité BonDeCommande
 *
 */
public interface BdcDAO extends DAO<BonDeCommande> {
	
	/**
	 * Recupérer une liste de bons de commande pour une certaine référence
	 * La liste ne devrait, normalement, ne contenir au maximum q'un seul élément, la référence étant en principe unique
	 * Cela n'est cependant pas imposé pour laisser plus de liberté dans l'implémentation
	 * @param ref référence à rechercher
	 * @return liste des bons de commande correspondant
	 * @throws PersistenceException
	 */
	public List<BonDeCommande> find(String ref) throws PersistenceException;
	
	/**
	 * Récupérer la liste des bons de commande d'un Client
	 * permet une association uni-directionnelle BonDeCommande -> Client si besoin
	 * @param client client ayant émis les bons de commande
	 * @return liste des bons de commande correspondant
	 * @throws PersistenceException
	 */
	public List<BonDeCommande> find(Client client) throws PersistenceException;
	
	/**
	 * Récupérer la liste des bons de commande ayant exactement un certain prix
	 * @param price prix à rechercher
	 * @return liste des bons de commande correspondant
	 * @throws PersistenceException
	 */
	public List<BonDeCommande> find(Double price) throws PersistenceException;
	
	/**
	 * Récupérer la liste des bons de commande dont le prix est compris dans un interval
	 * @param priceMin prix minimum
	 * @param priceMax prix maximum
	 * @return liste des bons de commande correspondant
	 * @throws PersistenceException
	 */
	public List<BonDeCommande> find(Double priceMin, Double priceMax) throws PersistenceException;

}
