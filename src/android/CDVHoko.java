package com.hokolinks.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class CDVHoko extends CordovaPlugin {

    private enum Function {
        setup, mapRoute
    }

    @Override
    public boolean execute(String action, JSONArray jsonArray, CallbackContext callbackContext) throws JSONException {
        try {
            Function function = Function.valueOf(action)
            switch (function) {
                case Function.setup:
                    return setup(jsonArray);
                case Function.mapRoute:
                    return mapRoute(jsonArray, callbackContext);
                default:
                    return false;
            }
        } catch (IllegalStateException e) {
            return false;
        }
    }

    private boolean setup(JSONArray jsonArray) throws JSONException {
        Context applicationContext = ((CordovaActivity) this.webView.getContext()).getApplicationContext();
        String token = jsonArray.getString(0);
        Hoko.setup(applicationContext, token, true);
        return true;
    }

    private boolean mapRoute(JSONArray jsonArray, CallbackContext callbackContext) throws JSONException {
        String route = jsonArray.getString(0);
        Hoko.deeplinking().mapRoute(route, new DeeplinkCallback() {
            @Override
            public void deeplinkOpened(Deeplink deeplink) {
                callbackContext.success(deeplink)); //toJSON()
            }
        })
        callbackContext.success(message);
        return true;
    }

}
