package com.noelmace.gestionclient.nospring.xml.dao;

import java.io.File;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.noelmace.gestionclient.nospring.business.data.dao.DAO;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceConnectionException;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.xml.ToHandler;
import com.noelmace.gestionclient.nospring.xml.XmlGenerator;

public abstract class DaoXML<T> implements DAO<T> {

	protected ToHandler handler;
	
	protected SAXParserFactory parserFactory = SAXParserFactory.newInstance();
	protected XmlGenerator generator;
	
	protected SAXParser parser;
	
	protected static File file;

	public DaoXML(ToHandler handler, File file) throws PersistenceException {
		this.handler = handler;
		this.file = file;
		try {
			parser = parserFactory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
			throw new PersistenceConnectionException();
		}
		generator = new XmlGenerator(file);
	}
}
