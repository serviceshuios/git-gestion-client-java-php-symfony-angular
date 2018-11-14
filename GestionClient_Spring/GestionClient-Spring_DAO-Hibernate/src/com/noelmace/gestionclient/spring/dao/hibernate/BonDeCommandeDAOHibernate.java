package com.noelmace.gestionclient.spring.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import com.noelmace.gestionclient.spring.business.entities.BonDeCommande;



/**
 * @author Noël Macé (noelmace.com)
 *
 */
public class BonDeCommandeDAOHibernate extends DAOHibernate<BonDeCommande> {

	@Override
	public BonDeCommande find(Long id) throws SQLException {
		Session session = sf.openSession();
        session.beginTransaction();
        BonDeCommande bdc = (BonDeCommande) session.get(BonDeCommande.class, id);
        session.close();
        return bdc;
	}

	@Override
	public List<BonDeCommande> findAll() throws SQLException {
		Session session = sf.openSession();
		session.beginTransaction();
        @SuppressWarnings("unchecked")
		List<BonDeCommande> result = session.createQuery("from BonDeCommande").list();
        session.close();
		return result;
	}
}
