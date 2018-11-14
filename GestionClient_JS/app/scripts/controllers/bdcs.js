'use strict';

/**
 * @ngdoc function
 * @name gestionClientJsApp.controller:BdcsCtrl
 * @description
 * # BdcsCtrl
 * Controller of the gestionClientJsApp
 */
angular.module('gestionClientJsApp')
  .controller('BdcsCtrl', function ($scope) {

    // temp -- TODO : nodejs
    $scope.bdcs = [
      {
        id : 0,
        designation : 'Awesome AngularJS App',
        description : 'A new and awesome app for our team.',
        clientId : 2,
        date : '2015-01-10',
        delay : '365'
      },
      {
        id : 1,
        designation : 'Fast & Furious',
        description : 'We need you realise an Android App in 5 days.',
        clientId : 0,
        date : '2014-12-10',
        delay : '5'
      },
      {
        id : 2,
        designation : 'What\'s up',
        description : 'It\'s just like yo!, but send "What\s up" instead',
        clientId : 0,
        date : '2014-11-24',
        delay : '850'
      }
    ];

    // éléments de la barre de recherche
    $scope.searchElmts = [{
      name: 'id',
      prop: 'id',
      inputType: 'number'
    }, {
      name: 'designation',
      prop: 'designation'
    }, {
      name: 'description',
      prop: 'description'
    }, {
      name: 'id du client',
      prop: 'clientId',
      inputType: 'number'
    }, {
      name: 'date de création',
      prop: 'date',
      inputType: 'date'
    }
    /*
    TODO : le filtre filter n'est pas adéquat pour les nombres
    , {
      name: 'delais',
      prop: 'delay',
      inputType: 'number'
    }*/
    ];

    // valeurs par défaut pour la barre de recherche
    $scope.search = { prop: 'clientId', pattern: '' };

    $scope.setFilterObj = function(prop, pattern) {
      $scope.filterObj = {};
      $scope.filterObj[prop] = pattern;
    };

    // initialisation du paramètre de filtre
    $scope.setFilterObj($scope.search.prop, $scope.search.pattern);

    // met à jour le paramètre de filtre en fonction des entrées dans la barre de recherche
    // le troisième argument est à true pour effectuer une comparaison par valeur (equals) plutôt que par référence
    // FIXME : performances ?
    $scope.$watch('search', function() {
      $scope.setFilterObj($scope.search.prop, $scope.search.pattern);
    }, true);
  });
