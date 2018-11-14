<?php

require_once dirname(__FILE__) . '/../Entity.class.php';

abstract class DAO {

	protected $db;

	protected function __construct() {
		$this -> db = new PDO('mysql:host=localhost;dbname=gestion_client_php', 'php_test', 'php');
	}

	protected function __destruct() {
		$this -> db = null;
	}

	abstract public function create(Entity $obj);

	abstract public function update(Entity $obj);

	abstract public function delete(Entity $obj);

	abstract protected function deleteByPk($pk);

	abstract public function getAll();

	abstract protected function getByPK($pk);

	public function createOrUpdate(Entity $obj) {
		if ($this -> getByPk($obj -> getPk())) {
			return $this -> update($obj);
		} else {
			return $this -> create($obj);
		}
	}

}
?>