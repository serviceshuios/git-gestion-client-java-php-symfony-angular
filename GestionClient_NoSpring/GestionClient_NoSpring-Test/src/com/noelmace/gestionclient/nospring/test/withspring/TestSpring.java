package com.noelmace.gestionclient.nospring.test.withspring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.noelmace.gestionclient.nospring.business.services.ClientService;
import com.noelmace.gestionclient.nospring.business.services.OrderingService;
import com.noelmace.gestionclient.nospring.business.services.exception.ServiceLayerException;


/**
 * @author Noël Macé (noelmace.com)
 *
 * transition entre le projet noSpring et le projet Spring
 * test d'une première configuration Spring, sans AOP ni interration Spring/Hibernate
 */
public class TestSpring {

	private ApplicationContext context;

	@Test
	public void test() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		OrderingService os = (OrderingService) context.getBean("orderingService");
	    ClientService cs = (ClientService) context.getBean("clientService");
		try {
			cs.create(new Integer(12), "Kouroul", "Pierre");
		    os.makeOrder(new Long(1), "machin", new Double(12));
			System.out.println(os.list());
			System.out.println(cs.list());
		} catch (ServiceLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
