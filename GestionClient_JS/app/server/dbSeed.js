var models = require("./modules/models");
var Client = models.Client;
var Bdc = models.Bdc;

models.sequelize.sync();

Client.findOrCreate({
  where: {
    firstname: 'pierre',
    lastname: 'jean'
  },
  default: {
    firstname: 'pierre',
    lastname: 'jean'
  }

}).then(function(client) {

  Bdc.findOrCreate({
    where: {
      designation: 'Awesome AngularJS App'
    },
    default: {
      designation: 'Awesome AngularJS App',
      description: 'A new and awesome app for our team.',
      clientId: client.id,
      date: '2015-01-10',
      delay: '365'
    }
  });

  return client;

}).then(function(client) {
  Bdc.findOrCreate({
    where: {
      designation: 'Fast & Furious'
    },
    default: {
      designation: 'Fast & Furious',
      description: 'We need you realise an Android App in 5 days.',
      clientId: client.id,
      date: '2014-12-10',
      delay: '5'
    }
  });
});

Client.findOrCreate({
  where: {
    firstname: 'michel',
    lastname: 'machin'
  },
  default: {
    firstname: 'michel',
    lastname: 'machin'
  }
}).then(function(client) {

  Bdc.findOrCreate({
    where: {
      designation: 'What\'s up',
    },
    default: {
      designation: 'What\'s up',
      description: 'It\'s just like yo!, but send "What\s up" instead',
      clientId: 0,
      date: '2014-11-24',
      delay: '850'
    }
  });
});

Client.findOrCreate({
  where: {
    firstname: 'antoine',
    lastname: 'baruchon'
  },
  default: {
    firstname: 'antoine',
    lastname: 'baruchon'
  }
});
