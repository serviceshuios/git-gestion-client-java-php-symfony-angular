package com.noelmace.gestionclient.nospring.business.dto;

public class BonDeCommandeTO {
		
	private int id = -1;

	private String ref;
	
	private double price;
	
	private int clientId;

	public BonDeCommandeTO(int id, String ref, double price, int clientId) {
		this.id = id;
		this.ref = ref;
		this.price = price;
		this.clientId = clientId;
	}

	public BonDeCommandeTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
}
