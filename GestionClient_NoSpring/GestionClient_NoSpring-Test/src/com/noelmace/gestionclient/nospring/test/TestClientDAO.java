package com.noelmace.gestionclient.nospring.test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.noelmace.gestionclient.nospring.business.data.dao.ClientDAO;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.entities.Client;
import com.noelmace.gestionclient.nospring.hibernate.dao.ClientDAOHibernate;


public class TestClientDAO {
	
	private ClientDAO clientDao = new ClientDAOHibernate();


	@Before
	public void testCreate() {
		List<Client> clients = new ArrayList<Client>(
				Arrays.asList(new Client(42, "Paul", "Ochon"), 
						new Client(33, "Pierre", "Quiroule"),
						new Client(22, "Ive", "Abien")));

		try {
			for(int i = 0; i < clients.size(); i++){
				clientDao.create(clients.get(i));
			}
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindByCriteria(){
		try {
			System.out.println("Par nom : " + clientDao.find("Paul", "Ochon").toString());
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("Par age : " + clientDao.find(new Integer(42)).toString());
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("Par intervale d'ages : " + clientDao.find(new Integer(32), new Integer(45)).toString());
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
