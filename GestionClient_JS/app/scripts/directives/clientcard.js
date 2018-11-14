'use strict';

/**
 * @ngdoc directive
 * @name gestionClientJsApp.directive:clientCard
 * @description
 * # clientCard
 */
angular.module('gestionClientJsApp')
  .directive('clientCard', function () {
    return {
      templateUrl: 'templates/directives/client-card.html',
      restrict: 'E',
      replace: 'true',
      scope: {
        client : '='
      }
    };
  });
