var exec = require('cordova/exec');

module.exports = {
  mapRoute: function(route, successCallBack, errorCallBack) {
    cordova.exec(successCallBack, errorCallBack, 'CDVHoko', 'mapRoute', [name]);
  }
};
