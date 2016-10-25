package com.uc.addon.sdk.remote;

public class EventPageStarted implements EventBase {
    public int id;
    public String url;

    public int getEventId() {
        return EventIds.EVENT_PAGE_START;
    }
}
