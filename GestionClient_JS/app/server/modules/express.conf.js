var passport = require('passport');
// var sassMiddleware = require('node-sass-middleware');
var path = require('path');
var cookieSession = require('cookie-session');
var bodyParser = require('body-parser');
var cookieParser = require('cookie-parser');
var csrf = require('csurf');

module.exports = function(app, express) {
  app.use("/", express.static("app/"));
  app.set('views', __dirname + '/../../views'); // Set the view directory, this enables us to use the .render method inside routes

  // solution rapide pour les composants bower
  app.use('/bower_components', express.static(__dirname + '/../../../bower_components'));

  app.use('/styles', express.static(__dirname + '/../../../build/styles'));
  // mise en place de la compilation et publication sass
  // app.use(sassMiddleware({
  //   /* Options */
  //   src: path.join(__dirname, '/../../../build/styles'),
  //   dest: path.join(__dirname, 'css-styles'),
  //   debug: true,
  //   outputStyle: 'compressed',
  //   prefix: '/styles' // Where prefix is at <link rel="stylesheets" href="prefix/style.css"/>
  // }));

  // app.use(express.static(path.join(__dirname, 'css-styles')));

  app.use(bodyParser.urlencoded({
    extended: false
  })); // parse application/x-www-form-urlencoded
  app.use(bodyParser.json()); // parse application/json

  // Setup cookie sessions
  app.use(cookieParser());
  app.use(cookieSession({
    secret: 'Super secret, this should be something super secure'
  }));

  // Add CSRF token to requests to secure our ajax requests from the angular.js app
  app.use(csrf());

  app.set('view engine', 'ejs'); // Set the template engine to ejs

  // This is a little custom middleware which adds the csrf token to local variables
  // which can be used used within ejs template forms by doing something like:
  // <form>
  //   <input type="hidden", name="_csrf", value='<%-csrfToken%>'>
  //   ... other inputs and submit buttons
  // </form>
  //
  // Setting the: res.cookie('XSRF-TOKEN', req.csrfToken()); is for angularJS
  // AngularJs looks for this cookie, and if it exists it sends it along with each
  // ajax request made with the $http service.
  app.use(function(req, res, next) {
    res.locals.csrfToken = req.csrfToken();
    res.cookie('XSRF-TOKEN', req.csrfToken());
    next();
  });

  // Initialize passport middleware for user authentication
  app.use(passport.initialize());
  app.use(passport.session());
}
