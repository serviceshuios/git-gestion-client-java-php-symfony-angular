<?php

namespace Library;

/**
 * Vue
 * 
 * Représentation d'une vue pour l'affichage dans un layout via un ModelAndView renvoyé par l'execution d'une action
 */
class View {
	
	/**
	 * nom de la vue
	 */
	private $name;
	
	/**
	 * nom de l'application à laquelle appartient la vue
	 */
	private $app;
	
	public function __construct($name, $app){
		$GLOBALS['logger']->debug("construction d'une nouvelle vue ".$app.'/'.$name);
		$this -> name = $name;
		$this -> app = $app;
	}
	
	public function getName(){
		return $this -> name;
	}
	
	public function getApp(){
		return $this->app;
	}
	
	/**
	 * récupération du chemin vers la vue
	 * à partir du nom de l'application et de la vue
	 * 
	 * @return string
	 */
	public function getPath(){
		return $GLOBALS['ROOT'].'Apps/'.$this-> app.'/Views/'.$this-> name.'.php';
	}
}
?>