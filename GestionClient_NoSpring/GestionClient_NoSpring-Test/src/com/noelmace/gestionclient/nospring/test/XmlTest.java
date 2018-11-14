package com.noelmace.gestionclient.nospring.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.noelmace.gestionclient.nospring.business.data.dao.DAO;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.dto.ClientTO;
import com.noelmace.gestionclient.nospring.xml.ToHandler;
import com.noelmace.gestionclient.nospring.xml.dao.ClientDAOXml;

public class XmlTest {
	
	private static File file = new File("test.xml");
	private static ToHandler handler = new ToHandler();
	private static DAO<ClientTO> daoClientTO;
	
	private ClientTO client;

	@BeforeClass
	public static void init(){
		try {
			file.createNewFile();
			daoClientTO = new ClientDAOXml(handler, file);
		} catch (PersistenceException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Before
	public void initObjs(){
		client = new ClientTO();
		client.setId(1);
		client.setAge(42);
		client.setNom("Jean");
		client.setPrenom("Jacques");
	}
	
	@Test
	public void test() {
		try {
			daoClientTO.create(client);
			//System.out.println(daoClientTO.findAll());
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
