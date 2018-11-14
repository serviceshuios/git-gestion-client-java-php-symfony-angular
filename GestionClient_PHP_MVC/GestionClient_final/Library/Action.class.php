<?php

namespace Library;

/**
 * classe abstraite Action
 * 
 * Classe à hériter pour réaliser une classe d'action
 * destinée à répondre à une url donnée, et à réaliser un traitement avant le renvoi d'un model et d'une vue
 */
abstract class Action {
	
	/**
	 * nom de l'application de l'action
	 */
	private $app;
	
	/**
	 * nom de l'action (son uri)
	 */
	private $name;
	
	public function __construct($name, $app){
		$GLOBALS['logger']->debug("nouvelle action ".__CLASS__." - ".$app.'/'.$name);
		$this -> setName($name);
		$this -> setApp($app);
	}
	
	private function setName($name){
		if(is_string($name)){
			$this -> name = $name;
		}
	}
		
	public function getName(){
		$GLOBALS['logger']->debug("Nom de l'action : ".$this -> name);
		return $this -> name;
	}
	
	
	public function getApp(){
		return $this -> app;
	}
	
	public function setApp($app){
		$this -> app = $app;
	}
	
	/**
	 * méthode d'execution de l'action
	 * 
	 * @param HttpRequest $request requête à traiter
	 * 
	 * @return ModelAndView
	 */
	public abstract function execute(HttpRequest $request);
} 
?>