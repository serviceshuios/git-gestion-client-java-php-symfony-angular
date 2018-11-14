'use strict';

describe('Directive: bdcCard', function () {

  // load the directive's module
  beforeEach(module('gestionClientJsApp'));
  beforeEach(module('app/templates/directives/bdc-card.html'));


  var element,
    scope, template;

  var bdc = {
    id : 0,
    designation : 'Awesome AngularJS App',
    description : 'A new and awesome app for our team.',
    clientId : 2,
    date : '2015-01-10',
    delay : '365'
  };

  beforeEach(inject(function ($rootScope, $templateCache) {
    scope = $rootScope.$new();
    // rend le template accessible via la bonne url (templateUrl)
    template = $templateCache.get('app/templates/directives/bdc-card.html');
		$templateCache.put('templates/directives/bdc-card.html',template);

    scope.bdc = bdc;
  }));

  it('should get the bdc to the isolated scope', inject(function ($compile) {
    element = angular.element('<bdc-card bdc="bdc"></bdc-card>');
    element = $compile(element)(scope);
    expect(element.scope().bdc).toBe(scope.bdc);
  }));

  it('should be replaced by a div.bdc-card', inject(function ($compile) {
    element = angular.element('<div><bdc-card bdc="bdc"></bdc-card></div>');
    element = $compile(element)(scope);
    scope.$digest();
    expect(element.children('div').length).toBe(1);
    expect(element.children('div').hasClass('bdc-card')).toBeTruthy();
  }));
});
