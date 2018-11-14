<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/wall.css" />

        <title>GestionClient PHP - Mur</title>
    </head>

    <body>

        <?php
		include_once ("includes/header.php");
        ?>
    
    	<h2>Messages</h2>
    
    	<div class="wallPosts">
    		<?php include 'snippets/wallPosts.php'; ?>
    	</div>
        <div class="wallForm">
            <h2>Poster un message</h2>
            <?php if (getLogin()) :
            ?>
            <form action="actions/newWallMessage.php" method="post" enctype="multipart/form-data" >
                <label for="message">Votre message :</label></br>
                <textarea name="message" class="message"></textarea>
                <input type="submit" name="submit" value="Envoyer !"/>
                <input type="reset" name="reset" value="Annuler" />
            </form>
            <?php else : ?>
            <p>
                Vous devez être connecté pour poster un message.
            </p>
            <?php endif; ?>
        </div>

        <?php
		include_once ("includes/footer.php");
        ?>
    </body>
</html>