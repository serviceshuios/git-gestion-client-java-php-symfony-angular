package com.noelmace.gestionclient.nospring.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.noelmace.gestionclient.nospring.business.data.dao.BdcDAO;
import com.noelmace.gestionclient.nospring.business.data.dao.ClientDAO;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.entities.BonDeCommande;
import com.noelmace.gestionclient.nospring.business.entities.Client;
import com.noelmace.gestionclient.nospring.hibernate.dao.BdcDAOHibernate;
import com.noelmace.gestionclient.nospring.hibernate.dao.ClientDAOHibernate;


public class TestBdcDao {
	
	private BdcDAO bdcDao = new BdcDAOHibernate();
	private ClientDAO clientDao = new ClientDAOHibernate();

	@Before
	public void testCreate() {
		List<Client> clients = new ArrayList<Client>(
				Arrays.asList(new Client(42, "Paul", "Ochon"), 
						new Client(33, "Pierre", "Quiroule"),
						new Client(22, "Ive", "Abien")));
		List<BonDeCommande> bdcs = new ArrayList<BonDeCommande>(
				Arrays.asList(
						new BonDeCommande("bdc1", new Double(12.2), clients.get(0)),
						new BonDeCommande("bdc2", new Double(13.4), clients.get(1)),
						new BonDeCommande("bdc3", new Double(3.4), clients.get(0)),
						new BonDeCommande("bdc4", new Double(1.4), clients.get(2))));
		try {
			for(int i = 0; i < clients.size(); i++){
				bdcDao.create(bdcs.get(i));
			}
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindByCriteria(){
		try {
			System.out.println("Par client : " + bdcDao.find(clientDao.find("Paul", "Ochon").get(0)).toString());
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("Par prix : " + bdcDao.find(new Double(12.2)).toString());
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("Par intervale de prix : " + bdcDao.find(new Double(10), new Double(15)).toString());
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
