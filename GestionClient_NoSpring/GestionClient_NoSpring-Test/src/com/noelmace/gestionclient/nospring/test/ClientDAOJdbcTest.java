package com.noelmace.gestionclient.nospring.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.entities.Client;
import com.noelmace.gestionclient.nospring.jdbc.dao.ClientDAOJdbc;


public class ClientDAOJdbcTest {
	
	static ClientDAOJdbc dao;
	private Client client1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new ClientDAOJdbc();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		client1 = new Client(12, "toto", "titi");
		dao.create(client1);
	}

	@After
	public void tearDown() throws Exception {
		dao.delete(client1);
	}

	@Test
	public void testFind() throws PersistenceException {
		System.out.println(dao.findAll());
	}

}
