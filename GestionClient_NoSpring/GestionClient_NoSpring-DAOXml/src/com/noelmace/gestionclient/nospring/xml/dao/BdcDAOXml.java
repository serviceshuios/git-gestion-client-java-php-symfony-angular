package com.noelmace.gestionclient.nospring.xml.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;

import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.dto.*;
import com.noelmace.gestionclient.nospring.xml.ToHandler;

public class BdcDAOXml extends DaoXML<BonDeCommandeTO> {

	public BdcDAOXml(ToHandler handler, File file) throws PersistenceException {
		super(handler, file);
	}

	@Override
	public void create(BonDeCommandeTO obj) throws PersistenceException {
		generator.addBdc(obj);
	}

	@Override
	public void update(BonDeCommandeTO obj) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BonDeCommandeTO find(Long id) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BonDeCommandeTO> findAll() throws PersistenceException {
		try {
			parser.parse(file, handler);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
		return handler.getBdcList();
	}

	@Override
	public void delete(BonDeCommandeTO obj) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	

}
