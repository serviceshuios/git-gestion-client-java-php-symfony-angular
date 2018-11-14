package com.noelmace.gestionclient.nospring.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.noelmace.gestionclient.nospring.business.data.dao.BdcDAO;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.entities.BonDeCommande;
import com.noelmace.gestionclient.nospring.business.entities.Client;

public class BdcDAOHibernate extends DAOHibernate<BonDeCommande> implements BdcDAO {

	@Override
	public BonDeCommande find(Long id) throws PersistenceException {
		return super.find(id, BonDeCommande.class);
	}

	@Override
	public List<BonDeCommande> findAll() throws PersistenceException {
		return super.findAll(BonDeCommande.class);
	}

	@Override
	public List<BonDeCommande> find(String ref) throws PersistenceException {
		List<BonDeCommande> bdcs;
		try {
			Session session = getSf().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(BonDeCommande.class);
			crit.add(Restrictions.like("ref", ref));
			bdcs = super.findByCriteria(crit);
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
		return bdcs;
	}

	@Override
	public List<BonDeCommande> find(Client client) throws PersistenceException {
		List<BonDeCommande> bdcs;
		try {
			Session session = getSf().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(BonDeCommande.class);
			crit.add(Restrictions.eq("client", client));
			bdcs = super.findByCriteria(crit);
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
		return bdcs;
	}

	@Override
	public List<BonDeCommande> find(Double price) throws PersistenceException {
		List<BonDeCommande> bdcs;
		try {
			Session session = getSf().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(BonDeCommande.class);
			crit.add(Restrictions.eq("price", price));
			bdcs = super.findByCriteria(crit);
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
		return bdcs;
	}

	@Override
	public List<BonDeCommande> find(Double priceMin, Double priceMax) throws PersistenceException {
		List<BonDeCommande> bdcs;
		try {
			Session session = getSf().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(BonDeCommande.class);
			crit.add(Restrictions.between("price", priceMin, priceMax));
			bdcs = super.findByCriteria(crit);
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
		return bdcs;
	}

}
