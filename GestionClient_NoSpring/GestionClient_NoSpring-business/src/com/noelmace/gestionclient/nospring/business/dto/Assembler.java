package com.noelmace.gestionclient.nospring.business.dto;

import com.noelmace.gestionclient.nospring.business.entities.BonDeCommande;
import com.noelmace.gestionclient.nospring.business.entities.Client;

public interface Assembler {
	
	public ClientTO assemble(Client client);

	public Client desassemble(ClientTO clientTo);

	public BonDeCommandeTO assemble(BonDeCommande bdc);

	public BonDeCommande desassemble(BonDeCommandeTO bdcTo);

}
