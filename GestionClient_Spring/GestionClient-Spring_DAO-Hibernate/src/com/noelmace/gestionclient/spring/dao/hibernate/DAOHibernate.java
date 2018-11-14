package com.noelmace.gestionclient.spring.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.noelmace.gestionclient.spring.business.dao.DAO;


/**
 * @author Noël Macé (noelmace.com)
 * 
 * classe abstraite définissant les méthodes au comportement commun quelque soit le type d'entité manipulé
 *
 * @param <T> Entité visée
 */
public abstract class DAOHibernate<T> implements DAO<T> {
	
	/**
	 * SessionFactory Hibernate à injecter par mutateur
	 */
	protected static SessionFactory sf;
	
	@Override
	public void create(T obj) throws SQLException {
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void update(T obj) throws SQLException {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(T obj) throws SQLException {
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();
		session.close();
	}
	
	public T find(Long id, Class clazz) throws SQLException {
		Session session = sf.openSession();
        session.beginTransaction();
        T obj = (T) session.get(clazz, id);
        session.close();
        return obj;
	}

	public List findAll(Class clazz) throws SQLException {
		Session session = sf.openSession();
		session.beginTransaction();
        @SuppressWarnings("unchecked")
		List result = session.createQuery("from "+clazz.getName()).list();
        session.close();
		return result;
	}

	public static void setSf(SessionFactory sf) {
		DAOHibernate.sf = sf;
	}
}
