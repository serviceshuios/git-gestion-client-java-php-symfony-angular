package com.noelmace.gestionclient.nospring.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceConnectionException;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.entities.Client;

public class ClientDAOJdbc extends DAOJdbc<Client> {
	
	public ClientDAOJdbc() throws PersistenceConnectionException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final String FIND_ST = "SELECT age, nom, prenom FROM Client WHERE id = ?";
	private static final String CREATE_ST = "INSERT INTO Client(age, nom, prenom) VALUES (?, ?, ?)";
	private static final String UPDATE_ST = "UPDATE client SET age = ?, nom = ?, prenom = ? WHERE id = ?";
	private static final String FIND_ALL_ST = "SELECT id, age, nom, prenom FROM Client";
	private static final String DELETE_ST = "DELETE FROM Client WHERE id = ?";
	
	@Override
	public void create(Client obj) throws PersistenceException {
		if(obj.getId() == null){
			PreparedStatement st = null;
			ResultSet generatedKeys = null;
			try {
				
				st = cnx.prepareStatement(CREATE_ST, Statement.RETURN_GENERATED_KEYS);
				st.setInt(1, obj.getAge());
				st.setString(2, obj.getNom());
				st.setString(3, obj.getPrenom());
				
				// test du nombre de lignes affectées (1 normalement)
				int affectedRows = st.executeUpdate();
				if(affectedRows != 1){
					throw new SQLException("Creating user failed, more or less than 1 row affected.");
				}
				
				// récupération de l'id générée pour cet enregistrement, et affectation au client
				generatedKeys = st.getGeneratedKeys();
				if(generatedKeys.next()){
		            obj.setId(generatedKeys.getLong(1));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PersistenceException();
			} finally {
				try {
					st.close();
					generatedKeys.close();
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			// TODO : lever une exception personnalisée 
		}
	}

	@Override
	public void update(Client obj) throws PersistenceException {
		if(obj.getId() != null){
			PreparedStatement st = null;
			try {
				st = cnx.prepareStatement(UPDATE_ST);
				st.setInt(1, obj.getAge());
				st.setString(2, obj.getNom());
				st.setString(3, obj.getPrenom());
				st.setLong(4, obj.getId());
				// test du nombre de lignes affectées (1 normalement)
				int affectedRows = st.executeUpdate();
				if (affectedRows != 1) {
					throw new SQLException(
							"Creating user failed, more or less than 1 row affected.");
				}
			} catch (SQLException e) {
				throw new PersistenceException();
			} finally {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			// TODO : lever une exception personnalisée 
		}
	}

	@Override
	public Client find(Long id) throws PersistenceException {
		PreparedStatement st;
		Client obj = null;
		try {
			st = cnx.prepareStatement(FIND_ST);
			st.setLong(1, id);
			ResultSet res = st.executeQuery();
			if(res.next()){
				obj = new Client(id, res.getInt(1), res.getString(2), res.getString(3));
			}
			res.close();
		} catch (SQLException e) {
			throw new PersistenceException();
		}
		
		return obj;
	}

	@Override
	public List<Client> findAll() throws PersistenceException {
		List<Client> tmp = new ArrayList<Client>();
		Statement st;
		try {
			st = cnx.createStatement();
			ResultSet res = st.executeQuery(FIND_ALL_ST);
			if (res != null) {
				while (res.next())
				{
					tmp.add(new Client(res.getLong(1), res.getInt(2), res.getString(3), res.getString(4)));
					
				}
				res.close();
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException();
		}
		return (tmp);
	}

	@Override
	public void delete(Client obj) throws PersistenceException {
		PreparedStatement st = null;
		try {
			st = cnx.prepareStatement(DELETE_ST);
			st.setLong(1, obj.getId());
			st.executeUpdate();
			obj.setId(null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
