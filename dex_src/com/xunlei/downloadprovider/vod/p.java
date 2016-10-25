package com.xunlei.downloadprovider.vod;

import android.text.TextUtils;

// compiled from: VodPlayerActivity.java
final class p implements Runnable {
    final /* synthetic */ VodPlayerActivity a;

    p(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void run() {
        this.a.mVodPlayerView.showError("\u64ad\u653e\u51fa\u9519\u5566~", !TextUtils.isEmpty(this.a.mToPlayUrl));
    }
}
