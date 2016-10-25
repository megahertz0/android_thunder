package com.xunlei.downloadprovider.vod;

// compiled from: VodPlayerView.java
final class ba implements Runnable {
    final /* synthetic */ VodPlayerView a;

    ba(VodPlayerView vodPlayerView) {
        this.a = vodPlayerView;
    }

    public final void run() {
        this.a.hideCenterProgressView();
    }
}
