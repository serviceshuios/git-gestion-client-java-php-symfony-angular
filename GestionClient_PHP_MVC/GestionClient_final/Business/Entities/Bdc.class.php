<?php

namespace Business\Entities;

use Library\Exceptions\InvalidExpressionException;


/**
 * Entité Bdc
 * 
 * Représente un bon de commande
 * Destiné à la persistance
 * 
 * TODO : externaliser la validation des champs
 */
class Bdc extends Entity {
	
	// attributs : noms identiques à ceux des champs des DB
	
	private $id;
	private $client;
	private $commentaire;
	private $montant;
	private $date;
	private $delais;

	public function getId() {
		return $this -> id;
	}
	
	public function getPk(){
		return $this -> getId();
	}

	public function setId($id) {
		$this -> id = $id;
	}

	public function getClient() {
		return $this -> client;
	}

	public function setClient($arg) {
		if($arg instanceof Client){
			$this -> client = $arg;
		} else {
			$this -> client = new Client(array('login' => $arg));
		}
	}

	public function getCommentaire() {
		return $this -> commentaire;
	}

	public function setCommentaire($commentaire) {
		if (strlen($commentaire) <= 255) {
			$this -> commentaire = $commentaire;
		} else {
			throw new \LengthException('le commentaire doit faire moins de 255 caractères');
		}
	}

	public function getMontant() {
		return $this -> montant;
	}

	public function setMontant($montant) {
		if (is_numeric($montant)) {
			if ($montant > 0 && $montant <= 999999.99) {
				$this -> montant = $montant;
			} else {
				throw new \RangeException($montant." n'est pas compris entre 0 et 999999.99");
			}
		} else {
			throw new InvalidArgumentException($montant . " n'est pas un nombre");
		}
	}

	public function getDate() {
		return $this -> date;
	}

	public function setDate($date) {
		if(date_parse($date) !== false){
			$this -> date = $date;
		} else {
			throw new \DomainException($date." n'est pas une date");
		}
	}

	public function getDelais() {
		return $this -> delais;
	}

	public function setDelais($delais) {
		if(ctype_digit($delais)){
			if($delais > 0 && $delais <= 365){
				$this -> delais = $delais;
			} else {
				throw new \RangeException($delais." n'est pas compris entre 0 et 365");
			}
		} else {
			throw new InvalidArgumentException($delais . " n'est pas un nombre entier");
		}
	}

	public function __construct(array $values) {
		$this -> hydrate($values);
	}

	public function hydrate(array $values) {
		foreach ($values as $key => $value) {
			// On récupère le nom du setter correspondant à l'attribut.
			$method = 'set' . ucfirst($key);

			// Si le setter correspondant existe.
			if (method_exists($this, $method)) {
				// On appelle le setter.
				$this -> $method($value);
			} else {
				throw new \BadMethodCallException('la muttateur de l\'attribut ' . $key . ' n\'existe pas');
			}
		}
	}
	
	public function toArray(){
		return get_object_vars($this);
	}

}
?>