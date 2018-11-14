'use strict';

/**
 * @ngdoc overview
 * @name gestionClientJsApp
 * @description
 * # gestionClientJsApp
 *
 * Main module of the application.
 * Configuration des routes
 */
angular
  .module('gestionClientJsApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'angular.filter'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/clients', {
        templateUrl: 'views/clients/index.html',
        controller: 'ClientsCtrl'
      })
      .when('/clients/:id/delete', {
        templateUrl: 'views/clients/delete.html',
        controller : 'ClientDeleteCtrl'
      })
      .when('/bdcs', {
        templateUrl: 'views/bdcs/index.html',
        controller: 'BdcsCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
