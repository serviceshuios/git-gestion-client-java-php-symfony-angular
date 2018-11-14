<?php

require_once 'DAO.class.php';
require_once dirname(__FILE__) . '/../Client.class.php';

class ClientDAO extends DAO {

	const CREATE_RQ = 'INSERT INTO Client (login, password, nom, prenom, commentaire, expiration, admin, icon) VALUES (:login, :password, :nom, :prenom, :commentaire, NOW() + INTERVAL 1 YEAR, :admin, :icon) ';
	const GET_RQ = 'SELECT * FROM Client WHERE login = :login';
	const GET_ALL_RQ = 'SELECT * FROM Client';
	const UPDATE_RQ = 'UPDATE Client SET nom = :nom, prenom = :prenom, commentaire = :commentaire, admin = :admin, icon = :icon WHERE login = :login';
	const UPDATE_PWD_RQ = 'UPDATE Client SET password = :password WHERE login = :login';
	const DELETE_RQ = 'DELETE FROM Client WHERE login = :login';

	const INVALID_ARG_ERROR_MSG = 'Seul un client peut-être passé en argument de la méthode ';

	public function __construct() {
		parent::__construct();
	}

	public function __desctuct() {
		parent::__destruct();
	}

	public function create(Entity $client) {
		if ($client instanceof Client) {
			$st = $this -> db -> prepare(self::CREATE_RQ);
			$client_array = $client -> toArray();
			unset($client_array['expiration']);
			return $st -> execute($client_array);
		} else {
			throw new InvalidArgumentException(self::INVALID_ARG_ERROR_MSG . __FUNCTION__);
		}

	}

	protected function getByPK($login) {
		$st = $this -> db -> prepare(self::GET_RQ);
		if ($st -> execute(array('login' => $login))) {

			$rows_tab = $st -> fetchAll(PDO::FETCH_ASSOC);

			$nbr_rows = sizeof($rows_tab);


			if ($nbr_rows == 1) {
				return new Client($rows_tab[0]);
			}
		}
		return false;
	}

	public function getByLogin($login) {
		return $this -> getByPK($login);
	}

	public function update(Entity $client) {
		if ($client instanceof Client) {
			$st = $this -> db -> prepare(self::UPDATE_RQ);
			$client_array = $client -> toArray();
			unset($client_array['expiration']);
			unset($client_array['password']);
			return $st -> execute($client_array);
		} else {
			throw new InvalidArgumentException(self::INVALID_ARG_ERROR_MSG . __FUNCTION__);
		}
	}

	protected function deleteByPK($login) {
		$st = $this -> db -> prepare(self::DELETE_RQ);
		return $st -> execute(array('login' => $login));
	}

	public function deleteByLogin($login) {
		return $this -> deleteByPK($login);
	}

	public function delete(Entity $client) {
		if ($client instanceof Client) {

			return $this -> deleteByPK($client -> getLogin());
		} else {
			throw new InvalidArgumentException(self::INVALID_ARG_ERROR_MSG . __FUNCTION__);
		}
	}

	public function getAll() {
		$rows = $this -> db -> query(self::GET_ALL_RQ) -> fetchAll(PDO::FETCH_ASSOC);
		$rslt = array();
		foreach ($rows as $row) {
			error_log(print_r($row, true));
			array_push($rslt, new Client($row));
		}
		return $rslt;
	}

}
?>