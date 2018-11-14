<?php

require_once 'DAO.class.php';
require_once 'ClientDAO.class.php';
require_once dirname(__FILE__) . '/../Bdc.class.php';

class BdcDAO extends DAO {
	
	private $_clientDAO;

	const CREATE_RQ = 'CALL createBdc(:commentaire, :montant, :delais, :client_login)';
	const GET_RQ = 'SELECT * FROM BonDeCommande WHERE id = :id';
	const GET_BYLOGIN_RQ = 'CALL getBdcByClient(:login)';
	const GET_ALL_RQ = 'SELECT * FROM BonDeCommande';
	const UPDATE_RQ = 'UPDATE BonDeCommande SET commentaire = :commentaire, montant = :montant, delais = :delais WHERE id = :id';
	const DELETE_RQ = 'DELETE FROM BonDeCommande WHERE id = :id';

	const INVALID_ARG_ERROR_MSG = 'Seul un bon de commande peut-être passé en argument de la méthode ';

	public function __construct() {
		parent::__construct();
		$this -> _clientDAO = new ClientDAO();
	}

	public function __desctuct() {
		parent::__destruct();
	}

	public function create(Entity $bdc) {
		if ($bdc instanceof Bdc) {

			$st = $this -> db -> prepare(self::CREATE_RQ);
			$bdc_array = $bdc -> toArray();
			unset($bdc_array['id']);
			$bdc_array['client_login'] = $bdc_array['client'] -> getLogin();
			unset($bdc_array['client']);
			unset($bdc_array['date']);

			error_log(print_r($bdc_array, true));
			return $st -> execute($bdc_array);
		} else {
			throw new InvalidArgumentException(self::INVALID_ARG_ERROR_MSG . __FUNCTION__);
		}
	}

	protected function getByPK($id) {
		$st = $this -> db -> prepare(self::GET_RQ);
		if ($st -> execute(array('id' => $id))) {
			$rows_tab = $st -> fetchAll(PDO::FETCH_ASSOC);
			$nbr_rows = sizeof($rows_tab);
			if ($nbr_rows == 1) {
				return new Bdc($rows_tab[0]);
			}
		}
		return false;
	}

	public function getById($id) {
		return $this -> getByPK($id);
	}

	public function getByClientLogin($login) {
		$st = $this -> db -> prepare(self::GET_BYLOGIN_RQ);
		if ($st -> execute(array('login' => $login))) {
			$rows_tab = $st -> fetchAll(PDO::FETCH_ASSOC);
			$rslt = array();
			foreach ($rows as $row) {
				array_push($rslt, new Bdc($row));
			}
			return $rslt;
		}
		return false;
	}

	public function update(Entity $bdc) {
		if ($bdc instanceof Bdc) {

			$st = $this -> db -> prepare(self::UPDATE_RQ);
			$bdc_array = $bdc -> toArray();
			unset($bdc_array['client']);
			return $st -> execute($bdc_array);
		} else {
			throw new InvalidArgumentException(self::INVALID_ARG_ERROR_MSG . __FUNCTION__);
		}
	}

	protected function deleteByPK($id) {
		$st = $this -> db -> prepare(self::DELETE_RQ);
		return $st -> execute(array('id' => $id));
	}

	public function deleteById($id) {
		return $this -> deleteByPK($id);
	}

	public function delete(Entity $bdc) {
		if ($bdc instanceof Bdc) {
			return $this -> deleteByPK($bdc -> getId());
		} else {
			throw new InvalidArgumentException(self::INVALID_ARG_ERROR_MSG . __FUNCTION__);
		}
	}

	public function getAll() {
		$rows = $this -> db -> query(self::GET_ALL_RQ) -> fetchAll(PDO::FETCH_ASSOC);
		$rslt = array();
		foreach ($rows as $row) {
			$row['client'] = $this -> _clientDAO -> getByLogin($row['client']);
			array_push($rslt, new Bdc($row));
		}
		return $rslt;
	}

}
?>