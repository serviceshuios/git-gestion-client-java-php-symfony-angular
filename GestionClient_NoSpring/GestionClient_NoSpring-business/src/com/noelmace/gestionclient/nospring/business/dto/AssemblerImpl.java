package com.noelmace.gestionclient.nospring.business.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.noelmace.gestionclient.nospring.business.entities.*;

public class AssemblerImpl implements Assembler {
	
	private List<ClientTO> clients = new ArrayList<ClientTO>();
	private List<BonDeCommandeTO> bdcs = new ArrayList<BonDeCommandeTO>();

	@Override
	public ClientTO assemble(Client client) {
		ClientTO clientTo = new ClientTO();
		clientTo.setId(client.getId().intValue());
		clientTo.setNom(client.getNom());
		clientTo.setPrenom(client.getPrenom());
		clientTo.setAge(client.getAge());
		clients.add(clientTo);
		clientTo.setId(clients.size() - 1);
		return clientTo;
	}

	@Override
	public Client desassemble(ClientTO clientTo) {
		Client client = new Client();
		client.setNom(clientTo.getNom());
		client.setPrenom(clientTo.getPrenom());
		client.setAge(clientTo.getAge());
		return client;
	}

	@Override
	public BonDeCommandeTO assemble(BonDeCommande bdc) {
		BonDeCommandeTO bdcTo = new BonDeCommandeTO();
		bdcTo.setPrice(bdc.getPrice().doubleValue());
		bdcTo.setRef(bdc.getRef());
		ClientTO clientTo = assemble(bdc.getClient());
		bdcTo.setClientId(clientTo.getId());
		bdcs.add(bdcTo);
		bdcTo.setId(bdcs.size() - 1);
		return bdcTo;
	}

	@Override
	public BonDeCommande desassemble(BonDeCommandeTO bdcTo) {
		BonDeCommande bdc = new BonDeCommande();
		bdc.setPrice(bdcTo.getPrice());
		bdc.setRef(bdcTo.getRef());
		bdc.setClient(desassemble(clients.get(bdcTo.getClientId())));
		return bdc;
	}

}
