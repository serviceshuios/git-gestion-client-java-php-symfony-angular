# gestion-client-js

Projet de démonstration JavaScript

Principales technos mises en oeuvre :
  - AngularJS
  - Node.js
  - Bootstrap
  - Sequelize.js
  - Grunt
  - Bower
  - Yeoman

La base de ce projet a été générée via [yo angular generator](https://github.com/yeoman/generator-angular)
version 0.11.1, mais pourra connaitre quelques déviations par rapport à cette base au fur et à mesure de son avancement.

## pré-requis

Installez globalement les outils suivants :

  - nodejs / npm
  - bower
  - grunt

Cf http://noelmace.com/blog/js-un-environnement-de-developpement-ideal-pour-angularjs/


## installation

Après avoir récupéré les sources du projet via :

`git clone https://github.com/noelmace/GestionClient_JS.git`

rendez vous dans le dossier GestionClient_JS et effectuez l'installation des dépendances via un simple :

`npm install`

Une post-installation, comprenant l'installation des dépendances bower ainsi que l'initialisation de la base sqlite, sera également automatiquement lancée.

## Lancement

Lancer `grunt serve` ou `npm start` pour démarrer le serveur express.js.

## Testing

Lancer `grunt test` ou `npm test` pour executer les tests unitaires avec Karma.
