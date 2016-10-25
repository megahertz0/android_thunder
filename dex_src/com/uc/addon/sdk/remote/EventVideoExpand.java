package com.uc.addon.sdk.remote;

public class EventVideoExpand implements EventBase {
    private DownloadTask a;

    public EventVideoExpand() {
        this.a = new DownloadTask();
    }

    final void a(DownloadTask downloadTask) {
        if (downloadTask != null) {
            this.a = downloadTask;
        }
    }

    public DownloadTask getDownloadTask() {
        return this.a;
    }

    public int getEventId() {
        return EventIds.EVENT_VIDEOEXPAND;
    }
}
