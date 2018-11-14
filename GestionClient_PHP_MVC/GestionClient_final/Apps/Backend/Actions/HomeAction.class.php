<?php

namespace Apps\Backend\Actions;

use Library\ModelAndView;
use Library\HttpRequest;
use Library\View;

/**
 * classe d'action de la page d'acceuil du backend
 * 
 */
class HomeAction extends BackendAction {
	
	public function __construct($name, $app){
		parent::__construct($name, $app);
	}
	
	public function execute(HttpRequest $request){
		$GLOBALS['logger']->debug("lancement d'une instance de ".__CLASS__);
		// renvoi vers la vue Backend/home sans model
		return new ModelAndView(new View('home', parent::APP));
	}
}

?>