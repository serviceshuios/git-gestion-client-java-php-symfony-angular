package com.noelmace.gestionclient.spring.business.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noelmace.gestionclient.spring.business.dto.ClientTO;
import com.noelmace.gestionclient.spring.business.entities.Client;

public class ClientService extends AbstractService {

	/**
	 * création d'un nouveau client
	 * @param nom
	 * @param prenom
	 * @throws SQLException
	 */
	public long create(String nom, String prenom) throws SQLException{
		Client client = new Client(nom, prenom);
		daoClient.create(client);
		return client.getId();
	}
	
	public List<ClientTO> list() throws SQLException{
		List<ClientTO> clientToList = new ArrayList<ClientTO>();
		List<Client> clientList = daoClient.findAll();
		for (Client client : clientList) {
			clientToList.add(assembler.assemble(client));
		}
		return clientToList;
	}

	public void delete(Long id) throws SQLException {
		Client client = daoClient.find(id);
		daoClient.delete(client);
	}

	public void update(Long id, String nom, String prenom) throws SQLException {
		Client client = daoClient.find(id);
		client.setNom(nom);
		client.setPrenom(prenom);
		daoClient.update(client);
	}

	public ClientTO get(Long id) throws SQLException {
		return assembler.assemble(daoClient.find(id));
	}

}
