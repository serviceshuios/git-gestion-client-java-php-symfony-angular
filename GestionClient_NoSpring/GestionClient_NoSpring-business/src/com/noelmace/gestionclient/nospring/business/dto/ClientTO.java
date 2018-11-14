package com.noelmace.gestionclient.nospring.business.dto;

import java.util.List;
import java.util.Set;

public class ClientTO {
	
	private int id = -1;
	
	private String nom;

	private String prenom;
	
	private int age;

	public ClientTO() {
	}

	public ClientTO(int id, String nom, String prenom, int age) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

}
