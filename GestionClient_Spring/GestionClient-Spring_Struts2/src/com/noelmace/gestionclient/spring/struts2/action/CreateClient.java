package com.noelmace.gestionclient.spring.struts2.action;

import com.noelmace.gestionclient.spring.business.dto.ClientTO;
import com.opensymphony.xwork2.ModelDriven;

public class CreateClient extends AbstractServicesAction implements ModelDriven<ClientTO> {
	
	private static final long serialVersionUID = 1L;
	
	ClientTO client = new ClientTO();
	 
	public String execute() throws Exception {
		clientService.create(client.getNom(), client.getPrenom());
		return SUCCESS;
	}
	
	public ClientTO getModel() {
		return client;
	}
}
