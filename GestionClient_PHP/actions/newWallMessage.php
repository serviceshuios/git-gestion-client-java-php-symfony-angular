<?php

session_start();
include ('../snippets/auth.php');

$login = getLogin();

$filename = '../snippets/wallPosts.php';

if ($login && $_POST['message']) {
	
	$data = file_get_contents($filename);
	
	$data .= '<div class="msg"> <p class="msgContent">' . htmlspecialchars($_POST['message']) . '</p>' . '<span class="msgAuthor">';
	
	if (isset($_SESSION['icon'])) {
		$data .= '<img class="icon" src="uploads/' . $_SESSION['icon'] . '" /> ';
	} else {
		$data .= '<img class="icon" src="uploads/default.jpg" /> ';
	}
	
	$data .= $login . " - le " . date('d/m/y') . "</span> </div>";	
	
	if(file_put_contents($filename, $data) !== false){
		header('Location: ../wall.php');
	} else {
		header('Location: ../error.php');
	}
}


?>