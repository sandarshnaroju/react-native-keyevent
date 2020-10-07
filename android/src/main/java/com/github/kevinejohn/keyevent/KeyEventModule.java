package com.github.kevinejohn.keyevent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.WritableMap;

import android.view.KeyEvent;


import java.util.HashMap;
import java.util.Map;


/**
 * Created by Kevin Johnson on 8/15/16.
 */
public class KeyEventModule extends ReactContextBaseJavaModule {
    private ReactContext mReactContext;
    private DeviceEventManagerModule.RCTDeviceEventEmitter mJSModule = null;

    private static KeyEventModule instance = null;

    public static KeyEventModule initKeyEventModule(ReactApplicationContext reactContext) {
        instance = new KeyEventModule(reactContext);
        return instance;
    }

    public static KeyEventModule getInstance() {
        return instance;
    }

    public void onKeyDownEvent(int keyCode, KeyEvent keyEvent) {
        if (!mReactContext.hasActiveCatalystInstance()) {
            return;
        }

        if (mJSModule == null) {
            mJSModule = mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
        mJSModule.emit("onKeyDown", getJsEventParams(keyCode, keyEvent, null));
    };

    public void onKeyUpEvent(int keyCode, KeyEvent keyEvent) {
        if (!mReactContext.hasActiveCatalystInstance()) {
            return;
        }

        if (mJSModule == null) {
            mJSModule = mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
        mJSModule.emit("onKeyUp", getJsEventParams(keyCode, keyEvent, null));
    };

    public void onKeyMultipleEvent(int keyCode, int repeatCount, KeyEvent keyEvent) {
        if (!mReactContext.hasActiveCatalystInstance()) {
            return;
        }
        
        if (mJSModule == null) {
            mJSModule = mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }


        mJSModule.emit("onKeyMultiple", getJsEventParams(keyCode, keyEvent, repeatCount));
    };

    protected KeyEventModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }

    private WritableMap getJsEventParams(int keyCode, KeyEvent keyEvent, Integer repeatCount) {
        WritableMap params = new WritableNativeMap();
        int action = keyEvent.getAction();
        char pressedKey = (char) keyEvent.getUnicodeChar();

        if (keyEvent.getAction() == KeyEvent.ACTION_MULTIPLE && keyCode == KeyEvent.KEYCODE_UNKNOWN) {
            String chars = keyEvent.getCharacters();
            if (chars != null) {
                params.putString("characters", chars);
            }
        }

        if (repeatCount != null) {
            params.putInt("repeatcount", repeatCount);
        }

        params.putInt("keyCode", keyCode);
        params.putInt("action", action);
        params.putString("pressedKey", String.valueOf(pressedKey));

        return params;
    }

    @Override
    public String getName() {
        return "KeyEventModule";
    }
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("KEYCODE_0", KeyEvent.KEYCODE_0);
        constants.put("KEYCODE_1", KeyEvent.KEYCODE_1);
        constants.put("KEYCODE_2", KeyEvent.KEYCODE_2);
        constants.put("KEYCODE_3", KeyEvent.KEYCODE_3);
        constants.put("KEYCODE_4", KeyEvent.KEYCODE_4);
        constants.put("KEYCODE_5", KeyEvent.KEYCODE_5);
        constants.put("KEYCODE_6", KeyEvent.KEYCODE_6);
        constants.put("KEYCODE_7", KeyEvent.KEYCODE_7);
        constants.put("KEYCODE_8", KeyEvent.KEYCODE_8);
        constants.put("KEYCODE_9", KeyEvent.KEYCODE_9);
        constants.put("KEYCODE_DPAD_UP", KeyEvent.KEYCODE_DPAD_UP);
        constants.put("KEYCODE_DPAD_DOWN", KeyEvent.KEYCODE_DPAD_DOWN);
        constants.put("KEYCODE_DPAD_LEFT", KeyEvent.KEYCODE_DPAD_LEFT);
        constants.put("KEYCODE_DPAD_RIGHT", KeyEvent.KEYCODE_DPAD_RIGHT);
        constants.put("KEYCODE_DPAD_CENTER", KeyEvent.KEYCODE_DPAD_CENTER);
        constants.put("KEYCODE_BACK", KeyEvent.KEYCODE_BACK);
        constants.put("KEYCODE_GUIDE", KeyEvent.KEYCODE_GUIDE);
        return constants;
    }
}
