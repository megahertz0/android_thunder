package com.uc.addon.sdk.remote;

import android.os.Bundle;
import android.os.RemoteException;
import com.uc.addon.sdk.remote.protocol.SimpleArg;

public abstract class AbstractJSExtension extends AbstractEventReceiver {
    public void onEvent(int i, EventBase eventBase) {
        if (i == 1700) {
            EventJSExtensionCallback eventJSExtensionCallback = (EventJSExtensionCallback) eventBase;
            if (eventBase != null) {
                String onInvoke = onInvoke(eventJSExtensionCallback.tabID, eventJSExtensionCallback.method, eventJSExtensionCallback.argsJson);
                if (eventJSExtensionCallback.callback != null) {
                    SimpleArg simpleArg = new SimpleArg();
                    simpleArg.value = onInvoke;
                    Bundle bundle = new Bundle();
                    simpleArg.toBundle(bundle);
                    try {
                        eventJSExtensionCallback.callback.onReceiveValue(bundle);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public abstract String onInvoke(int i, String str, String str2);
}
