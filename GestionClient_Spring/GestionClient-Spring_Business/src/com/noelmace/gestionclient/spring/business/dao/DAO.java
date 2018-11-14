package com.noelmace.gestionclient.spring.business.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Noël Macé (noelmace.com)
 * 
 *         interface de DAO
 *         
 *         Pour plus de simplicité dans l'apprentissage, nous n'aborderons ici aucune recherche spécifique type "findByName"
 *         d'où la généricité maximum. Dans le cas contraire, nous aurions du mettre en place une nouvelle interface pour chaque entité nécessitant
 *         une DAO (ClientDAO par exemple) afin de permettre les cas spécifiques.
 * 
 * @param <T>
 *            Entité visée
 */
public interface DAO<T> {

	/**
	 * Récupération d'un élément persistant
	 * 
	 * @param id
	 *            identifiant de l'entité à récupérer
	 * @return object T récupéré (null si aucun)
	 * @throws SQLException
	 */
	public abstract T find(Long id) throws SQLException;

	/**
	 * Récupération de l'ensemble des éléments persistant de type T
	 * 
	 * @return liste des éléments (null si aucun)
	 * @throws SQLException
	 */
	public abstract List<T> findAll() throws SQLException;

	/**
	 * Mise en persistance d'un élément T.
	 * 
	 * @param obj
	 *            élément à mettre en persistance
	 * @throws SQLException
	 */
	public abstract void create(T obj) throws SQLException;

	/**
	 * Mise à jour d'un élément persistant
	 * 
	 * @param obj
	 *            élément à mettre en persistance
	 * @throws SQLException
	 */
	public abstract void update(T obj) throws SQLException;

	/**
	 * Annulation de la mise en persistance d'un objet
	 * 
	 * @param obj
	 *            élément à rendre transient
	 * @throws SQLException
	 */
	public abstract void delete(T obj) throws SQLException;

}