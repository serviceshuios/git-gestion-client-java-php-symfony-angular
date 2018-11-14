package com.noelmace.gestionclient.nospring.business.services.exception;

@SuppressWarnings("serial")
public class ServiceLayerException extends Exception {
	
	public ServiceLayerException(String message){
		super(message);
	}
	
	public ServiceLayerException(Throwable cause){
		super(cause);
	}
	
	public ServiceLayerException(String message, Throwable cause){
		super(message, cause);
	}
}
