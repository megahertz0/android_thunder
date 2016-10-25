package com.xunlei.downloadprovider.player.wrapper;

public enum PlayerMessageState {
    IDLE,
    SETTING_DATA_SOURCE,
    PREPARING,
    STARTING,
    PAUSING,
    RESETTING,
    STOPPING,
    RELEASING;

    static {
        IDLE = new PlayerMessageState("IDLE", 0);
        SETTING_DATA_SOURCE = new PlayerMessageState("SETTING_DATA_SOURCE", 1);
        PREPARING = new PlayerMessageState("PREPARING", 2);
        STARTING = new PlayerMessageState("STARTING", 3);
        PAUSING = new PlayerMessageState("PAUSING", 4);
        RESETTING = new PlayerMessageState("RESETTING", 5);
        STOPPING = new PlayerMessageState("STOPPING", 6);
        RELEASING = new PlayerMessageState("RELEASING", 7);
        a = new PlayerMessageState[]{IDLE, SETTING_DATA_SOURCE, PREPARING, STARTING, PAUSING, RESETTING, STOPPING, RELEASING};
    }
}
