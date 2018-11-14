package com.noelmace.gestionclient.nospring.ioc;

import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceConnectionException;
import com.noelmace.gestionclient.nospring.business.services.AbstractService;
import com.noelmace.gestionclient.nospring.business.services.ClientService;
import com.noelmace.gestionclient.nospring.business.services.OrderingService;
import com.noelmace.gestionclient.nospring.business.services.exception.ServiceLayerException;

/**
 * Fabrique pour les service
 * 
 * Réalise l'injection des DAO (via DaoFactory) dans les instances de services.
 * 
 * @author Noël Macé (noelmace.com)
 *
 */
public abstract class ServiceFactory {
	
	private static ClientService clientService;
	private static OrderingService orderingService;
		
	/**
	 * Création d'une instance de ClientService avec injection des DAO
	 * @return l'instance de ClientService
	 * @throws ServiceLayerException
	 */
	public static ClientService getClientService() throws ServiceLayerException {
		if(clientService == null){
			try {
				clientService = new ClientService(DaoFactory.getBdcDao(), DaoFactory.getClientDao());
			} catch (PersistenceConnectionException e) {
				e.printStackTrace();
				throw new ServiceLayerException(AbstractService.PERSISTENCE_CONNECTION_ERROR_MSG, e);
			}
		}
		return clientService;
	}
	
	/**
	 *
	 * @return
	 * @throws PersistenceConnectionException 
	 */
	public static OrderingService getOrderingService() throws PersistenceConnectionException {
		if(orderingService == null){
			orderingService = new OrderingService(DaoFactory.getBdcDao(), DaoFactory.getClientDao());
		}
		return orderingService;
	}
	
	

}
