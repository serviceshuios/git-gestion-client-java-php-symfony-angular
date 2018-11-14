<?php

/**
 * fichier central pour la gestion des requêtes du client
 * 
 * toutes les requêtes sont redirigées vers cette page grâce au fichier .htaccess 
 */
 
require '../Library/FrontController.class.php';

\Library\FrontController::getInstance()->handle(new \Library\HttpRequest($_SERVER, $_GET, $_POST));

?>