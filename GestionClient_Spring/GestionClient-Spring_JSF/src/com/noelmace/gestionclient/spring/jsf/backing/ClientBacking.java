package com.noelmace.gestionclient.spring.jsf.backing;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import com.noelmace.gestionclient.spring.business.comparators.ClientTOComparator;
import com.noelmace.gestionclient.spring.business.dto.ClientTO;
import com.noelmace.gestionclient.spring.business.services.ClientService;

public class ClientBacking {

	private ClientService clientService;

	private String nom;

	private String prenom;

	private List<ClientTO> clientList;

	private long id;

	@PostConstruct
	public void init(){
		try {
			this.clientList = clientService.list();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Getters et setters

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

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	// méthodes du bean

	public List<ClientTO> getClientList() {
		return clientList;
	}

	public void add() throws SQLException{
		this.id = clientService.create(nom, prenom);
		init();
	}

	public void delete(ClientTO client) throws SQLException {
		clientService.delete(client.getId());
		init();
	}

	public void update() throws SQLException {
		clientService.update(this.id, this.nom, this.prenom);
		init();
	}

	public void sort(){
		Collections.sort(clientList, new ClientTOComparator());
	}

	// actions

	public String initModify(ClientTO client){
		this.nom = client.getNom();
		this.prenom = client.getPrenom();
		this.id = client.getId();
		return "/client/modify";
	}

}
