package com.uc.addon.sdk.remote;

import com.uc.addon.sdk.remote.protocol.IValueCallback;

public class EventJSExtensionCallback implements EventBase {
    public String argsJson;
    public IValueCallback callback;
    public String method;
    public int tabID;

    EventJSExtensionCallback(int i, String str, String str2, IValueCallback iValueCallback) {
        this.method = str;
        this.argsJson = str2;
        this.callback = iValueCallback;
        this.tabID = i;
    }

    public int getEventId() {
        return EventIds.EVENT_VIDEOEXPAND;
    }
}
