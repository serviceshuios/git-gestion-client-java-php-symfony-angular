package com.noelmace.gestionclient.spring.business.dto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.noelmace.gestionclient.spring.business.dao.DAO;
import com.noelmace.gestionclient.spring.business.entities.*;

public class AssemblerImpl implements Assembler {
	
	private DAO<Client> clientDAO;	
	private DAO<BonDeCommande> bdcDAO;

	@Override
	public ClientTO assemble(Client client){
		ClientTO clientTo = new ClientTO();
		clientTo.setId(client.getId().longValue());
		clientTo.setNom(client.getNom());
		clientTo.setPrenom(client.getPrenom());
		clientTo.setBdcIds(fromBdcToIds(client.getBonsDeCommande()));
		return clientTo;
	}

	@Override
	public Client desassemble(ClientTO clientTo){
		Client client = new Client();
		if(clientTo.getId() > 0){
			Client clientP = null;
			try {
				clientP = clientDAO.find(new Long(clientTo.getId()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(clientP != null){
				client.setId(clientP.getId());
			} else {
				client.setId(null);
			}
		}
		client.setNom(clientTo.getNom());
		client.setPrenom(clientTo.getPrenom());
		client.setBonsDeCommande(fromIdsToBdc(clientTo.getBdcIds()));
		return client;
	}
	
	@Override
	public BonDeCommandeTO assemble(BonDeCommande bdc){
		BonDeCommandeTO bdcTo = new BonDeCommandeTO();
		bdcTo.setId(bdc.getId().longValue());
		bdcTo.setPrice(bdc.getPrice().doubleValue());
		bdcTo.setRef(bdc.getRef());
		if(bdc.getClient() != null){
			bdcTo.setClientId(bdc.getClient().getId());
		}
		return bdcTo;
	}
	
	@Override
	public BonDeCommande desassemble(BonDeCommandeTO bdcTo){
		BonDeCommande bdc = new BonDeCommande();
		if(bdcTo.getId() > 0){
			BonDeCommande bdcP = null;
			try {
				bdcP = bdcDAO.find(new Long(bdcTo.getId()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(bdcP != null){
				bdc.setId(bdcP.getId());
			} else {
				bdc.setId(null);
			}
		}
		bdc.setPrice(bdcTo.getPrice());
		bdc.setRef(bdcTo.getRef());
		if(bdcTo.getClientId() > 0){
			try {
				bdc.setClient(clientDAO.find(bdcTo.getClientId()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bdc;
	}
	
	private List<Long> fromBdcToIds(Set<BonDeCommande> bdcList){
		List<Long> idList = new ArrayList<Long>();
		for (BonDeCommande bdc : bdcList) {
			idList.add(bdc.getId());
		}
		return idList;
	}
	
	private Set<BonDeCommande> fromIdsToBdc(List<Long> idList){
		Set<BonDeCommande> bdcList = new HashSet<BonDeCommande>();
		for (Long id : idList) {
			BonDeCommande bdc = null;
			if(id > 0){
				try {
					bdc = bdcDAO.find(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bdc != null){
				bdcList.add(bdc);
			}
		}
		return bdcList;
	}
	
	// TODO : injection Spring
	public void setClientDAO(DAO<Client> clientDAO) {
		this.clientDAO = clientDAO;
	}

	// TODO : injection Spring
	public void setBdcDAO(DAO<BonDeCommande> bdcDAO) {
		this.bdcDAO = bdcDAO;
	}
	
	

}
