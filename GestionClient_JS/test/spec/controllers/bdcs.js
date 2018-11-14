'use strict';

describe('Controller: BdcsCtrl', function () {

  // load the controller's module
  beforeEach(module('gestionClientJsApp'));

  var BdcsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BdcsCtrl = $controller('BdcsCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of bdcs', function () {
    expect(scope.bdcs.lenth).not.toBe(0);
  });
});
