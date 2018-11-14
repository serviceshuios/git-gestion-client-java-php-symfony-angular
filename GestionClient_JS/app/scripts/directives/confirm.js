'use strict';

/**
 * @ngdoc directive
 * @name gestionClientJsApp.directive:confirm
 * @description
 * # confirm
 */
angular.module('gestionClientJsApp')
  .directive('confirm', function () {
    return {
      templateUrl: 'templates/directives/confirm.html',
      restrict: 'E',
      replace: 'true',
      scope: {
        yes: '&',
        no: '&'
      }
    };
  });
