'use strict';

/**
 * @ngdoc function
 * @name gestionClientJsApp.controller:ClientsCtrl
 * @description
 * # ClientsCtrl
 * Controller of the gestionClientJsApp
 */
angular.module('gestionClientJsApp')
  .controller('ClientsCtrl', function($scope, Client) {

    $scope.reloadClients = function(){
      $scope.clients = [new Client({firstname: '', lastname: ''})];

      Client.query({}, function success(rslt) {
        $scope.clients.unshift.apply($scope.clients, rslt);
      });
    };

    $scope.reloadClients();

    console.log($scope.clients);

    // éléments de la barre de recherche
    $scope.searchElmts = [{
      name: 'id',
      prop: 'id',
      inputType: 'number'
    }, {
      name: 'nom',
      prop: 'lastname',
    }, {
      name: 'prenom',
      prop: 'firstname'
    }];

    // valeurs par défaut pour la barre de recherche
    $scope.search = {
      prop: 'lastname',
      pattern: ''
    };

    $scope.setFilterObj = function(prop, pattern) {
      $scope.filterObj = {};
      $scope.filterObj[prop] = pattern;
    };

    // initialisation du paramètre de filtre
    $scope.setFilterObj($scope.search.prop, $scope.search.pattern);

    /*
    met à jour le paramètre de filtre en fonction des entrées dans la barre de recherche
    le troisième argument est à true pour effectuer une comparaison par valeur (equals) plutôt que par référence
    /!\ mauvaise solution en terme de performance
    privilégier ng-watch quand possible
    */
    $scope.$watch('search', function() {
      $scope.setFilterObj($scope.search.prop, $scope.search.pattern);
    }, true);

    // $scope.addClient = function (client){
    //   console.log('sent');
    //   $scope.clients.push(client);
    // };

    // solution temporaire
    // TODO : simplement ajouter un new Client à clients
    $scope.notifyNewClient = $scope.reloadClients;
  });
