<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="css/default.css" />
        <link rel="stylesheet" href="css/table.css" />
        <link rel="stylesheet" href="css/form.css" />
        <title>GestionClient PHP - Bon de commande</title>
    </head>

    <body>

        <?php
		include_once ("includes/header.php");
		include_once ("snippets/auth_redirect.php");
		$isAdmin = isAdmin();
        ?>
        
        <?php if(isset($_GET['error'])) : ?>
    	<p class="errorMessages center"><?php echo htmlspecialchars($_GET['error']); ?></p>
    	<?php endif; ?>

        <div class="formWrapper">
            <form method="post" class="createForm" action="actions/createOrUpdate_Bdc.php">
				<?php if(!$isAdmin) : ?>
					<input class="textfield" type="text" name="client_login" />
				<?php endif; ?>
                <fieldset>
                    <legend>
                        Informations du bon de commande
                    </legend>
                    <table>
						<?php if($isAdmin) : ?>
                        <tr>
                            <td class="label"><label for="client_login">Login du client</label></td><td class="input">
                            <input class="textfield" type="text" name="client_login" />
                            </td>
                        </tr>
                        <?php endif; ?>
                        <tr>
                            <td class="label"><label for="commentaire">Commentaire</label></td>
                            <td class="input"><textarea name="commentaire"></textarea></td>
                        </tr>
                        <tr>
                            <td class="label"><label for="montant">Montant (en euros)</label></td>
                            <td class="input">
                            <input class="textfield" type="text" name="montant" />
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><label for="delais">Delais (en jours)</label></td>
                            <td class="input">
                            <input class="textfield" type="text" name="delais" />
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
        
        <?php
		include_once ("includes/footer.php");
        ?>
    </body>
</html>