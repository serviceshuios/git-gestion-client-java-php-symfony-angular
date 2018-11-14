package com.noelmace.gestionclient.nospring.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.noelmace.gestionclient.nospring.business.data.dao.DAO;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceConnectionException;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;

public abstract class DAOHibernate<T> implements DAO<T> {

	/**
	 * singleton, ne doit jamais être accédé directement !
	 */
	private static SessionFactory sf;
	
	/**
	 * accesseur pour le singleton SessionFactory
	 * remplace l'initialisation statique de l'attribut, pour une meilleure gestion des exceptions
	 * @return la sessionfactory
	 * @throws PersistenceConnectionException 
	 */
	protected static synchronized SessionFactory getSf() throws PersistenceConnectionException{
		if(sf == null){
			try {
				Configuration configuration = new Configuration();
				configuration.configure();
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				        configuration.getProperties()).build();
				sf = configuration.buildSessionFactory(serviceRegistry);
			} catch (HibernateException e) {
				e.printStackTrace();
				throw new PersistenceConnectionException();
			}
		}
		return sf;
	}
	

	@Override
	public void create(T obj) throws PersistenceException {
		try {
			Session session = getSf().openSession();
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
	}

	@Override
	public void update(T obj) throws PersistenceException{
		try {
			Session session = getSf().openSession();
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
	}

	@Override
	public void delete(T obj) throws PersistenceException{
		try {
			Session session = getSf().openSession();
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
	}

	protected T find(Long id, Class clazz) throws PersistenceException {
		T obj;
		try {
			Session session = getSf().openSession();
			session.beginTransaction();
			obj = (T) session.get(clazz, id);
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
        return obj;
	}

	protected List<T> findAll(Class clazz) throws PersistenceException {
		List<T> result;
		try {
			Session session = getSf().openSession();
			session.beginTransaction();
			result = session.createQuery("from "+clazz.getName()).list();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
		return result;
	}
	
	protected List<T> findByCriteria(Criteria crit) throws PersistenceException{
		List<T> rslt;
		try {
			rslt = crit.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
		return rslt;
	}
	
}
