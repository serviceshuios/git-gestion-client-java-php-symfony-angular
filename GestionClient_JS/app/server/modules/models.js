var sequelize = require('sequelize');

var sequelize = new sequelize('gestionClientJs', 'username', 'password', {
  dialect: 'sqlite',
  port: 3306,
  storage: './database.sqlite'
});

var Client = sequelize.import(__dirname + '/models/client');
var Bdc = sequelize.import(__dirname + '/models/bdc');

Client.hasMany(Bdc);
Bdc.belongsTo(Client);

module.exports = {
  Client : Client,
  Bdc: Bdc,
  sequelize: sequelize
}
