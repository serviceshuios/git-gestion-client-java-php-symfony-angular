package com.noelmace.gestionclient.spring.test;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.noelmace.gestionclient.spring.business.services.OrderingService;

public class MainTest {

	private ApplicationContext context;
	private OrderingService orderingService;
	
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		orderingService = (OrderingService) context.getBean("orderingService");
	}

	@Test
	public void test() throws SQLException {
		orderingService.makeOrder(1L, "bdc-nm-43", 320.42);
	}

}
