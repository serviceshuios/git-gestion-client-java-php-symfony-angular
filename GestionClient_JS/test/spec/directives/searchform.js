'use strict';

describe('Directive: searchForm', function() {

  // load the directive's module
  beforeEach(module('gestionClientJsApp'));
  beforeEach(module('app/templates/directives/search-form.html'));


  var element, scope, template, search;

  var elements = [{
    name: 'id',
    prop: 'id',
    inputType: 'number'
  }, {
    name: 'nom',
    prop: 'lastname'
  }, {
    name: 'prenom',
    prop: 'firstname'
  }];

  search = { prop: 'lastname', pattern: '' };

  beforeEach(inject(function($rootScope, $templateCache) {
    scope = $rootScope.$new();
    scope.elements = elements;
    scope.search = search;
    // rend le template accessible via la bonne url (templateUrl)
    template = $templateCache.get('app/templates/directives/search-form.html');
    $templateCache.put('templates/directives/search-form.html', template);
  }));

  it('should get the elements to the isolated scope', inject(function($compile) {
    element = angular.element('<search-form elements="elements" selected-prop="search.prop" pattern="search.pattern"></search-form>');
    element = $compile(element)(scope);
    scope.$digest();
    expect(element.scope().elements).toBe(scope.elements);
  }));

  it('should be replaced by a form.search-form', inject(function($compile) {
    element = angular.element('<div><search-form elements="elements" selected-prop="search.prop" pattern="search.pattern"></search-form></div>');
    element = $compile(element)(scope);
    scope.$digest();
    expect(element.children('form').length).toBe(1);
    expect(element.children('form').hasClass('search-form')).toBeTruthy();
  }));

  it('should change input#search-pattern type when new propertie is selected', inject(function($compile) {
    element = angular.element('<search-form elements="elements" selected-prop="search.prop" pattern="search.pattern"></search-form>');
    element = $compile(element)(scope);
    scope.$digest();
    var selectElmt = element.find('select').eq(0);
    selectElmt.val('string:id').trigger('change');
    var searchPatternInput = element.find('input').eq(0);
    // expect(element.isolateScope().inputType).toEqual('number');
    expect(searchPatternInput.attr('type')).toEqual('number');
  }));
});
