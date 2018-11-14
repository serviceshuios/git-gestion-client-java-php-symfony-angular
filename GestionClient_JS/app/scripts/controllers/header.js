'use strict';

/**
 * @ngdoc function
 * @name gestionClientJsApp.controller:HeaderCtrl
 * @description controller for the header of all pages
 * # HeaderCtrl
 * Controller of the gestionClientJsApp
 */
angular.module('gestionClientJsApp')
  .controller('HeaderController', function ($scope, $location) {
    // test sur une url est active ou non (pour le nav)
    $scope.isActive = function(viewLocation) {
      return viewLocation === $location.path();
    };
  });
