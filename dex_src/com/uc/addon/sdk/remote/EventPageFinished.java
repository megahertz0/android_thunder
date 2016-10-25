package com.uc.addon.sdk.remote;

public class EventPageFinished implements EventBase {
    public int id;
    public String url;

    public int getEventId() {
        return EventIds.EVENT_PAGE_FINISH;
    }
}
