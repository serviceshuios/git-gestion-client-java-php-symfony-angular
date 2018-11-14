'use strict';

/**
 * @ngdoc function
 * @name gestionClientJsApp.controller:MainCtrl
 * @description controller for the main view
 * # MainCtrl
 * Controller of the gestionClientJsApp
 */
angular.module('gestionClientJsApp')
  .controller('MainCtrl', function ($scope) {
      $scope.test = [
        {
          test: 'toto'
        }
      ];
  });
