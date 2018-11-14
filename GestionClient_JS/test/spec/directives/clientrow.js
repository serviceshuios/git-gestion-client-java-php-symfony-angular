'use strict';

describe('Directive: clientRow', function () {

  var element,
    scope, template;

  beforeEach(module('gestionClientJsApp'));
  beforeEach(module('app/templates/directives/client-row.html'));



  beforeEach(inject(function ($templateCache, $compile, $rootScope) {
    scope = $rootScope.$new();
    // rend le template accessible via la bonne url (templateUrl)
    template = $templateCache.get('app/templates/directives/client-row.html');
		$templateCache.put('templates/directives/client-row.html',template);
  }));

  it('should get the client to the isolated scope', inject(function ($compile) {
    element = angular.element('<tr client="client" client-row></tr>');
    scope.client = {
      id : 0,
      firstname : 'pierre',
      lastname : 'jean'
    };
    element = $compile(element)(scope);
    expect(element.scope().client).toBe(scope.client);
  }));

  it('should have 4 columns inside', inject(function ($compile) {
    element = angular.element('<tr client="client" client-row></tr>');
    scope.client = {
      id : 0,
      firstname : 'pierre',
      lastname : 'jean'
    };
    element = $compile(element)(scope);
    scope.$digest();
    expect(element.find('td').length).toEqual(4);
  }));
});
