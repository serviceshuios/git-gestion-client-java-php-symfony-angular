<?php
try {
	$bdd = new PDO('mysql:host=localhost;dbname=gestion_client_php', 'php_test', 'php');
} catch (Exception $e) {
	die('Erreur : ' . $e -> getMessage());
}
?>