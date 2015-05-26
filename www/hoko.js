var exec = require('cordova/exec');

module.exports = {
  test: function(name, successCallBack, errorCallBack) {
    cordova.exec(successCallBack, errorCallBack, 'Hello', 'greet', [name]);
  }
};
