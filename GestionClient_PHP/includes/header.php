<?php include_once('snippets/auth.php') ?>

<header>

	<div id="title">
		<h1>GestionClient - PHP</h1>
		<a class="connectLkn" href="connect.php">
		<?php $login = getLogin() ; echo ($login) ? "Connecté en tant que : " . $login : "Se connecter"; ?>
		</a> 
	</div>

	<nav>
		<ul>
			<li><a href="index.php">Acceuil</a></li>
			<li><a href="#">Clients</a>
				<ul>
					<li><a href="form_clients.php">Créer</a></li>
					<li><a href="list_clients.php">Lister</a></li>
				</ul></li>
			<li><a href="#">Bons de commandes</a>
				<ul>
					<li><a href="form_bdc.php">Créer</a></li>
					<li><a href="list_bdc.php">Lister</a></li>
				</ul></li>
			<li>
				<a href="wall.php">Mur</a>
			</li>
		</ul>
	</nav>
</header>

<div id="content">
