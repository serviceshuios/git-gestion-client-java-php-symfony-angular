<?php

namespace Apps\Backend\Actions;

use Library\Action;

/**
 * Classe d'action abstraite pour le Backend
 * 
 * classe mère de toutes les actions du Backend
 */
abstract class BackendAction extends Action {
	
	/**
	 * spécification de l'application sous forme de constante pour plus de simplicité lors de la création d'une vue
	 */
	const APP = 'Backend';
	
}
?>