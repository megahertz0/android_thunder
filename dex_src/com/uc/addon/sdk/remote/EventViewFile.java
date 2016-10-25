package com.uc.addon.sdk.remote;

import android.content.Intent;

public class EventViewFile implements EventBase {
    public Intent intent;

    public int getEventId() {
        return EventIds.EVENT_VIEW_FILE;
    }
}
