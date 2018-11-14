'use strict';

angular.module('gestionClientJsApp').factory('Client', function ClientFactory($resource) {
  return $resource('/clients/:id', {}, {
    update: {
      method: 'PUT'
    }
  });
});
