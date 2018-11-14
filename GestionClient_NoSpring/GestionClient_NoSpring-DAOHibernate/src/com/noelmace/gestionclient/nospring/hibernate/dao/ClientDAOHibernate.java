package com.noelmace.gestionclient.nospring.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.noelmace.gestionclient.nospring.business.data.dao.ClientDAO;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.entities.Client;

public class ClientDAOHibernate extends DAOHibernate<Client> implements ClientDAO {

	@Override
	public Client find(Long id) throws PersistenceException {
		return super.find(id, Client.class);
	}

	@Override
	public List<Client> findAll() throws PersistenceException {
		return super.findAll(Client.class);
	}

	@Override
	public List<Client> find(String nom, String prenom) throws PersistenceException {
		List<Client> clients;
		try {
			Session session = getSf().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Client.class);
			crit.add(Restrictions.like("nom", nom));
			crit.add(Restrictions.like("prenom", prenom));
			clients = super.findByCriteria(crit);
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
		return clients;
	}

	@Override
	public List<Client> find(Integer age) throws PersistenceException {
		List<Client> clients;
		try {
			Session session = getSf().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Client.class);
			crit.add(Restrictions.eq("age", age));
			clients = super.findByCriteria(crit);
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
		return clients;
	}

	@Override
	public List<Client> find(Integer ageMin, Integer ageMax) throws PersistenceException {
		List<Client> clients;
		try {
			Session session = getSf().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Client.class);
			crit.add(Restrictions.between("age", ageMin, ageMax));
			clients = super.findByCriteria(crit);
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
		return clients;
	}

}
