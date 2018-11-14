<?php
	session_start();
	session_destroy();
	session_unset();
	setcookie('login', '', time() - 3600, '/', null, false, true);
	header('Location: ../index.php');
?>