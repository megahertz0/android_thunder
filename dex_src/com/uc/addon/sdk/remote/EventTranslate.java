package com.uc.addon.sdk.remote;

public class EventTranslate implements EventBase {
    public String text;

    public int getEventId() {
        return EventIds.EVENT_TRANSLATE;
    }
}
