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
	require_once("classes/DAO/BdcDAO.class.php");
	
	?>
    

    <?php

	$login = getLogin();
			?>
			<table class="sql_table">
				<tr><th>id</th><th>montant</th><th>commentaire</th><th>date</th><th>delais</th><th>client</th></tr>
			<?php
			$bdcDao = new BdcDAO();
			if(isAdmin()){
				$bdc_tab = $bdcDao->getAll();
			} else {
				$bdc_tab = $bdcDao->getByClientLogin($login);
			}
						
			if(sizeof($bdc_tab) > 0){
				foreach ($bdc_tab as $bdc){
					?>
					<tr><td><?php echo $bdc -> getId();?></td><td><?php echo $bdc -> getMontant();?></td><td><?php echo $bdc -> getCommentaire();?></td><td><?php echo $bdc -> getDate();?></td><td><?php echo $bdc -> getDelais(); ?></td><td><?php echo $bdc -> getClient() -> getLogin(); ?></td></tr>
					<?php
					}
					} else {
 ?>
				<tr><td colspan="6"><span class="errorMessages">Vous ne disposez d'aucun bon de commande !</span></td></tr>
				<?php
				}

	?>
        
    <?php if($login) : ?>
    	 	</table>
    <?php endif; ?>
    <?php
	include_once ("includes/footer.php");
 ?>
    
    </body>
</html>