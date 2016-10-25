package com.uc.addon.sdk.remote;

import com.xunlei.tdlive.sdk.IHost;

public class EventTabRemoved implements EventBase {
    public int tabId;

    public int getEventId() {
        return IHost.HOST_NOFITY_PAGE_SELECTED;
    }
}
