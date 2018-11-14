'use strict';

/**
 * @ngdoc directive
 * @name gestionClientJsApp.directive:bdcCard
 * @description
 * # bdcCard
 */
angular.module('gestionClientJsApp')
  .directive('bdcCard', function () {
    return {
      replace: true,
      templateUrl: 'templates/directives/bdc-card.html',
      restrict: 'E',
      scope: {
        bdc: '='
      }
    };
  });
