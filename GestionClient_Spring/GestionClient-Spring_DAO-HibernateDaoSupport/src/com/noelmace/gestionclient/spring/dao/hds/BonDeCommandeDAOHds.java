package com.noelmace.gestionclient.spring.dao.hds;

import java.sql.SQLException;
import java.util.List;

import com.noelmace.gestionclient.spring.business.entities.BonDeCommande;



/**
 * @author Noël Macé (noelmace.com)
 *
 */
public class BonDeCommandeDAOHds extends DAOHds<BonDeCommande> {

	@Override
	public BonDeCommande find(Long id) throws SQLException {
		return super.find(id, BonDeCommande.class);
	}

	@Override
	public List<BonDeCommande> findAll() throws SQLException {
		return super.findAll(BonDeCommande.class);
	}

	
}
