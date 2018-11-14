'use strict';

/*

Pour apprendre :

  - solution pour simuler une réponse à un $http.get sur une certaine adresse
    (non adapté aux ressources)
      httpBackend.expectGET('/clients').respond(clientsMock);
      httpBackend.flush();

  - solution pour faire un stub rapide de service (avec angular-mock)

      ClientResStub = jasmine.createSpyObj('clientStub', ['query']);
      ClientResStub.get.and.addCallFake(function(params){
        return clientsMock;
      });
      $provide.factory('Client', function() {
        return ClientResStub;
      });

*/

describe('Controller: ClientsCtrl', function() {

  var ClientsCtrl, scope, mockDb;

  // load the controller's module
  beforeEach(module('gestionClientJsApp'));

  // load the mock module
  beforeEach(module('mock'));

  // Initialize the controller and a mock scope
  beforeEach(inject(function($controller, $rootScope, _Client_, _MockDb_) {
    mockDb = _MockDb_;
    scope = $rootScope.$new();

    ClientsCtrl = $controller('ClientsCtrl', {
      $scope: scope,
      Client: _Client_
    });
  }));

  it('should have a list of clients', function() {

    expect(scope.clients).toEqual(mockDb.clients);
  });
});
