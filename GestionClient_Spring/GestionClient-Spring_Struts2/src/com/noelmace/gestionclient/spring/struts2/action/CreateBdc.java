package com.noelmace.gestionclient.spring.struts2.action;

public class CreateBdc extends AbstractServicesAction {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String ref;
	private double price;
	private long clientId;
		 
	
	public String execute() throws Exception {
		this.id = orderingService.makeOrder(this.clientId, this.ref, this.price);
		return SUCCESS;
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
