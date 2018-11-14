package com.noelmace.gestionclient.nospring.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import com.noelmace.gestionclient.nospring.business.entities.Client;
import com.noelmace.gestionclient.nospring.business.services.exception.ServiceLayerException;
import com.noelmace.gestionclient.nospring.ioc.ServiceFactory;

public class ClientServiceTest {

	@Test
	public void testList() {
		List<Client> clients = null;
		try {
			clients = ServiceFactory.getClientService().list();
		} catch (ServiceLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(clients);
	}

	@Ignore
	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

}
