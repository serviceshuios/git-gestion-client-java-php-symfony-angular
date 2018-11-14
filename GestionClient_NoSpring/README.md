GestionClient_NoSpring
======================

Projet de démonstration pour une première approche de Hibernate en Java.

Projet réalisé sous Eclipse.

# Installation

Ce projet constituant une introduction au développement Java, sa structure et ses dépendances est le plus sommaire possible.
De fait, la gestion des dépendances se fait sans aucun utilitaire hormis Eclipse, raison pour laquelle toute configuration Eclipse y est incluse.

## Dépendances 

Pour disposer des dépendances requises, vous devez donc :

+ récupérer le projet ressource Eclipse Libs sur noelmace.com/cours/, ainsi que le fichier libs.userlibraries associé

+ extraire le projet Libs dans votre Workspace, et l'importer

    + File > Import > Existing project into workspace
    + sélectionner le dossier Libs
    + interrompre le chargement en cliquant sur le carré rouge
    + desselectionner "copier les fichiers"
    + selectionner le projet Libs
    + valider

+ importer les librairies utilisateur grâce au fichier téléchargé

    + Window > Preferences > Java > Build path > User libraries
    + sélectionner le fichier libs.userlibraries
    + importer     

### Autres dépendances

Quelques erreurs de dépendances peuvent persister après ces opérations, notamment :

+ une version de Java Runtime incompatible (la version utilisée actuellement est la version Oracle 8)
+ un targeted runtime inexistant
    + pour résoudre ce problème, rdv sur Properties > Targeted Runtime, et selectionner votre serveur Tomcat 7

## Import du projet

Pour importer le projet en lui même :

+ File > Import > Existing project into workspace
+ Selectionner l'archive (zip) ou le dossier
+ Les 8 projets apparaissent à côté de cases à cocher
+ Cocher tous les projets
+ Finish
