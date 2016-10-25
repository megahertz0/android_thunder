package com.xunlei.downloadprovider.player.wrapper;

import com.xunlei.downloadprovider.player.MediaPlayerState;

// compiled from: AsyncMediaPlayer.java
/* synthetic */ class a$1 {
    static final /* synthetic */ int[] a;

    static {
        a = new int[MediaPlayerState.values().length];
        try {
            a[MediaPlayerState.IDLE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[MediaPlayerState.STOPPED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[MediaPlayerState.RELEASE.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[MediaPlayerState.STARTED.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
