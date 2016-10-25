package com.uc.addon.sdk.remote;

import com.uc.addon.sdk.remote.protocol.TabCreateEventArg;
import com.xunlei.tdlive.sdk.IHost;

public class EventTabCreated implements EventBase {
    public boolean isActive;
    public int tabId;

    final void a(TabCreateEventArg tabCreateEventArg) {
        if (tabCreateEventArg != null) {
            this.tabId = tabCreateEventArg.id;
            this.isActive = tabCreateEventArg.isActive;
        }
    }

    public int getEventId() {
        return IHost.HOST_NOFITY_REFRESH_LIST;
    }
}
