package com.noelmace.gestionclient.spring.business.dto;

import java.util.List;
import java.util.Set;

public class ClientTO {
	
	private long id = -1;
	
	private String nom;

	private String prenom;
	
	// TODO : primitive list (pour performance / pas de tableau)
	private List<Long> bdcIds;

	public ClientTO() {
	}

	public ClientTO(long id, String nom, String prenom, List<Long> bdcIds) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.bdcIds = bdcIds;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Long> getBdcIds() {
		return bdcIds;
	}

	public void setBdcIds(List<Long> bdcIds) {
		this.bdcIds = bdcIds;
	}
	
	

}
