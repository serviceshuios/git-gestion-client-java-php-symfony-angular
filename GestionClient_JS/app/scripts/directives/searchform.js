'use strict';

/**
 * @ngdoc directive
 * @name gestionClientJsApp.directive:searchForm
 * @description
 * # searchForm
 */
angular.module('gestionClientJsApp')
  .directive('searchForm', function() {
    return {
      replace: true,
      templateUrl: 'templates/directives/search-form.html',
      restrict: 'E',
      scope: {
        elements: '=',
        selectedProp: '=',
        pattern: '='
      },
      controller: function($scope) {
          $scope.inputType = 'text';
      },
      link: function($scope){
        // ngChange aurait également pu être utilisé
        $scope.$watch('selectedProp', function() {
          var selectedElement = $scope.elements.filter(function (item) {
            return item.prop === $scope.selectedProp;
          })[0];
          $scope.inputType = (typeof selectedElement.inputType === 'undefined') ? 'text' : selectedElement.inputType;
        });
      }
    };
  });
