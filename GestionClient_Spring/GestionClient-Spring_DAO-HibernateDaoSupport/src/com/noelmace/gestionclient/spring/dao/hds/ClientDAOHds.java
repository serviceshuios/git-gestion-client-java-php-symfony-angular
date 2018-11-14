package com.noelmace.gestionclient.spring.dao.hds;

import java.sql.SQLException;
import java.util.List;

import com.noelmace.gestionclient.spring.business.entities.Client;



/**
 * @author Noël Macé (noelmace.com)
 *
 */
public class ClientDAOHds extends DAOHds<Client> {

	@Override
	public Client find(Long id) throws SQLException {
		return super.find(id, Client.class);
	}

	@Override
	public List<Client> findAll() throws SQLException {
		return super.findAll(Client.class);
	}

	
}
