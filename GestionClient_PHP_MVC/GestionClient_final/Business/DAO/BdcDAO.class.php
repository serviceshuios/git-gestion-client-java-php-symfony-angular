<?php

namespace Business\DAO;

use Business\Entities\Bdc;
use Business\Entities\Client;
use Business\Entities\Entity;

/**
 * DAO pour les bons de commande, via PDO
 */
class BdcDAO extends DAO {
	
	// DAO du client, nécessaire pour l'association
	private $_clientDAO;

	// Requêtes
	const CREATE_RQ = 'CALL createBdc(:commentaire, :montant, :delais, :client_login)';
	const GET_RQ = 'SELECT * FROM BonDeCommande WHERE id = :id';
	const GET_BYLOGIN_RQ = 'CALL getBdcByClient(:login)';
	const GET_ALL_RQ = 'SELECT * FROM BonDeCommande';
	const UPDATE_RQ = 'UPDATE BonDeCommande SET commentaire = :commentaire, montant = :montant, delais = :delais WHERE id = :id';
	const DELETE_RQ = 'DELETE FROM BonDeCommande WHERE id = :id';

	// message d'erreur à renvoyer en cas d'argument invalide sur une méthode
	const INVALID_ARG_ERROR_MSG = 'Seul un bon de commande peut-être passé en argument de la méthode ';

	public function __construct() {
		parent::__construct();
		$this -> _clientDAO = new ClientDAO();
	}

	public function __destruct() {
		parent::__destruct();
	}

	/**
	 * Mise en persistance d'un bon de commande
	 * 
	 * @param Bdc $bdc
	 * @return boolean
	 * @throws InvalidArgumentException
	 */
	public function create(Entity $bdc) {
		if ($bdc instanceof Bdc) {
			$bdc_array = $bdc -> toArray();
			unset($bdc_array['id']);
			$bdc_array['client_login'] = $bdc_array['client'] -> getLogin();
			unset($bdc_array['client']);
			unset($bdc_array['date']);
			return self::getDb()->exec(self::CREATE_RQ, $bdc_array, true);
		} else {
			throw new \InvalidArgumentException(self::INVALID_ARG_ERROR_MSG . __FUNCTION__);
		}
	}

	/**
	 * Récupération d'un Bdc par son id
	 * 
	 * @param string $id
	 * @return Bdc
	 */
	protected function getByPK($id) {
		$tab = self::getDb()->exec(self::GET_RQ, array('id' => $id), true);
		if (count($tab) == 1) {
			return new Bdc($tab[0]);
		}
		return false;
	}

	/**
	 * Alias publique de getByPk
	 */
	public function getById($id) {
		return $this -> getByPK($id);
	}

	/**
	 * Récupération de l'ensemble des bdc d'un client
	 * 
	 * @param string $login
	 * @return mixed : array ou false si l'opération n'a pas aboutie
	 */
	public function getByClientLogin($login) {
		$tab = self::getDb()->exec(self::GET_BYLOGIN_RQ, array('login' => $login), true);
		if ($tab !== false) {
			$rslt = array();
			foreach ($tab as $row) {
				array_push($rslt, new Bdc($row));
			}
			return $rslt;
		}
		return false;
	}

	/**
	 * Mise à jour d'un bdc persistant
	 * 
	 * @param Bdc $bdc
	 * @return boolean
	 * @throws InvalidArgumentException
	 */
	public function update(Entity $bdc) {
		if ($bdc instanceof Bdc) {
			$bdc_array = $bdc -> toArray();
			unset($bdc_array['client']);
			return self::getDb()->exec(self::UPDATE_RQ, $bdc_array);
		} else {
			throw new \InvalidArgumentException(self::INVALID_ARG_ERROR_MSG . __FUNCTION__);
		}
	}

	/**
	 * Supprimer un bdc de la base
	 * 
	 * @param string $id
	 * @return boolean
	 */
	protected function deleteByPK($id) {
		return self::getDb()->exec(self::DELETE_RQ, array('id' => $id));
	}

	/**
	 * Alias publique de deleteByPk
	 */
	public function deleteById($id) {
		return $this -> deleteByPK($id);
	}

	/**
	 * Passer un bdc de persistant à transient
	 * 
	 * @param Bdc $bdc
	 * @return boolean
	 * @throws InvalidArgumentException
	 */
	public function delete(Entity $bdc) {
		if ($bdc instanceof Bdc) {
			return $this -> deleteByPK($bdc -> getId());
		} else {
			throw new \InvalidArgumentException(self::INVALID_ARG_ERROR_MSG . __FUNCTION__);
		}
	}

	/**
	 * Récupérer l'ensemble des bdc persistant
	 * 
	 * @return mixed : array ou false si n'a pas abouti
	 */
	public function getAll() {
		$tab = self::getDb()->exec(self::GET_ALL_RQ, null, true);
		$rslt = array();
		foreach ($tab as $row) {
			$row['client'] = $this -> _clientDAO -> getByLogin($row['client']);
			array_push($rslt, new Bdc($row));
		}
		return $rslt;
	}

}
?>