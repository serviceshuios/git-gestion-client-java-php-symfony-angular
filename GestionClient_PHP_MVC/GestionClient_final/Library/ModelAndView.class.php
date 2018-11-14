<?php

namespace Library;

/**
 * Classe d'association Vue / Model
 * 
 * permet d'associer facilement Vue et Model lors de l'execution d'une action
 */
class ModelAndView {
	/**
	 * Model
	 * simple tableau associatif, remplissable librement
	 * (vide par défaut)
	 */
	private $model;
	
	/**
	 * Vue (instance de Library\View)
	 */
	private $view;

	public function __construct(View $view, $model) {
		$this -> view = $view;
		$this -> model = $model;
	}

	/**
	 * "Execution" de la vue avec envoi des informations du model
	 * 
	 *	Point final de l'execution : c'est ici que se termine la réalisation d'un action, et l'affichage final sur le navigateur
	 */
	public function follow() {
		
		$GLOBALS['logger']->debug("lancement du ModelAndView pour l'adresse".$this -> view -> getPath());
		$GLOBALS['logger']->debug("model : ".print_r($this->model, true));
		
		// chargement de la vue (stockée dans une variable $content)
		ob_start();
		require $this -> view -> getPath();
		$content = ob_get_clean();
	
		/**
		 * chargement du layout correspondant à l'application de laquelle dépend la vue
		 * et envoi de la totalité du HTML
		 * 
		 * le layout devra imperativement utiliser la variable $content pour afficher la vue
		 * 
		 * code à reproduire dans le layout :
		 * <?= $content >
		 */ 
		ob_start();
		require $GLOBALS['ROOT']. '/Apps/'.$this->view->getApp().'/Template/layout.php';
		return ob_end_flush();
	}

}
?>