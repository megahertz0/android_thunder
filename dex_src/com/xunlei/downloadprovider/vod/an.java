package com.xunlei.downloadprovider.vod;

// compiled from: VodPlayerForBtActivity.java
final class an implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ VodPlayerForBtActivity b;

    an(VodPlayerForBtActivity vodPlayerForBtActivity, String str) {
        this.b = vodPlayerForBtActivity;
        this.a = str;
    }

    public final void run() {
        VodPlayerForBtActivity.a(this.b, this.a);
    }
}
