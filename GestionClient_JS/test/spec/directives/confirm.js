'use strict';

describe('Directive: confirm', function() {

  // load the directive's module
  beforeEach(module('gestionClientJsApp'));
  beforeEach(module('app/templates/directives/confirm.html'));


  var element,
    scope, template;

  var yes = function(){
    scope.confirm = true;
  };

  var no = function(){
    scope.confirm = false;
  }

  beforeEach(inject(function($rootScope, $templateCache) {
    scope = $rootScope.$new();

    template = $templateCache.get('app/templates/directives/confirm.html');
    $templateCache.put('templates/directives/confirm.html', template);
  }));

  it('should be replaced by a form with two button inputs', inject(function($compile) {
    element = angular.element('<div><confirm yes="yes()" no="no()"></confirm></div>');
    element = $compile(element)(scope);
    scope.$digest();
    expect(element.children('form').length).toBe(1);
    expect(element.find('input').length).toBe(2);
    expect(element.find('input').eq(0).attr('type')).toEqual('button');
    expect(element.find('input').eq(1).attr('type')).toEqual('button');
  }));
});
