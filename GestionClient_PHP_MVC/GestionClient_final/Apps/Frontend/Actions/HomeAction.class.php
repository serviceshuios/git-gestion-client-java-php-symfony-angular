<?php

namespace Apps\Frontend\Actions;

use Library\ModelAndView;
use Library\HttpRequest;
use Library\View;

class HomeAction extends FrontendAction {
	
	public function __construct($name, $app){
		parent::__construct($name, $app);
	}
	
	public function execute(HttpRequest $request){
		$GLOBALS['logger']->debug("lancement d'une instance de ".__CLASS__);
		return new ModelAndView(new View('home', parent::APP));
	}
}

?>