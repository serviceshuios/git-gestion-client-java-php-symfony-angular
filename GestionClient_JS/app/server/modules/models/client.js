module.exports = function (sequelize, DataTypes) {
  return sequelize.define('Client', {
    id: {
      type: DataTypes.INTEGER.UNSIGNED,
      primaryKey: true,
      autoIncrement: true
    },
    firstname: DataTypes.STRING,
    lastname: DataTypes.STRING
  });
}
