package com.noelmace.gestionclient.nospring.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceConnectionException;
import com.noelmace.gestionclient.nospring.business.data.dao.exceptions.PersistenceException;
import com.noelmace.gestionclient.nospring.business.entities.BonDeCommande;
import com.noelmace.gestionclient.nospring.business.entities.Client;

public class BdcDAOJdbc extends DAOJdbc<BonDeCommande> {

	private static final String FIND_ST = "SELECT * FROM BonDeCommande WHERE id = ?";
	private static final String CREATE_ST = "INSERT INTO BonDeCommande(ref, price, clientId) VALUES (?, ?, ?)";
	// private static final String UPDATE_ST =
	// "UPDATE client SET age = ?, nom = ?, prenom = ? WHERE id = ?";
	// private static final String FIND_ALL_ST =
	// "SELECT id, age, nom, prenom FROM Client";
	// private static final String DELETE_ST =
	// "DELETE FROM Client WHERE id = ?";

	private ClientDAOJdbc clientDao;

	public BdcDAOJdbc() throws PersistenceConnectionException {
		super();
		this.clientDao = new ClientDAOJdbc();
	}

	@Override
	public void create(BonDeCommande obj) throws PersistenceException {
		Client client = obj.getClient();
		if (client != null) {
			if (client.getId() == null) {
				clientDao.create(client);
			} else if (!client.equals(clientDao.find(client.getId()))) {
				clientDao.update(client);
			}
			try {
				PreparedStatement st = cnx.prepareStatement(CREATE_ST,
						Statement.RETURN_GENERATED_KEYS);
				st.setString(1, obj.getRef());
				st.setDouble(2, obj.getPrice());
				st.setLong(3, obj.getClient().getId());
				int affectedRows = st.executeUpdate();
				if (affectedRows != 1) {
					throw new SQLException(
							"Creating bdc failed, more or less than 1 row affected.");
				}

				// récupération de l'id générée pour cet enregistrement, et
				// affectation au bdc
				ResultSet generatedKeys = st.getGeneratedKeys();
				if (generatedKeys.next()) {
					obj.setId(generatedKeys.getLong(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PersistenceException();
			}
		}
	}

	@Override
	public void update(BonDeCommande obj) throws PersistenceException {
		// TODO Auto-generated method stub

	}

	@Override
	public BonDeCommande find(Long id) throws PersistenceException {
		PreparedStatement st;
		BonDeCommande obj = null;
		try {
			st = cnx.prepareStatement(FIND_ST);
			st.setLong(1, id);
			ResultSet res = st.executeQuery();
			if (res.next()) {
				Client client = this.clientDao.find(res.getLong("client_id"));
				obj = new BonDeCommande(res.getString("ref"),
						res.getDouble("prix"), client);
				obj.setId(id);
			}
			res.close();
		} catch (SQLException e) {
			throw new PersistenceException();
		}

		return obj;
	}

	@Override
	public List<BonDeCommande> findAll() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(BonDeCommande obj) throws PersistenceException {
		// TODO Auto-generated method stub

	}

}
