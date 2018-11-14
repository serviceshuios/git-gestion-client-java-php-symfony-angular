'use strict';

angular.module('mock').factory('MockDb', function MockDbFactory() {
  return {
    clients: [{
      id: 0,
      firstname: 'testFirst',
      lastname: 'testLast'
    }, {
      id: 1,
      firstname: 'testFirst2',
      lastname: 'testLast2'
    }, {
      id: 2,
      firstname: 'testFirst3',
      lastname: 'testLast3'
    }]
  };
});
