<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/table.css">

        <title>GestionClient PHP - Clients</title>
    </head>

    <body>

	    <?php
	    
		include_once ("includes/header.php");
		include_once("snippets/auth_redirect.php");
		require_once("classes/DAO/ClientDAO.class.php");
		
	    ?>
	        
	    <h1>Liste des clients</h1>
	
	
	    <?php
	
		include ('snippets/dbConnect.php');
	
		$login = getLogin();
		
		?>
			
		<table class="sql_table">
			<tr><th>login</th><th>nom</th><th>prenom</th><th>commentaire</th><th>icone</th><th>admin</th></tr>
	
			<?php
			$clientDAO = new ClientDAO();
			if(isAdmin()){
				$client_tab = $clientDAO -> getAll();
			} else {
				$client_tab = array($clientDAO -> getByLogin($login));
			}
	
			foreach ($client_tab as $client) : ?>
				<tr>
					<td><?php echo $client -> getLogin(); ?></td>
					<td><?php echo $client -> getNom() ; ?></td>
					<td><?php echo $client -> getPrenom(); ?></td>
					<td><?php echo $client -> getCommentaire(); ?></td>
					<td>
						<img class="icon" src="<?php if($client -> getIcon() != NULL && file_exists('uploads/'.$client -> getIcon())) { echo 'uploads/'.$client -> getIcon() ; } else { echo 'uploads/default.jpg' ;} ?>" />
					</td>
					<td>
						<img class="yesno" src="<?php if($client -> isAdmin()) { echo 'images/yes.png' ; } else { echo 'images/no.png' ;} ?>" />
					</td>
				</tr>
			<?php endforeach; ?>
	    </table>
	
	    <?php include_once ("includes/footer.php"); ?>
    </body>
</html>