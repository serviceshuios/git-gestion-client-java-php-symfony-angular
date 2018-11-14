package com.noelmace.gestionclient.nospring.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.noelmace.gestionclient.nospring.business.dto.BonDeCommandeTO;
import com.noelmace.gestionclient.nospring.business.dto.ClientTO;
import com.noelmace.gestionclient.nospring.business.entities.*;

public class ToHandler extends DefaultHandler {

	private List<BonDeCommandeTO> bdcList = new ArrayList<>();
	private List<ClientTO> clientList = new ArrayList<ClientTO>();
	private BonDeCommandeTO curBdc = null;
	private ClientTO curClient = null;

	// private Client curClient = null;

	private double curPrice;
	private String curRef;

	private int curClientId;

	private int curAge;
	private String curFirstName;
	private String curLastName;

	public List<BonDeCommandeTO> getBdcList() {
		return bdcList;
	}

	public List<ClientTO> getClientList() {
		return clientList;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("bdc")) {
			curRef = attributes.getValue("ref");
			curPrice = Double.parseDouble(attributes.getValue("price"));
			curClientId = Integer.parseInt(attributes.getValue("clientId"));
		} else if (qName.equalsIgnoreCase("client")) {
			curAge = Integer.parseInt(attributes.getValue("age"));
			curFirstName = attributes.getValue("firstName");
			curLastName = attributes.getValue("lastName");
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("bdc")) {
			curBdc = new BonDeCommandeTO(-1, curRef, curPrice, curClientId);
			bdcList.add(curBdc);
			curBdc.setId(bdcList.size() - 1);
		} else if (qName.equalsIgnoreCase("client")) {
			curClient = new ClientTO(-1, curLastName, curFirstName, curAge);
			clientList.add(curClient);
			curClient.setId(clientList.size() - 1);
		}
	}
}
