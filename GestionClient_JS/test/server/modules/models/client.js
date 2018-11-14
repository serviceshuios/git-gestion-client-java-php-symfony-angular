'use strict';

var rewire = require('rewire');
describe("Client model", function() {

    var clientModel;

    beforeEach(function() {
        clientModel = rewire("../../../../app/server/modules/models/client");
    });

    it("should exist", function() {
        expect(clientModel).toBeDefined();
    });
});
