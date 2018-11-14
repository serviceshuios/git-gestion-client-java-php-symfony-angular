<?php

namespace Library;

require '../log4php/Logger.php';

/**
 * Configuration
 * 
 * class permettant le chargement de la configuration (par fichier ini ou par défaut)
 */
abstract class Configuration {

	/**
	 * paramètres récupérés à partir du fichier .ini
	 * 
	 * tableau associatif sous la forme :
	 * 		array(
	 * 			section => array (
	 * 				param : valeur
	 * 			)
	 * 		)
	 */
	private static $params;

	/**
	 * Valeurs par défaut pour les paramètres
	 * établies par php (cf self::init()) sous la même forme
	 * que $params 
	 */
	private static $default_values;

	/**
	 * chemins vers les deux fichiers .ini possibles :
	 * 		- dev (pour le développement)
	 * 		- prod (pour la mise en production)
	 */
	const PATHS = array('dev' => 'Conf/dev.ini', 'prod' => 'Conf/prod.ini');

	/**
	 * permet de récupérer la valeur (dans le fichier ini ou par défaut) d'un paramètre
	 * 
	 * @param string $section section
	 * @param string $key clé
	 */
	public static function get($section, $key) {
		if (isset(self::getParams()[$section][$key])) {
			$rslt = self::getParams()[$section][$key];
		} else {
			$rslt = self::getDefaut_values()[$section][$key];
		}
		return $rslt;
	}

	/**
	 * Getter pour l'attribut params
	 */
	private static function getParams() {
		if (self::$params == null) {
			self::init();
		}
		return self::$params;
	}

	/**
	 * Getter de l'attribut default_values
	 */
	private static function getDefaut_values() {
		if (self::$default_values == null) {
			self::init();
		}
		return self::$default_values;
	}
	
	/**
	 * méthode d'initialisation de la configuration
	 * ne doit être appellée qu'une fois dans le programme, juste après la définition de $GLOBALS['ROOT']
	 * et juste avant le chargement de l'autoload
	 * (cf le FrontController)
	 */
	public static function init() {
		// vérifie si la méthode n'a pas déjà été appelée
		if (!isset($GLOBALS['logger']) || self::$params == null || self::$default_values == null) {

			// vérifie si le fichier de configuration 'dev' existe, sinon, vérifie que le fichier 'prod' existe
			foreach (self::PATHS as $path) {
				if (file_exists($GLOBALS['ROOT'] . $path)) {
					// chargement du fichier de configuration (dev si existe, prod sinon)
					self::$params = parse_ini_file($GLOBALS['ROOT'] . $path, true);
				}
			}

			// définition des valeurs par défaut
			self::$default_values = array('DB' => array('engine' => 'pdo', 'dsn' => 'mysql:host=localhost;dbname=gestion_client_php;charset=utf8', 'login' => 'root', 'password' => ''), 'XML' => array('actions' => 'Conf/actions.xml', 'objects' => 'Conf/beans.xml', ), 'LOG' => array('file' => 'Conf/log4php.xml'));

			// création du logger
			\Logger::configure($GLOBALS['ROOT'] . self::get('LOG', 'file'));

			$GLOBALS['logger'] = \Logger::getLogger('myLogger');
			$GLOBALS['logger'] -> info("-- chargement du logger --");
		}
	}

}
