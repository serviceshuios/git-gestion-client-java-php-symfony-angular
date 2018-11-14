<?php

namespace Apps\Frontend\Actions;

use Library\Action;

/**
 * Classe d'action abstraite pour le Frontend
 * 
 * classe mère de toutes les actions du Frontend
 */
abstract class FrontendAction extends Action {
		
	/**
	 * spécification de l'application sous forme de constante pour plus de simplicité lors de la création d'une vue
	 */
	const APP = 'Frontend';
	
}
?>