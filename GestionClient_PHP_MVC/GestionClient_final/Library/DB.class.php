<?php

namespace Library;

use \PDO;

/**
 * classe centrale pour la connexion à une base de donnée et l'execution de requête, via PDO
 */
class DB {
	
	/**
	 * objet PDO
	 */
	private $pdo;
	
	/**
	 * execution d'une requête
	 * 
	 * @param string $request la requête (avec paramètres nommés)
	 * @param array $param tableau associatif des valeurs à affecter aux paramètres de la requête
	 * @param boolean $isAQuery (false par défaut) défini si la requête doit renvoyer des valeurs (sous forme de tableau)
	 * 
	 * @return mixed (boolean / array) valeurs à retourner, ou true
	 * @throws Exception en cas d'echec de la requête
	 */
	public function exec($request, array $params, $isAQuery = false){
		$st = $this->getPdo()->prepare($request);
		if($st->execute($params)){
			if($isAQuery){
				return $st->fetchAll(PDO::FETCH_ASSOC);
			} else {
				return true;
			}
		}
		throw new \Exception("l'execution de la requête ".$request." n'a pas pu se réaliser");
	}
	
	/**
	 * Connexion à la base (une seule fois par instance de DB)
	 */
	private function getPdo(){
		if($this->pdo == null){
			$this->pdo = new PDO(Configuration::get('DB', 'dsn'), Configuration::get('DB', 'login'), Configuration::get('DB', 'password'));
		}
		return $this->pdo;
	}
	
	/**
	 * deconnexion de la base
	 */
	public function __destruct(){
		$this->pdo = null;
	}
}

?>