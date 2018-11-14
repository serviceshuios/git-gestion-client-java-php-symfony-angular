'use strict';

angular.module('mock').factory('Client', function ClientFactory(MockDb) {
  var clients = MockDb.clients;

  var clientResource = {};

  clientResource.get = function(params) {
    return clients[params.id];
  };

  clientResource.query = function(){
    return clients;
  };

  clientResource.delete = clientResource.get;

  clientResource.remove = clientResource.delete;

  return clientResource;
});
