<?php

namespace Library;

/**
 * Chargement automatique des classes
 * 
 * ce fichier doit être inclu en début de tout code php de tout fichier du dossier Web
 * 
 * code à reproduire :

		if(!isset($GLOBALS['ROOT'])){
			$GLOBALS['ROOT'] = dirname( dirname(__FILE__) ).'/';
		}
		
		include $GLOBALS['ROOT'].'Library/autoload.php';

 */
class AutoLoader {
	
	/**
	 * méthode d'autoload
	 */
	public static function load($className) {
		$className = ltrim($className, '\\');
		
		// attention : cette variable globale doit avoir été définie auparavant
		$fileName = $GLOBALS['ROOT'];
		
		$namespace = '';
		$extension = '.class.php';
		
		// comportement classique (notre code)
		if ($lastNsPos = strrpos($className, '\\')) {
			$namespace = substr($className, 0, $lastNsPos);
			$className = substr($className, $lastNsPos + 1);
		// log4php n'utilisant pas de namespace, nous devons un petit peu ruser
		} else if (preg_match('#^Logger#', $className)) {
			$namespace = 'log4php';
			$extension = '.php';
		}
		
		$fileName .= str_replace('\\', DIRECTORY_SEPARATOR, $namespace) . DIRECTORY_SEPARATOR;
		$fileName .= str_replace('_', DIRECTORY_SEPARATOR, $className) . $extension;

		require $fileName;
	}

}

/**
 * enregistrement de la méthode d'autoloading
 */
spl_autoload_register(__NAMESPACE__ . "\\Autoloader::load");

?>