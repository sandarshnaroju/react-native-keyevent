import {
  DeviceEventEmitter,
  NativeEventEmitter,
  NativeModules,
  Platform,
} from "react-native";

export const KEYCODE_0 = NativeModules.KeyEventModule.KEYCODE_0;
export const KEYCODE_1 = NativeModules.KeyEventModule.KEYCODE_1;
export const KEYCODE_2 = NativeModules.KeyEventModule.KEYCODE_2;
export const KEYCODE_3 = NativeModules.KeyEventModule.KEYCODE_3;
export const KEYCODE_4 = NativeModules.KeyEventModule.KEYCODE_4;
export const KEYCODE_5 = NativeModules.KeyEventModule.KEYCODE_5;
export const KEYCODE_6 = NativeModules.KeyEventModule.KEYCODE_6;
export const KEYCODE_7 = NativeModules.KeyEventModule.KEYCODE_7;
export const KEYCODE_8 = NativeModules.KeyEventModule.KEYCODE_8;
export const KEYCODE_9 = NativeModules.KeyEventModule.KEYCODE_9;
export const KEYCODE_DPAD_UP = NativeModules.KeyEventModule.KEYCODE_DPAD_UP;
export const KEYCODE_DPAD_DOWN = NativeModules.KeyEventModule.KEYCODE_DPAD_DOWN;
export const KEYCODE_DPAD_LEFT = NativeModules.KeyEventModule.KEYCODE_DPAD_LEFT;
export const KEYCODE_DPAD_RIGHT =
  NativeModules.KeyEventModule.KEYCODE_DPAD_RIGHT;
export const KEYCODE_DPAD_CENTER =
  NativeModules.KeyEventModule.KEYCODE_DPAD_CENTER;
export const KEYCODE_BACK = NativeModules.KeyEventModule.KEYCODE_BACK;
export const KEYCODE_GUIDE = NativeModules.KeyEventModule.KEYCODE_GUIDE;

class KeyEvent {
  onKeyDownListener(cb) {
    this.removeKeyDownListener();
    if (Platform.OS === "ios") {
      let keyEvent = new NativeEventEmitter(NativeModules.RNKeyEvent);
      this.listenerKeyDown = keyEvent.addListener("onKeyDown", cb);
    } else {
      this.listenerKeyDown = DeviceEventEmitter.addListener("onKeyDown", cb);
    }
  }

  removeKeyDownListener() {
    if (this.listenerKeyDown) {
      this.listenerKeyDown.remove();
      this.listenerKeyDown = null;
    }
  }

  onKeyUpListener(cb) {
    this.removeKeyUpListener();
    if (Platform.OS === "ios") {
      let keyEvent = new NativeEventEmitter(NativeModules.RNKeyEvent);
      this.listenerKeyUp = keyEvent.addListener("onKeyUp", cb);
    } else {
      this.listenerKeyUp = DeviceEventEmitter.addListener("onKeyUp", cb);
    }
  }

  removeKeyUpListener() {
    if (this.listenerKeyUp) {
      this.listenerKeyUp.remove();
      this.listenerKeyUp = null;
    }
  }

  onKeyMultipleListener(cb) {
    this.removeKeyMultipleListener();
    if (Platform.OS === "ios") {
      let keyEvent = new NativeEventEmitter(NativeModules.RNKeyEvent);
      this.listenerKeyMultiple = keyEvent.addListener("onKeyMultiple", cb);
    } else {
      this.listenerKeyMultiple = DeviceEventEmitter.addListener(
        "onKeyMultiple",
        cb
      );
    }
  }

  removeKeyMultipleListener() {
    if (this.listenerKeyMultiple) {
      this.listenerKeyMultiple.remove();
      this.listenerKeyMultiple = null;
    }
  }
}

export default new KeyEvent();
