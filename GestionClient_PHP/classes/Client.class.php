<?php

require_once 'FileNotFoundException.class.php';
require_once 'InvalidExpressionException.class.php';
require_once 'Entity.class.php';


class Client extends Entity {
	private $login;
	private $nom;
	private $prenom;
	private $password;
	private $commentaire;
	private $icon;
	private $expiration;
	private $admin;
	
	const PWD_REGEX = "#^(?=[a-zA-Z0-9]{4,12}$)(?=.*[A-Z])(?=.*[0-9])#";
	const LOGIN_REGEX = "#^[a-z]{2,50}$#i";
	const NAME_REGEX = "#^[\p{L}- ]{2,50}$#u";
	
	public function getLogin(){
		return $this->login;
	}
	
	public function getPk(){
		return $this -> getLogin();
	}
	
	public function setLogin($login){
		if(preg_match(self::LOGIN_REGEX, $login)){
			$this->login = htmlspecialchars($login);
		} else {
			throw new InvalidExpressionException("le login ne doit être composé que de lettres");
		}
	}
	
	public function getNom(){
		return $this->nom;
	}
	
	public function setNom($nom){
		if(preg_match(self::NAME_REGEX, $nom)){
			$this->nom = htmlspecialchars($nom);
		} else {
			throw new InvalidExpressionException("le nom ne doit comporter que des lettres, des tirets ou des espaces");
		}
	}
	
	public function getPrenom(){
		return $this->prenom;
	}
	
	public function setPrenom($prenom){
		if(preg_match(self::NAME_REGEX, $prenom)){
			$this->prenom = htmlspecialchars($prenom);
		} else {
			throw new InvalidExpressionException("le prenom ne doit comporter que des lettres, des tirets ou des espaces");
		}
	}
	
	public function getPassword(){
		return $this->password;
	}
	
	public function setPassword($password){
		$this->password = $password;
	}
	
	public function setClearPassword($password){
		if(preg_match(self::PWD_REGEX, $password)){
			$this->password = md5($password);
		} else {
			throw new InvalidExpressionException("le mot de passe ne doit comporter que des caractères alphanumériques, et au moins une lettre majuscule et un chiffre");
		}
	}
	
	public function getCommentaire(){
		return $this->commentaire;
	}
	
	public function setCommentaire($commentaire){
		if(strlen($commentaire) <= 500){
			$this->commentaire = htmlspecialchars($commentaire);
		} else {
			throw new LengthException('le commentaire doit faire moins de 500 caractères');
		}
	}
	
	public function getIcon(){
		return $this->icon;
	}
	
	public function setIcon($icon){
		$this->icon = $icon;
	}
	
	public function getExpiration(){
		return $this->expiration;
	}
	
	public function setExpiration($expiration){
		if(date_parse($expiration) !== false){
			$this->expiration = $expiration;
		} else {
			throw new DomainException($expiration." n'est pas une date");
		}
	}
	
	public function isAdmin(){
		return $this->admin;
	}
	
	public function setAdmin($admin){
		$this->admin = (boolean) $admin;	
	}
	
	public function __construct(array $values){
		$this -> setAdmin(false);
		$this->hydrate($values);
	}
	
	public function hydrate(array $values){
		foreach ($values as $key => $value) {
			// On récupère le nom du setter correspondant à l'attribut.
			$method = 'set' . ucfirst($key);

			// Si le setter correspondant existe.
			if (method_exists($this, $method)) {
				// On appelle le setter.
				$this -> $method($value);
			} else {
				throw new BadMethodCallException('la muttateur de l\'attribut '.$key.' n\'existe pas');
			}
		}
	}
	
	public function toArray(){
		return get_object_vars($this);
	}

}

?>