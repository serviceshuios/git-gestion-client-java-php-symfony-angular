package com.noelmace.gestionclient.spring.mvc.ctrl;

import org.springframework.web.servlet.mvc.Controller;

import com.noelmace.gestionclient.spring.business.services.ClientService;
import com.noelmace.gestionclient.spring.business.services.OrderingService;

public abstract class AbstractCtrl implements Controller {
	
	protected ClientService clientService;
	protected OrderingService orderingService;
	
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	
	public void setOrderingService(OrderingService orderingService) {
		this.orderingService = orderingService;
	}
}
