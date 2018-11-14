package com.noelmace.gestionclient.nospring.hibernate.test;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestSF {

	@Test
	public void test() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
	}

}
