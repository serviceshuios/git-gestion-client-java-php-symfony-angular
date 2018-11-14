module.exports = function (sequelize, DataTypes) {
  return sequelize.define('Bdc', {
    id: {
      type: DataTypes.INTEGER.UNSIGNED,
      primaryKey: true,
      autoIncrement: true
    },
    designation: DataTypes.STRING,
    description: DataTypes.TEXT,
    date: DataTypes.DATEONLY,
    delay: DataTypes.INTEGER.UNSIGNED
  });
}
