package com.noelmace.gestionclient.nospring.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.dto.BonDeCommandeTO;
import com.noelmace.gestionclient.nospring.business.dto.ClientTO;

/**
 * @author Noël Macé (noelmace.com)
 * 
 * Génération d'un fichier XML à partir de DTOs
 * Module en cours de rédaction
 * 
 * Exemple avec StAX (ne permet que la génération que d'un élément)
 */
public class XmlGenerator {
		
	private XMLOutputFactory outFactory = XMLOutputFactory.newInstance();
		
	private XMLStreamWriter streamWriter;
	
	private File file;
	
	private BufferedReader br;

	private boolean newFile = false; 
	
	public XmlGenerator(File file) throws PersistenceException {
		try {
			this.file = file;
			streamWriter = outFactory.createXMLStreamWriter(new FileWriter(file, true));
			init();
		} catch (XMLStreamException | IOException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
	}
	
	public void addClient(ClientTO client) throws PersistenceException{
		try {
			streamWriter.writeStartElement("client");
			streamWriter.writeAttribute("id", Integer.toString(client.getId()));
			streamWriter.writeAttribute("age", Integer.toString(client.getAge()));
			streamWriter.writeAttribute("firstName", client.getPrenom());
			streamWriter.writeAttribute("lastName", client.getNom());
			streamWriter.writeEndElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
	}
	
	public void addBdc(BonDeCommandeTO bdc) throws PersistenceException{
		try {
			streamWriter.writeStartElement("bdc");
			streamWriter.writeAttribute("id", Integer.toString(bdc.getId()));
			streamWriter.writeAttribute("ref", bdc.getRef());
			streamWriter.writeAttribute("price", Double.toString(bdc.getPrice()));
			// TODO : vérifier que le client a été enregistré
			streamWriter.writeAttribute("clientId", Integer.toString(bdc.getClientId()));
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
	}
	
	// TODO : solution temporaire
	public void close() throws PersistenceException{
		try {
			// empèche la réécriture
			if(newFile)
				streamWriter.writeEndElement();
			streamWriter.writeEndDocument();
			streamWriter.flush();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		streamWriter.close();
		br.close();
		super.finalize();
	}
	
	// TODO : test syntaxe du fichier
	private void init() throws PersistenceException{
		try {
			br = new BufferedReader(new FileReader(file));    
			if (br.readLine() == null) {
				this.newFile  = true;
				streamWriter.writeStartDocument();
				streamWriter.writeStartElement("exports");
			}
		} catch (IOException | XMLStreamException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
	}
	
}
