var express = require('express');
var app = express();

require('./express.conf')(app, express);

app.get('/', function (req, res) {
  res.sendfile('../index.html', {root: app.settings.views});
});

require('./routes/client')(app);
require('./routes/bdc')(app);

module.exports = app;
