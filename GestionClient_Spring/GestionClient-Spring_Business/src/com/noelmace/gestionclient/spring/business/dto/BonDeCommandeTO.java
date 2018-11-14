package com.noelmace.gestionclient.spring.business.dto;

public class BonDeCommandeTO {
		
	private long id;

	private String ref;
	
	private double price;
	
	private long clientId;

	private BonDeCommandeTO(long id, String ref, double price, long clientId) {
		this.id = id;
		this.ref = ref;
		this.price = price;
		this.clientId = clientId;
	}

	public BonDeCommandeTO() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	
}
