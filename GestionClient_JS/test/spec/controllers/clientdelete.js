'use strict';

describe('Controller: ClientdeleteCtrl', function () {

  // load the controller's module
  beforeEach(module('gestionClientJsApp'));

  beforeEach(module('mock'));

  var ClientdeleteCtrl,
    scope, httpBackend, MockDb, Client;

    var clientMock = {
      id : 1,
      firstname: 'testFirst',
      lastname: 'testLast'
    }

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, _Client_, _MockDb_) {
    scope = $rootScope.$new();
    MockDb = _MockDb_;
    Client = _Client_;
    ClientdeleteCtrl = $controller('ClientDeleteCtrl', {
      $scope: scope,
      $routeParams: {id : 1},
      Client: Client
    });
  }));

  it('should get a client', inject(function (Client) {
    expect(scope.client).toBe(MockDb.clients[1]);
  }));

  it('should delete a client and redirect to /clients when deleteClient() is called', inject(function (Client, $location) {
    spyOn(Client, 'remove');
    scope.deleteClient();
    expect(Client.remove).toHaveBeenCalledWith({id: 1});
    expect($location.path()).toEqual('/clients');
  }));
});
