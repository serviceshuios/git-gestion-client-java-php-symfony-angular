package com.noelmace.gestionclient.spring.struts2.action;

import java.util.List;

import com.noelmace.gestionclient.spring.business.dto.BonDeCommandeTO;

public class ListBdc extends AbstractServicesAction {
	
	private static final long serialVersionUID = 1L;
	
	private List<BonDeCommandeTO> bdcList;
		 
	public String execute() throws Exception {
		this.bdcList = orderingService.list();
		return SUCCESS;
	}

	public List<BonDeCommandeTO> getBdcList() {
		return bdcList;
	}

	
}
