<?php

namespace Apps\Backend\Actions;

use Business\DAO\ClientDAO;
use Library\HttpRequest;
use Library\ModelAndView;
use Library\View;

/**
 * action pour l'affichage de la liste complète des clients
 */
class ListClients extends BackendAction {
	
		/**
		 * dao client
		 */
		private $dao;
		
		public function execute(HttpRequest $request){
			$model = $this -> dao -> getAll();
			return new ModelAndView(new View('show_clients', parent::APP), $model);
		}
		
		/**
		 * setter pour l'injection
		 */
		public function setDao($dao){
			$this -> dao = $dao;
		}
}

?>