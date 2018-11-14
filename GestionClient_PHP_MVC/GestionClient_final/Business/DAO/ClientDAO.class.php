<?php

namespace Business\DAO;

use Business\Entities\Client;
use Business\Entities\Entity;

/**
 * DAO pour les clients, par PDO
 */
class ClientDAO extends DAO {

	// Constantes définissant les requêtes à effectuer 
	
	const CREATE_RQ = 'INSERT INTO Client (login, password, nom, prenom, commentaire, expiration, admin, icon) VALUES (:login, :password, :nom, :prenom, :commentaire, NOW() + INTERVAL 1 YEAR, :admin, :icon) ';
	const GET_RQ = 'SELECT * FROM Client WHERE login = :login';
	const GET_ALL_RQ = 'SELECT * FROM Client';
	const UPDATE_RQ = 'UPDATE Client SET nom = :nom, prenom = :prenom, commentaire = :commentaire, admin = :admin, icon = :icon WHERE login = :login';
	const UPDATE_PWD_RQ = 'UPDATE Client SET password = :password WHERE login = :login';
	const DELETE_RQ = 'DELETE FROM Client WHERE login = :login';

	// Message d'erreur en cas d'argument invalide
	
	const INVALID_ARG_ERROR_MSG = 'Seul un client peut-être passé en argument de la méthode ';

	/**
	 * Mettre en persistance un client non persistant
	 * 
	 * @param Client $client
	 * @throws InvalidArgumentException
	 * @return boolean : true si le client a pu être mis en persistance, false sinon
	 */
	public function create(Entity $client) {
		if ($client instanceof Client) {
			unset($client_array['expiration']);
			return parent::getDb() -> exec(self::CREATE_RQ, $client_array);
		} else {
			throw new \InvalidArgumentException(self::INVALID_ARG_ERROR_MSG . __FUNCTION__);
		}
	}

	/**
	 * Récupérer un client par sa clé primaire
	 * 
	 * @param string $login clé primaire
	 * @return Client
	 */
	protected function getByPK($login) {

		$tab = self::getDb() -> exec(self::GET_RQ, array('login' => $login, true));
		if (count($tab) == 1) {
			return new Client($tab[0]);
		}
		return false;
	}

	/**
	 * Alias publique de getByPk
	 */
	public function getByLogin($login) {
		return $this -> getByPK($login);
	}

	/**
	 * Mettre à jour un client persistant
	 * 
	 * @param Client $client
	 * @return boolean
	 * @throws InvalidArgumentException
	 */
	public function update(Entity $client) {
		if ($client instanceof Client) {
			$client_array = $client -> toArray();
			unset($client_array['expiration']);
			unset($client_array['password']);
			return self::getDb()->exec(self::UPDATE_RQ, $client_array);
		} else {
			throw new \InvalidArgumentException(self::INVALID_ARG_ERROR_MSG . __FUNCTION__);
		}
	}

	/**
	 * Supprimer un objet persistant
	 * 
	 * @param string $login
	 * @return boolean
	 */
	protected function deleteByPK($login) {
		return self::getDb()->exec(self::DELETE_RQ, array('login' => $login));
	}

	/**
	 * Alias publique de deleteByPk
	 */
	public function deleteByLogin($login) {
		return $this -> deleteByPK($login);
	}

	/**
	 * Rendre un objet persistant transient
	 * 
	 * @param Client $client
	 * @return boolean
	 * @throws InvalidArgumentException
	 */
	public function delete(Entity $client) {
		if ($client instanceof Client) {
			return $this -> deleteByPK($client -> getLogin());
		} else {
			throw new \InvalidArgumentException(self::INVALID_ARG_ERROR_MSG . __FUNCTION__);
		}
	}

	/**
	 * Récupérer l'intégralité des Clients en persistance
	 * 
	 * @return array
	 */
	public function getAll() {
		$rows = self::getDb()->exec(self::GET_ALL_RQ, array(), true);
		$GLOBALS['logger']->debug("récupération de l'intégralité des clients");
		$GLOBALS['logger']->debug("valeurs :".print_r($rows, true));
		$rslt = array();
		foreach ($rows as $row) {
			array_push($rslt, new Client($row));
		}
		return $rslt;
	}

}
?>