package com.hokolinks.plugin;

import android.content.Context;
import com.hokolinks.Hoko;
import com.hokolinks.model.Deeplink;
import com.hokolinks.model.DeeplinkCallback;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CDVHoko extends CordovaPlugin {

    private enum Function {
        setup, mapRoute, addHandler
    }

    @Override
    public boolean execute(String action, JSONArray jsonArray, CallbackContext callbackContext) throws JSONException {
        try {
            switch (action) {
                case "setup":
                    return setup(jsonArray);
                case "mapRoute":
                    return mapRoute(jsonArray, callbackContext);
                case "addHandler":
                    return addHandler(callbackContext);
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
                final callbackContext.success(deeplink.toJSON());
            }
        });
        callbackContext.success();
        return true;
    }

    private boolean addHandler(CallbackContext callbackContext) throws JSONException {

        Hoko.deeplinking().addHandler(new DeeplinkCallback() {
            @Override
            public void deeplinkOpened(Deeplink deeplink) {
                final callbackContext.success(deeplink.toJSON());
            }
        });
        callbackContext.success();
        return true;
    }
}
