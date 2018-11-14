package com.noelmace.gestionclient.spring.business.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noelmace.gestionclient.spring.business.dto.BonDeCommandeTO;
import com.noelmace.gestionclient.spring.business.dto.ClientTO;
import com.noelmace.gestionclient.spring.business.entities.BonDeCommande;
import com.noelmace.gestionclient.spring.business.entities.Client;

/**
 * @author Noël Macé (noelmace.com)
 *
 * Service de gestion des commandes
 */
public class OrderingService extends AbstractService {

	/**
	 * Création d'une nouvelle commande
	 * @param clientId id (unique) du client auquel associer la commande
	 * dans le cas où cet Id ne corresponde à aucun client, le bon de commande ne sera associé à aucun client
	 * @param ref référence du bon de commande
	 * @param price prix du bon de commande
	 * @throws SQLException 
	 */
	// TODO : gestion d'exeception
	public long makeOrder(Long clientId, String ref, Double price) throws SQLException{
		Client client = null;
		BonDeCommande bdc = new BonDeCommande(ref, price, null);
		client = daoClient.find(clientId);
		bdc.setClient(client);
		daoBdc.create(bdc);
		return bdc.getId();
	}

	public List<BonDeCommandeTO> list() throws SQLException {
		List<BonDeCommandeTO> bdcToList = new ArrayList<BonDeCommandeTO>();
		List<BonDeCommande> bdcList = daoBdc.findAll();
		for (BonDeCommande bdc : bdcList) {
			bdcToList.add(assembler.assemble(bdc));
		}
		return bdcToList;
	}

	public void delete(Long id) throws SQLException {
		BonDeCommande bdc = daoBdc.find(id);
		daoBdc.delete(bdc);
	}

	public void update(Long id, String ref, Double price) throws SQLException {
		BonDeCommande bdc = daoBdc.find(id);
		bdc.setRef(ref);
		bdc.setPrice(price);
		daoBdc.update(bdc);
	}
}
