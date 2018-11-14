package com.noelmace.gestionclient.spring.business.comparators;

import java.util.Comparator;

import com.noelmace.gestionclient.spring.business.entities.Client;

public class ClientComparator implements Comparator<Client> {
	@Override
	public int compare(Client o1, Client o2) {
		int valNom = o1.getNom().compareTo(o2.getNom());
		if(valNom != 0){
			return valNom;
		}
		int valPrenom = o1.getPrenom().compareTo(o2.getPrenom());
		if(valPrenom != 0){
			return valPrenom;
		}
		return o1.getId().compareTo(o2.getId());
	}
}
