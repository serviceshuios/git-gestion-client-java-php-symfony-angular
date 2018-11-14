'use strict';

/**
 * @ngdoc function
 * @name gestionClientJsApp.controller:ClientdeleteCtrl
 * @description
 * # ClientdeleteCtrl
 * Controller of the gestionClientJsApp
 */
angular.module('gestionClientJsApp')
  .controller('ClientDeleteCtrl', function ($scope, Client, $routeParams, $location) {

    $scope.client = Client.get({id : $routeParams.id});
    $scope.confirm = false;

    $scope.deleteClient = function () {
      Client.remove({id : $scope.client.id});
      $location.path('/clients');
    };

    $scope.cancel = function () {
      $location.path('/clients');
    };
  });
