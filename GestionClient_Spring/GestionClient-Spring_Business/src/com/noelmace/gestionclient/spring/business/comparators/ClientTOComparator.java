package com.noelmace.gestionclient.spring.business.comparators;

import java.util.Comparator;

import com.noelmace.gestionclient.spring.business.dto.ClientTO;

public class ClientTOComparator implements Comparator<ClientTO> {
	@Override
	public int compare(ClientTO o1, ClientTO o2) {
		int valNom = o1.getNom().compareTo(o2.getNom());
		if(valNom != 0){
			return valNom;
		}
		int valPrenom = o1.getPrenom().compareTo(o2.getPrenom());
		if(valPrenom != 0){
			return valPrenom;
		}
		return (new Long(o1.getId())).compareTo(new Long(o2.getId()));
	}
}
