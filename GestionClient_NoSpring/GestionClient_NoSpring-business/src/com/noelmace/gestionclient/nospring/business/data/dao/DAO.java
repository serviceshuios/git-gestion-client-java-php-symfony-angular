package com.noelmace.gestionclient.nospring.business.data.dao;

import java.sql.SQLException;
import java.util.List;

import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;

/**
 * @author Noël Macé (noelmace.com)
 * 
 * interface générique pour la DAO (CRUD)
 *
 * @param <T> type d'entité à gérer
 */
public interface DAO<T> {
	
	/**
	 * mise en persistance d'un objet
	 * @param objet à persister
	 * @throws PersistenceException
	 */
	public void create(T obj) throws PersistenceException;
	
	/**
	 * mise à jour d'un objet persistant
	 * @param objet à mettre à jour
	 * @throws PersistenceException
	 */
	public void update(T obj) throws PersistenceException;
	
	/**
	 * Récupération d'un objet à partir de la persistance par son id
	 * @param identifiant de l'objet
	 * @return l'objet persistant
	 * @throws PersistenceException
	 */
	public T find(Long id) throws PersistenceException;
		
	/**
	 * Récupérer tous les objets mis en persistance pour cette classe
	 * @return liste des objets
	 * @throws PersistenceException
	 */
	public List<T> findAll() throws PersistenceException;
	
	/**
	 * Passer un objet de persistant à transiant
	 * ie. supprimer l'objet de la persistance
	 * @param objet persistant à rendre transiant
	 * @throws PersistenceException
	 */
	public void delete(T obj) throws PersistenceException;

}
