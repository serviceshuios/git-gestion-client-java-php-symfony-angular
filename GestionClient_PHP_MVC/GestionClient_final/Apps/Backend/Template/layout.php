<?php $GLOBALS['logger']->debug('chargement du layout - content = '.$content); ?>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="/css/default.css">
        <link rel="stylesheet" href="/css/wall.css">
        <link rel="stylesheet" href="/css/table.css">
        <link rel="stylesheet" href="/css/form.css">

        <title>GestionClient PHP - backend - <?php if(isset($title)) { echo ' - '.$title;} ?></title>
    </head>

    <body>

        <!-- en-tête -->

        <header>

            <div id="title">
                <h1>GestionClient PHP - backend</h1>
                <a class="connectLkn" href="/">
					frontend
				</a>
            </div>

            <nav>
                <ul>
                    <li>
                        <a href="/index.php">Acceuil</a>
                    </li>
                    <li>
                        <a href="#">Comptes clients</a>
                        <ul>
                            <li>
                                <a href="clients">Consulter</a>
                            </li>
                            <li>
                                <a href="#">Modifier</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">Commandes</a>
                        <ul>
                            <li>
                                <a href="clients">Lister</a>
                            </li>
                            <li>
                                <a href="#">Rechercher</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">Mur</a>
                    </li>
                </ul>
            </nav>
        </header>

            <!-- Le corps -->

        <div id="content">

            <?= $content ?> 

        </div>

            <!-- Le pied de page -->

        <footer>
            <p>
                L'ensemble des ressources crées par Noël Macé, ce site compris, sont, sauf mention contraire, distribuées sous licence <a rel="license"
                href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img
                alt="CC BY-NC-SA" style="border-width: 0; vertical-align: middle;"
                src="http://i.creativecommons.org/l/by-nc-sa/4.0/80x15.png" /></a>.
            </p>
        </footer>
    </body>
</html>