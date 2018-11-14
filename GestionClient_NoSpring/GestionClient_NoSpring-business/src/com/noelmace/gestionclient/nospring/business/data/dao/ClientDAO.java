package com.noelmace.gestionclient.nospring.business.data.dao;

import java.util.List;

import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.entities.Client;

/**
 * @author Noël Macé (noelmace.com)
 * 
 * interface spécifique à la DAO pour l'entité client
 *
 */
public interface ClientDAO extends DAO<Client> {

	/**
	 * Récupérer une liste de clients par leurs noms et prénoms
	 * La liste ne devrait normalement ne contenir qu'un élément (le couple nom et prenom étant, en principe, unique)
	 * Cela n'est cependant pas imposé pour laisser plus de liberté dans l'implémentation
	 * @param nom du client
	 * @param prenom du client
	 * @return liste des clients correspondant
	 * @throws PersistenceException
	 */
	public List<Client> find(String nom, String prenom) throws PersistenceException;
	
	/**
	 * Récupérer une liste de clients pour un age précis
	 * @param age des clients à recherche
	 * @return liste des clients correspondant
	 * @throws PersistenceException
	 */
	public List<Client> find(Integer age) throws PersistenceException;
	
	/**
	 * Récupérer une liste de clients dont l'age est dans un certain interval
	 * @param ageMin age minimum
	 * @param ageMax age maximum
	 * @return liste des clients correspondant
	 * @throws PersistenceException
	 */
	public List<Client> find(Integer ageMin, Integer ageMax) throws PersistenceException;
}
