<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
       	<link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/form.css" />

        <title>GestionClient PHP - Clients</title>
    </head>
 
    <body>
 
    <?php include_once("includes/header.php"); ?>
    
    <h1>Bonjour 
    	<?php 
    		$addr = (isset($_SERVER['REMOTE_ADDR']) AND $_SERVER['REMOTE_ADDR'] !== '') ? $_SERVER['REMOTE_ADDR'] : "utilisateur anonyme";
			if(isset($_SESSION['login'])){
				echo $_SESSION['login'];
			} else {
				echo $addr;
			}
		 ?> !</h1>
		 
		 <?php if($_GET['redirect']) : ?>
		 	<p class="errorMessages center">Vous devez vous authentifier pour acceder à cette page !</p>
		 <?php endif ; ?>
        
    <div class="formWrapper">
            <form method="post" class="createForm" action="actions/connect.php">
			<input type="hidden" name="redirect" value="<?php echo $_GET['redirect']; ?> "/>
                <fieldset>
                    <legend>
                        Authentification
                    </legend>
                    <table>
                        <tr>
                            <td class="label"><label for="login">Login</label></td><td class="input">
                            <input class="textfield" type="text" name="login" />
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><label for="password">Mot de passe</label></td>
                            <td class="input">
                            <input class="textfield" type="password" name="password" />
                            </td>
                        </tr>
                    </table>
                </fieldset>

                <p>
                    <input type="submit" value="Envoyer" />
                    <input type="reset" value="Annuler"/>
                </p>
            </form>
        </div>
    
    <?php if(isset($_SESSION['login'])) : ?>
    <p><a href="actions/disconnect.php">Se déconnecter</a></p>
    <p><a href="form_clients.php?modif=1">Modifier mon compte</a></p>
    <?php endif ; ?>
    
    <?php include_once("includes/footer.php"); ?>
    
    </body>
</html>