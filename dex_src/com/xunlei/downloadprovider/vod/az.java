package com.xunlei.downloadprovider.vod;

// compiled from: VodPlayerView.java
final class az implements Runnable {
    final /* synthetic */ VodPlayerView a;

    az(VodPlayerView vodPlayerView) {
        this.a = vodPlayerView;
    }

    public final void run() {
        this.a.hideControlBar(false);
    }
}
