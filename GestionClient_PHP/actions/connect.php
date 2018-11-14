<?php

session_start();

if (isset($_POST['login']) && isset($_POST['password'])) {

	// connection à la base de données
	include ('../snippets/dbConnect.php');

	$st = $bdd -> prepare('SELECT * FROM Client WHERE login = :login AND password = :password');

	$_POST['login'] = strip_tags($_POST['login']);
	$_POST['password'] = strip_tags($_POST['password']);

	$login_len = strlen($_POST['login']);
	$pwd_len = strlen($_POST['password']);

	// vérification de la taille du login et du mot de passe
	if ($login_len > 2 && $login_len < 50 && $pwd_len > 3 && $pwd_len < 12) {
		$st -> bindValue(':login', $_POST['login']);
		$st -> bindValue(':password', md5($_POST['password']));
		$st -> execute();

		// si une ligne a été trouvée
		if ($client = $st -> fetch()) {
			$_SESSION['login'] = $_POST['login'];
			$_SESSION['admin'] = (boolean) $client['admin'];
			if(file_exists('../uploads/'.$client['icon'])){
				$_SESSION['icon'] = $client['icon'];
			}
			
			if(isset($_POST['redirect']) && $_POST['redirect'][0] == '/'){
				header('Location: '.$_POST['redirect']);
			} else {
				header('Location: ../index.php');
			}

		}else{
			error_log("echec d'authentification - l'utilisateur ".$_POST['login']." n'existe pas, ou n'a pas indique le bon mot de passe - hash : ".md5($_POST['password']));
			header('Location: ../error.php');
		}
	} else {
		error_log("echec d'authentification - longueur de parametres inadequats - login_len=".$login_len." - pwd_len=".$pwd_len);
		header('Location: ../error.php');
	}

} else {
	error_log("echec d'authentification - pas de login ou de mdp defini");
	header('Location: ../error.php');

}
?>