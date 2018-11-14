package com.noelmace.gestionclient.nospring.xml.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;

import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.dto.*;
import com.noelmace.gestionclient.nospring.xml.ToHandler;

public class ClientDAOXml extends DaoXML<ClientTO> {

	public ClientDAOXml(ToHandler handler, File file) throws PersistenceException {
		super(handler, file);
	}

	@Override
	public void create(ClientTO obj) throws PersistenceException {
		generator.addClient(obj);
		// solution temporaire
		generator.close();
	}

	@Override
	public void update(ClientTO obj) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientTO find(Long id) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientTO> findAll() throws PersistenceException {
		try {
			parser.parse(file, handler);
			return handler.getClientList();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
	}

	@Override
	public void delete(ClientTO obj) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	

}
