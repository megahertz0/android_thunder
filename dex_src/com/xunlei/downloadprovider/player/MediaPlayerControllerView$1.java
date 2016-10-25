package com.xunlei.downloadprovider.player;

/* synthetic */ class MediaPlayerControllerView$1 {
    static final /* synthetic */ int[] a;

    static {
        a = new int[MediaPlayerState.values().length];
        try {
            a[MediaPlayerState.PLAYBACK_COMPLETED.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[MediaPlayerState.PREPARING.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[MediaPlayerState.LOADING.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[MediaPlayerState.PREPARED.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[MediaPlayerState.STARTED.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[MediaPlayerState.PAUSED.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[MediaPlayerState.STOPPED.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        try {
            a[MediaPlayerState.IDLE.ordinal()] = 8;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[MediaPlayerState.ERROR.ordinal()] = 9;
        } catch (NoSuchFieldError e9) {
        }
    }
}
