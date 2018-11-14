<?php

namespace Business\Entities;

/**
 * Classe abstraite Entity
 * 
 * éléments communs à toute entité
 */
abstract class Entity {
	
	// protected $isPersistant = false;
	
	/**
	 * Transformation de l'instance en tableau nommé
	 * association nom d'attribut => valeur
	 */
	abstract public function toArray();
	
	/**
	 * Getter de la primary key
	 * Alias vers la méthode adéquate en fonction du nom de l'attribut
	 */
	abstract function getPk();
	
	public function isPersistant(){
		return $this->isPersistant;
	}
	
	/*
	public function setPersistant($value){
		if(!is_bool($value)){
			throw new InvalidArgumentException('isPersistant ne peu prendre qu\'un booléen en argument');
		}
		$this->isPersistant = $value;
	}
	 */
}

?>