<?php

session_start(); 

// récupérer le login de l'utilisateur (via Session)
// si aucun login n'est trouvé, renvoi une chaine de caractère vide, équivalent à false
function getLogin(){
	$rslt = '';
	if(isset($_SESSION['login'])){
		$rslt = $_SESSION['login'];
	}
	return $rslt;
}

function isAdmin(){
	if(getLogin()){
		return $_SESSION['admin'];
	} else {
		return false;
	}
}

?>