package com.uc.addon.sdk.remote;

public class EventDownload implements EventBase {
    public String filename;
    public String path;
    public String url;

    public int getEventId() {
        return EventIds.EVENT_DOWNLOAD;
    }
}
