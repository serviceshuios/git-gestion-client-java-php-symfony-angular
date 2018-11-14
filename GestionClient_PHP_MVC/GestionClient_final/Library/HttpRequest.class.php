<?php

namespace Library;

/**
 * Requête HTTP
 * 
 * représentation d'une requête HTTP pour la gestion par une action via le FrontController
 */
class HttpRequest {
	
	/**
	 * données ($_GET ou $_POST)
	 */
	private $datas;
	
	/**
	 * url demandée
	 */
	private $url;

	/**
	 * Construction d'une nouvelle requête
	 * 
	 * code à reproduire :
	 * FrontController::getInstance()->handle(new \Library\HttpRequest($_SERVER, $_GET, $_POST));
	 */
	public function __construct(array $server, array $get, array $post) {
		$GLOBALS['logger'] -> debug("nouvelle requête " . $server['REQUEST_URI']);

		$this -> datas = array();
		// en fonction de la méthode utiliser pour la requête
		switch ($server['REQUEST_METHOD']) {
			case 'GET' :
				// stocker les informations de $_GET
				array_merge($this -> datas, $get);
				break;
			case 'POST' :
				// ou les informations de $_POST
				array_merge($datas, $post);
				break;
		}
		
		// récupération de l'url demandée
		$this -> url = $server['REQUEST_URI'];

	}

	public function getData($key) {
		return isset($datas[$key]) ? $datas[$key] : null;
	}

	public function issetData($key) {
		return isset($datas[$key]);
	}

	public function getUrl() {
		return $this -> url;
	}

}
?>