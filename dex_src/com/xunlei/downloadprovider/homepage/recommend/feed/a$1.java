package com.xunlei.downloadprovider.homepage.recommend.feed;

import com.xunlei.downloadprovider.player.MediaPlayerState;

// compiled from: ChannelFeedVideoItemView.java
/* synthetic */ class a$1 {
    static final /* synthetic */ int[] a;

    static {
        a = new int[MediaPlayerState.values().length];
        try {
            a[MediaPlayerState.INITIALIZED.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[MediaPlayerState.PREPARING.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[MediaPlayerState.STARTED.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
