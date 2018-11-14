package com.noelmace.gestionclient.spring.business.dto;

import com.noelmace.gestionclient.spring.business.entities.BonDeCommande;
import com.noelmace.gestionclient.spring.business.entities.Client;

public interface Assembler {
	
	public ClientTO assemble(Client client);

	public Client desassemble(ClientTO clientTo);

	public BonDeCommandeTO assemble(BonDeCommande bdc);

	public BonDeCommande desassemble(BonDeCommandeTO bdcTo);

}
