package com.noelmace.gestionclient.spring.dao.hds;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.noelmace.gestionclient.spring.business.dao.DAO;


/**
 * @author Noël Macé (noelmace.com)
 * 
 * classe abstraite définissant les méthodes au comportement commun quelque soit le type d'entité manipulé
 *
 * @param <T> Entité visée
 */
@Transactional()
public abstract class DAOHds<T> extends HibernateDaoSupport implements DAO<T> {
	
	@Override
	public void create(T obj) throws SQLException {
		getHibernateTemplate().save(obj);
	}

	@Override
	public void update(T obj) throws SQLException {
		getHibernateTemplate().update(obj);
	}

	@Override
	public void delete(T obj) throws SQLException {
		getHibernateTemplate().delete(obj);
	}
	
	public T find(Long id, Class clazz) throws SQLException {
        return (T) getHibernateTemplate().get(clazz, id);
	}
	
	public List findAll(Class clazz) throws SQLException {
		return getHibernateTemplate().find("from "+clazz.getName());
	}
}
