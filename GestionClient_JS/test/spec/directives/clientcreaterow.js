'use strict';

describe('Directive: ClientCreateRow', function () {

  // load the directive's module
  beforeEach(module('gestionClientJsApp'));
  beforeEach(module('app/templates/directives/client-create-row.html'));

  var element, scope, template;

  beforeEach(inject(function ($rootScope, $templateCache, $compile) {
    scope = $rootScope.$new();

    template = $templateCache.get('app/templates/directives/client-create-row.html');
		$templateCache.put('templates/directives/client-create-row.html',template);

    element = angular.element('<tr client-create-row></tr>');
    element = $compile(element)(scope);
    scope.$digest();
  }));

  it('should have 4 columns', inject(function ($compile) {
    expect(element.find('td').length).toBe(4);
  }));
});
