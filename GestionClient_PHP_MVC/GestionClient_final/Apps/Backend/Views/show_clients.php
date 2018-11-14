<!-- affichage de la liste complète des clients -->

<?php use \Tools\HtmlConverter; ?>

<h1>Liste des clients</h1>

<?=	HtmlConverter::clientsToTable($this->model) ?>