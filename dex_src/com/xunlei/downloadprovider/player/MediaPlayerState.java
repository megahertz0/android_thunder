package com.xunlei.downloadprovider.player;

public enum MediaPlayerState {
    IDLE,
    INITIALIZED,
    PREPARING,
    PREPARED,
    LOADING,
    STARTED,
    PAUSED,
    PLAYBACK_COMPLETED,
    STOPPED,
    ERROR,
    RELEASE;

    static {
        IDLE = new MediaPlayerState("IDLE", 0);
        INITIALIZED = new MediaPlayerState("INITIALIZED", 1);
        PREPARING = new MediaPlayerState("PREPARING", 2);
        PREPARED = new MediaPlayerState("PREPARED", 3);
        LOADING = new MediaPlayerState("LOADING", 4);
        STARTED = new MediaPlayerState("STARTED", 5);
        PAUSED = new MediaPlayerState("PAUSED", 6);
        PLAYBACK_COMPLETED = new MediaPlayerState("PLAYBACK_COMPLETED", 7);
        STOPPED = new MediaPlayerState("STOPPED", 8);
        ERROR = new MediaPlayerState("ERROR", 9);
        RELEASE = new MediaPlayerState("RELEASE", 10);
        a = new MediaPlayerState[]{IDLE, INITIALIZED, PREPARING, PREPARED, LOADING, STARTED, PAUSED, PLAYBACK_COMPLETED, STOPPED, ERROR, RELEASE};
    }
}
