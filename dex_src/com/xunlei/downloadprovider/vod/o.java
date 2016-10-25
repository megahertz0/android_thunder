package com.xunlei.downloadprovider.vod;

import android.text.TextUtils;

// compiled from: VodPlayerActivity.java
final class o implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ VodPlayerActivity b;

    o(VodPlayerActivity vodPlayerActivity, String str) {
        this.b = vodPlayerActivity;
        this.a = str;
    }

    public final void run() {
        this.b.mVodPlayerView.showError(this.a, !TextUtils.isEmpty(this.b.mToPlayUrl));
    }
}
