package com.noelmace.gestionclient.spring.jsp.ctrl;

import org.springframework.web.HttpRequestHandler;

import com.noelmace.gestionclient.spring.business.services.ClientService;
import com.noelmace.gestionclient.spring.business.services.OrderingService;

/**
 * Servlet implementation class AbstractServlet
 */
public abstract class AbstractServiceRequestHandler implements HttpRequestHandler {

	protected ClientService clientService;
	protected OrderingService orderingService;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setOrderingService(OrderingService orderingService) {
		this.orderingService = orderingService;
	}

	/*
	private static ApplicationContext context;

	static {
		context = new ClassPathXmlApplicationContext("DaoHibernateSpringContext.xml");
	    clientService = (ClientService) context.getBean("clientService");
	}
	*/

}
