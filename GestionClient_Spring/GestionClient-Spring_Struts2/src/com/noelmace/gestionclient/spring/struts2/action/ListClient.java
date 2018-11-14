package com.noelmace.gestionclient.spring.struts2.action;

import java.util.List;

import com.noelmace.gestionclient.spring.business.dto.ClientTO;

public class ListClient extends AbstractServicesAction {
	
	private static final long serialVersionUID = 1L;
	
	private List<ClientTO> clientList;
	 
	public String execute() throws Exception {
		clientList = clientService.list();
		return SUCCESS;
	}

	public List<ClientTO> getClientList() {
		return clientList;
	}
}
