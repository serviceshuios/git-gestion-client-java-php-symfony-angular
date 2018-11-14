<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="css/default.css" />
        <link rel="stylesheet" href="css/table.css" />
        <link rel="stylesheet" href="css/form.css" />
        <title>GestionClient PHP - Clients</title>
    </head>

    <body>

        <?php
		include_once ("includes/header.php");
		include_once("snippets/auth_redirect.php")
        ?>

        <?php
        $login = getLogin();
        $isAdmin = isAdmin();

        include('snippets/dbConnect.php');

        $st = $bdd->prepare('SELECT login, nom, prenom, commentaire FROM Client WHERE login = :login');
        $st->bindValue(':login', $_SESSION['login']);
        $st->execute();

        $client = $st->fetch();

        ?>
        
        <?php if(isset($_GET['error'])) : ?>
    	<p class="errorMessages center"><?php echo htmlspecialchars($_GET['error']); ?></p>
    	<?php endif; ?>
    	
        <div class="formWrapper">
            <form method="post" class="createForm" action="actions/createOrUpdate_Client.php" enctype="multipart/form-data">

                <?php if (!$isAdmin || $_GET['modif']) :
                ?>
                <input type="hidden" name="login" <?php echo 'value="' . $_SESSION['login'] . '" '; ?> />
                <?php endif; ?>
                <fieldset>
                    <legend>
                        Informations du client
                    </legend>
                    <table>

                        <?php if ($isAdmin && !(isset($_GET['modif']) || $_GET['modif'])) :
                        ?>
                        <tr>
                        <td class="label"><label for="login">Login</label></td><td class="input">
                        <input class="textfield"
                        type="text" name="login"
                        />
                        </td>
                        </tr>
                        <?php endif; ?>
                        <tr>
                            <td class="label"><label for="nom">Nom</label></td>
                            <td class="input">
                            <input class="textfield" type="text" name="nom"
                            <?php
							if (!$isAdmin || $_GET['modif']) { echo 'value="' . $client['nom'] . '" ';
							}
                            ?>
                            />
                            </td>
                        </tr>
                        <tr>
                        <td class="label"><label for="prenom">Prenom</label></td>
                        <td class="input">
                        <input class="textfield" type="text" name="prenom"
                        <?php
						if (!$isAdmin || $_GET['modif']) { echo 'value="' . $client['prenom'] . '" ';
						}
                        ?>

                        />
                        </td>
                        </tr>
                        <?php if($isAdmin && !(isset($_GET['modif']) || $_GET['modif'])) :
                        ?>
                        <tr>
                        <td class="label"><label for="password">Mot de passe</label></td>
                        <td class="input">
                        <input class="textfield" type="password" name="password" />
                        </td>
                        </tr>
                        <tr>
                        <td class="label"><label for="password2">Confirmer le mot de passe</label></td>
                        <td class="input">
                        <input class="textfield" type="password" name="password2" />
                        </td>
                        </tr>
                        <tr>
                        <td class="label"><label for="icon">Photo de profil</label></td>
                        <td class="input">
                        <input type="file" name="icon" />
                        </td>
                        </tr>
                        <?php endif; ?>
                        <tr>
                        <td class="label"><label for="commentaire">Commentaire</label></td>
                        <td class="input">
                        <textarea name="commentaire"><?php if (!$isAdmin || $_GET['modif']) { echo $client['commentaire']; } ?></textarea>
                        </td>
                        </tr>
                    </table>
                </fieldset>

                <?php if($isAdmin && !(isset($_GET['modif']) || $_GET['modif'])) :
                ?>

                <p>
                <input type="checkbox" name="admin" />
                <label for="admin">Faire de cet utilisateur un administrateur du site.</label></td>
                </p>

                <?php endif; ?>
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