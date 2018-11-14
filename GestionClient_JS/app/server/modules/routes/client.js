var models = require('../models');
var Client = models.Client;
var Bdc = models.Bdc;

var clientSafeParams = ['id', 'firstname', 'lastname'];
var bdcSafeParames = ['id', 'designation', 'description', 'date', 'delay'];

module.exports = function(app) {
  // findAll
  app.get('/clients', function(req, res) {
    models.sequelize.sync().then(function() {
      Client.findAll({
          attributes: clientSafeParams
        })
        .then(function(clients) {
          res.json(clients);
        })
    });
  });

  // create
  app.post('/clients', function(req, res) {
    models.sequelize.sync().then(function() {
      Client.create({
        firstname: req.param('firstname'),
        lastname: req.param('lastname')
      }).then(function(client) {
        res.json(client);
      })
    });
  });

  // update
  app.put('/clients', function(req, res) {
    var updateParams = {};

    models.sequelize.sync().then(function() {
      Client.find({
          where: {
            id: req.param('id')
          },
          attributes: clientSafeParams
        })
        .then(function(client) {

          // TODO : auth

          // filtrage des paramètres
          clientSafeParams.forEach(function(param) {
            updateParams[param] = req.param(param);
          });

          client.updateAttributes(updateParams).then(function() {
            res.json(client);
          });
        });
    });
  });

  // findById
  app.get('/clients/:id', function(req, res) {
    var clientId = parseInt(req.params.id, 10);

    if (!clientId) {
      res.json({});
      return;
    }

    models.sequelize.sync().then(function() {
      Client.find({
        where: {
          id: clientId,
        },
        attributes: clientSafeParams

      }).then(function(client) {
        res.json(client);
      });
    });
  });

  app.delete('/clients/:id', function(req, res) {
    var clientId = parseInt(req.params.id, 10);

    if (!clientId) {
      res.json({});
      return;
    }

    models.sequelize.sync().then(function() {
      Client.find({
        where: {
          id: clientId,
        },
        attributes: clientSafeParams
      }).then(function(client) {
        client.destroy().then(function(client) {
          res.json(client);
        });
      });
    });
  });
}
