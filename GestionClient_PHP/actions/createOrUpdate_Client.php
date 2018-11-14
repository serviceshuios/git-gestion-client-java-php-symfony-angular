<?php

include_once '../snippets/auth.php';
require '../classes/DAO/ClientDAO.class.php';

function save_file() {
	if (isset($_FILES['icon']) && $_FILES['icon']['error'] == 0) {

		// types mime valides
		$validImages = array("image/gif", "image/jpeg", "image/jpg", "image/pjpeg", "image/x-png", "image/png");

		// test du type mime et de la taille du fichier exporté
		if (in_array($_FILES['icon']['type'], $validImages) && $_FILES['icon']['size'] < 3000000) {

		$icon_name = uniqid().pathinfo($_FILES['icon']['name'])['extension'];

			// sauvegarde du fichier d'icone
			if (move_uploaded_file($_FILES['icon']['tmp_name'], '../uploads/' . $icon_name)) {
				return $icon_name;
			} else {
				error_log("Fichier " . $_FILES['icon']['name'] . " non charge - " . $_FILES['icon']['type'] . ", " . $_FILES['icon']['size'] . "oc");
			}
		}

	} else {
		error_log("Fichier " . $_FILES['icon']['name'] . " non charge - " . $_FILES['icon']['type'] . ", " . $_FILES['icon']['size'] . "oc");
	}

	return null;
}

try {
	$client = new Client(array_intersect_key($_POST, array('login' => true, 'nom' => true, 'prenom' => true, 'admin' => true, 'commentaire' => true)));

	$client -> setIcon(save_file());
	error_log(print_r($client -> toArray(), true));

	$clientDAO = new ClientDAO();

	if (isset($_POST['password'], $_POST['password2']) && isAdmin()) {
		if ($_POST['password'] == $_POST['password2']) {
			$client -> setClearPassword($_POST['password']);
		}

		if ($clientDAO -> createOrUpdate($client)) {
			header('Location: ../list_clients.php');
			die();
		} else {
			header('Location: ../form_clients.php?error=le client n\'a pas pu être créé pour des raisons inconnues');
			die();
		}
	} else if (getLogin() == $_POST['login']) {
		if ($clientDAO -> update($client)) {
			header('Location: ../list_clients.php');
			die();
		} else {
			header('Location: ../form_clients.php?error=le client n\'a pas pu être créé pour des raisons inconnues');
			die();
		}
	} else {
		header('Location: ../form_clients.php?error=cette opération vous est interdite');
	}

} catch (Exception $e) {
	header('Location: ../form_clients.php?error=' . $e -> getMessage());
	die();
}
?>