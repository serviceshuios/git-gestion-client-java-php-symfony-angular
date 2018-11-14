package com.noelmace.gestionclient.spring.business.comparators;

import java.util.Comparator;

import com.noelmace.gestionclient.spring.business.entities.BonDeCommande;

public class BdcComparator implements Comparator<BonDeCommande> {

	@Override
	public int compare(BonDeCommande o1, BonDeCommande o2) {
		int valRef = o1.getRef().compareTo(o2.getRef());
		if(valRef != 0){
			return valRef;
		}
		int valPrice = o1.getPrice().compareTo(o2.getPrice());
		if(valPrice != 0){
			return valPrice;
		}
		int valClient = (new ClientComparator()).compare(o1.getClient(), o2.getClient());
		if(valClient != 0){
			return valClient;
		}
		return o1.getId().compareTo(o2.getId());
	}
}


