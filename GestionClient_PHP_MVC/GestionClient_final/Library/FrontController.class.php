<?php

namespace Library;

use \DOMElement;

use Library\HttpRequest;

/**
 * Controleur central
 *
 * gère l'ensemble des requêtes, redirigées par la page par défaut dans le dossier Web
 * afin d'y associer une action, définie par le fichier de configuration Conf/actions.xml
 */
class FrontController {
	/**
	 * Toutes les actions possibles
	 * chargées à partir de Conf/actions.xml
	 */
	private $actions = array();

	/**
	 * Unique instance de cette classe (singleton)
	 */
	private static $instance;

	private function __construct() {
		$this -> loadActions();
	}

	/**
	 * Chargement de toutes les actions à partir du fichier de configuration
	 */
	private function loadActions() {

		$GLOBALS['ROOT'] = dirname(dirname(__FILE__)) . '/';

		require 'Configuration.class.php';

		Configuration::init();

		require 'autoload.php';

		$GLOBALS['logger'] -> info("Chargement du Frontcontroller");

		$GLOBALS['logger'] -> debug("actions ...");

		$xml = new \DOMDocument;
		$xml -> load($GLOBALS['ROOT'] . Configuration::get('XML', 'actions'));
		$actions_tag_list = $xml -> getElementsByTagName('actions');
		foreach ($actions_tag_list as $actions_tag) {
			$app = $this -> getTagAttribute($actions_tag, 'app');
			$GLOBALS['logger'] -> info("chargement des actions de l'application " . $app);
			$namespace = $this -> getTagAttribute($actions_tag, 'namespace', true);
			$GLOBALS['logger'] -> debug("le namespace a été défini à " . $namespace);
			$action_tag_list = $actions_tag -> getElementsByTagName('action');
			$GLOBALS['logger'] -> debug((count($action_tag_list) + 1) . " actions ont été trouvées");
			foreach ($action_tag_list as $action_tag) {
				$name = $this -> getTagAttribute($action_tag, 'name');
				$GLOBALS['logger'] -> debug("chargement de l'action " . $name);
				$class = $this -> getTagAttribute($action_tag, 'class', true);
				if ($class !== '') {
					$className = $namespace . $class;
					$GLOBALS['logger'] -> debug("class = " . $class . " - app = " . $app . " - className = " . $className);
					array_push($this -> actions, new $className($name, $app));
				} else {
					$ref = $this -> getTagAttribute($action_tag, 'ref');
					$GLOBALS['logger'] -> debug("récupération de l'objet ".$ref);
					array_push($this -> actions, ObjectFactory::getObject($ref));
					//$this -> actions[] = &ObjectFactory::getObject($ref);
				}

			}
		}
	}

	/**
	 * Récupération de la valeur d'un attribut d'un tag XML
	 *
	 * @param DOMElement $tag balise de laquelle on souhaite récupérer l'attribut
	 * @param string $attribute nom de l'attribut dont on souhaite récupérer la valeur
	 * @param boolean $optionnal spécifie si cet attribut est optionnel ou non
	 *
	 * @return string la valeur de la chaine de caractère (vide si abscent et optionnel)
	 * @throws Exception dans le cas où l'attribut ne soit pas optionnel, cette exception est levée si l'attribut n'est pas présent sur la balise
	 */
	private function getTagAttribute($tag, $attribute, $optionnal = false) {
		if ($tag -> hasAttribute($attribute)) {
			return $tag -> getAttribute($attribute);
		} else if ($optionnal === false) {
			throw new \Exception('Fichier actions.xml : une balise ' . $tag -> tagName . ' doit impérativement posséder un attribut ' . $attribute);
		}
		return '';
	}

	/**
	 * setter
	 * Ajout d'une action dans le tableau $actions
	 */
	private function addAction(Action $action) {
		if (!in_array($action, $this -> actions)) {
			array_push($this -> actions, $action);
		}
	}

	/**
	 * Récupération d'un action dans $actions
	 *
	 * @param string $name nom de l'action
	 * @param string $app nom de l'application de cette action
	 * @param boolean $getDefault si false, lève une exception en cas d'abscence de cette action (true par défaut)
	 *
	 * @return Action
	 * @throws Exception si l'action n'existe pas et qu'il n'y a pas de valeur par défaut pour cette application (ou que celle-ci n'a pas été autorisée)
	 */
	private function getAction($name, $app, $getDefault = true) {
		foreach ($this->actions as $action) {
			if ($action -> getName() == $name && $action -> getApp() == $app) {
				return $action;
			}
		}
		// TODO : ajouter un balise default-action
		if ($getDefault) {
			return $this -> getAction('home', $app, false);
		}
		throw new \Exception("Il n'existe pas d'action " . $app . '/' . $name);
	}

	/**
	 * gestion de la requête reçue
	 * cette méthode est celle à appeler dans le fichier par défaut du dossier Web
	 *
	 * code à reproduire :
	 * FrontController::getInstance()->handle(new HttpRequest($_SERVER, $_GET, $_POST));
	 *
	 * @param HttpRequest $request requète à gérer
	 */
	public function handle(HttpRequest $request) {
		if (preg_match('#^/backend#i', $request -> getUrl())) {
			$app = "backend";
		} else {
			$app = "frontend";
		}
		$name = basename($request -> getUrl());
		$this -> launchAction($name, $app, $request);
	}

	/**
	 * Execution d'une action
	 *
	 * @param string $name nom de l'action
	 * @param string $app nom de l'application de l'action
	 * @param HttpRequest $request requête à transférer à l'action
	 */
	private function launchAction($name, $app, HttpRequest $request) {
		$GLOBALS['logger'] -> debug("lancement de l'action " . $app . "/" . $name);
		$action = $this -> getAction($name, $app);
		$modelAndView = $action -> execute($request);
		$modelAndView -> follow();
	}

	/**
	 * Récupération de l'unique instance (singleton) de cette classe
	 * remplace l'appel au constructeur quelque soit le contexte
	 *
	 * @return FrontController l'instance
	 */
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new self();
		}
		return self::$instance;
	}

}
?>