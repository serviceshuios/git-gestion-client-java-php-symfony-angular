'use strict';

/**
 * @ngdoc directive
 * @name gestionClientJsApp.directive:ClientColumns
 * @description
 * # ClientColumns
 */
angular.module('gestionClientJsApp')
  .directive('clientRow', function() {
    return {
      replace: true,
      restrict: 'A',
      templateUrl: 'templates/directives/client-row.html',
      scope: {
        client: '=',
        errors: '='
      },
      controller: function($scope, Client) {
        $scope.edit = $scope.newClient = (typeof $scope.client.id === 'undefined');

        $scope.save = function(client) {
          if ($scope.newClient) {
            client.$save().then(function(client){
              $scope.$parent.notifyNewClient();
            }, function(clientData) {
              $scope.create.errors = [clientData.data.error];
            }).finally(function() {
              $scope.create.saving = false;
            });
            $scope.newClient = $scope.edit = false;
          } else {
            client.$update().catch(function(clientData) {
              $scope.errors = [clientData.data.error];
            }).finally(function() {
              $scope.edit = false;
            });
          }
        };

        $scope.beginEdition = function() {
          $scope.edit = true;
        }
      }
    };
  });
