package com.xunlei.downloadprovider.vod;

// compiled from: VodPlayerActivity.java
final class k implements Runnable {
    final /* synthetic */ VodPlayerActivity a;

    k(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void run() {
        this.a.loadDLNADex();
    }
}
