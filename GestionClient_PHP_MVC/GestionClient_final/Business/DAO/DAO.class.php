<?php

namespace Business\DAO;

use Business\Entities\Entity;
use Library\DB;

/**
 * Classe abstraite destinée à définit les fonctions communes de la DAO
 */
abstract class DAO {

	/**
	 * instance de la classe DB, permettant la création d'une connexion et l'execution d'une requête
	 */
	private static $db;
	
	// méthodes abstraites pour la définition de méthodes communes à toute classe de DAO

	abstract public function create(Entity $obj);

	abstract public function update(Entity $obj);

	abstract public function delete(Entity $obj);

	abstract protected function deleteByPk($pk);

	abstract public function getAll();

	abstract protected function getByPK($pk);

	/**
	 * Création ou mise à jour d'une entité en fontion de l'état de persistance de l'objet
	 * 
	 * @param Entity $obj
	 * @return boolean
	 */
	public function createOrUpdate(Entity $obj) {
		if ($this -> getByPk($obj -> getPk())) {
			return $this -> update($obj);
		} else {
			return $this -> create($obj);
		}
	}
	
	/**
	 * Récupère l'instance de DB pour l'execution de requètes
	 */
	protected static function getDb(){
		if(self::$db === null){
			self::$db = new DB();
		}
		return self::$db;
	}

}
?>