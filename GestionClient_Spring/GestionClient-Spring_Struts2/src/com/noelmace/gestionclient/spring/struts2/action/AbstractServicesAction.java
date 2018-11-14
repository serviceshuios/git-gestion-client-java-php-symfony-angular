package com.noelmace.gestionclient.spring.struts2.action;

import com.noelmace.gestionclient.spring.business.services.ClientService;
import com.noelmace.gestionclient.spring.business.services.OrderingService;
import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractServicesAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	protected ClientService clientService;
	protected OrderingService orderingService;
	
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	
	public void setOrderingService(OrderingService orderingService) {
		this.orderingService = orderingService;
	}
	
}
