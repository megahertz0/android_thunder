package com.uc.addon.sdk.remote;

public class EventMemoryState implements EventBase {
    public int mState;

    public int getEventId() {
        return EventIds.EVENT_MEMORY_STATE;
    }
}
