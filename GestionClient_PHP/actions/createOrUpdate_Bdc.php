<?php

include_once ('../snippets/auth.php');
require_once ('../classes/DAO/BdcDAO.class.php');
require_once ('../classes/DAO/ClientDAO.class.php');


$_POST['client_login'] = htmlspecialchars($_POST['client_login']);

if (isAdmin() || $_POST['client_login'] == getLogin()) {
	try {
		$clientDAO = new ClientDAO();
		$client = $clientDAO -> getByLogin($_POST['client_login']);
		if ($client) {
			$bdc = new Bdc(array_intersect_key($_POST, array('montant' => true, 'delais' => true, 'commentaire' => true)));
			$bdc -> setClient($client);

			$bdcDAO = new BdcDAO();
			if ($bdcDAO -> create($bdc)) {
				header('Location: ../list_bdc.php');
				die();
			} else {
				$msg = "le bon de commande n'a pu être créé pour une raison inconnue";
			}
		} else {
			$msg = "le client ".$_POST['client_login']." n'existe pas";
		}
	} catch (Exception $e) {
		error_log($e -> getMessage());
		$msg = $e->getMessage();
	}
} else {
	$msg = "il est interdit de modifier un bon de commande ne vous appartenant pas";
}

header('Location: ../form_bdc.php?error=' . $msg);
?>