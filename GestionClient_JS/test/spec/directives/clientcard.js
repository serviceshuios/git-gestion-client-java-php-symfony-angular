'use strict';

describe('Directive: clientCard', function() {

  // load the directive's module
  beforeEach(module('gestionClientJsApp'));
  beforeEach(module('app/templates/directives/client-card.html'));

  beforeEach(module('mock'));

  var element,
    scope, template;

  beforeEach(inject(function($rootScope, _MockDb_, $templateCache) {
    scope = $rootScope.$new();
    scope.client = _MockDb_.clients[1];

    template = $templateCache.get('app/templates/directives/client-card.html');
    $templateCache.put('templates/directives/client-card.html', template);
  }));

  it('should be replaced by a div.client-card', inject(function($compile) {
    element = angular.element('<div><client-card client="client"></client-card></div>');
    element = $compile(element)(scope);
    scope.$digest();
    expect(element.children('div').length).toBe(1);
    expect(element.children('div').hasClass('client-card')).toBeTruthy();
  }));
});
