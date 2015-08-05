var exec = require('cordova/exec');

module.exports = {
  setup: function(token) {
    cordova.exec(null, null, 'CDVHoko', 'setup', [token]);
  },
  mapRoute: function(route, deeplinkCallback) {
    cordova.exec(deeplinkCallback, null, 'CDVHoko', 'mapRoute', [route]);
  },
  addHandler: function(deeplinkCallback) {
    cordova.exec(deeplinkCallback, null, 'CDVHoko', 'addHandler', []);
  }
};
