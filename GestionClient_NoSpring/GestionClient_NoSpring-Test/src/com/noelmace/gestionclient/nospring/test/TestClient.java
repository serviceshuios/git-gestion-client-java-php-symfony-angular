package com.noelmace.gestionclient.nospring.test;

import org.junit.Test;

import com.noelmace.gestionclient.nospring.business.entities.Client;

public class TestClient {

	@Test
	public void test() {
		Client cl = new Client(42, "toto", "tutu");
	}

}
