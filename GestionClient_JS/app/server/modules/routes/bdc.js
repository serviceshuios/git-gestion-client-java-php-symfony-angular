var models = require('../models');
var Bdc = models.Bdc;
var Bdc = models.Bdc;

var clientSafeParams = ['id', 'firstname', 'lastname'];
var clientIdOnlyParams = ['id'];
var bdcSafeParams = ['id', 'designation', 'description', 'date', 'delay', 'clientId'];

module.exports = function(app) {
  // findAll
  app.get('/bdcs', function(req, res) {
    models.sequelize.sync().then(function() {
      Bdc.findAll({
          attributes: bdcSafeParams/*,
          include: [{
            model: Client,
            attributes: clientIdOnlyParams
          }]*/
        })
        .then(function(bdcs) {
          res.json(bdcs);
        })
    });
  });

  // create
  app.post('/bdcs', function(req, res) {
    models.sequelize.sync().then(function() {
      Bdc.create({
        designation: req.param('designation'),
        description: req.param('description'),
        date: req.param('date'),
        delay: req.param('delay')
      }).then(function(bdcs) {
        req.json(bdcs);
      })
    });
  });

  // update
  app.put('/bdcs', function(req, res) {
    var updateParams = {};

    models.sequelize.sync().then(function() {
      Bdc.find({
        where: {
          id: req.params('id'),
          attributes: bdcSafeParams
        }
        .then(function(bdc) {

          // TODO : auth

          // filtrage des paramètres
          clientSafeParams.forEach(function(param) {
            updateParams[param] = req.param(param);
          });

          client.updateAttributes(updateParams).success(function() {
            res.json(client);
          });
        })
      });
    });
  });

  // findById
  app.get('/clients/:id', function(req, res) {
    var bdcId = parseInt(req.params.id, 10);

    if (!bdcId) {
      res.json({});
      return;
    }

    models.sequelize.sync().then(function() {
      Bdc.find({
        where: {
          id: bdcId,
          attributes: bdcSafeParams
        }
      }).then(function(bdc) {
        res.json(client);
      });
    });
  });
}
