package com.noelmace.gestionclient.nospring.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.noelmace.gestionclient.nospring.jdbc.dao.ClientDAOJdbc;
import com.noelmace.gestionclient.nospring.business.data.dao.DAO;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceConnectionException;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.entities.*;

public class TestClientDAOJdbc {

	@Test
	public void testFind() {
		DAO<Client> clientDao;
		try {
			clientDao = new ClientDAOJdbc();
			Client client = clientDao.find(1L);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAll() {
		DAO<Client> clientDao;
		try {
			clientDao = new ClientDAOJdbc();
			List<Client> clientList = clientDao.findAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}
}
