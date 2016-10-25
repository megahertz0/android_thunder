package com.xunlei.downloadprovider.vod;

import com.aplayer.aplayerandroid.APlayerAndroid.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: VodPlayerActivity.java
final class n implements c {
    final /* synthetic */ VodPlayerActivity a;

    n(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void a(int i) {
        VodPlayerActivity.TAG;
        if (i == 100 || !this.a.isPlaying()) {
            this.a.isBuffering = false;
            this.a.mVodPlayerView.setPlayerSate(this.a.isPlaying() ? XZBDevice.DOWNLOAD_LIST_FAILED : 1);
            this.a.mSuspendEndTime = System.currentTimeMillis();
            if (this.a.mSuspendType == 0) {
                VodPlayerActivity.access$5714(this.a, this.a.mSuspendEndTime - this.a.mSuspendStartTime);
                VodPlayerActivity.access$5808(this.a);
            } else {
                VodPlayerActivity.access$5914(this.a, this.a.mSuspendEndTime - this.a.mSuspendStartTime);
                VodPlayerActivity.access$6008(this.a);
            }
            this.a.mSuspendStartTime = this.a.mSuspendEndTime;
            this.a.mSuspendType = 1;
        } else if (!this.a.isBuffering) {
            this.a.mVodPlayerView.setPlayerSate(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            this.a.isBuffering = true;
            VodPlayerActivity.access$4908(this.a);
            this.a.mSuspendStartTime = System.currentTimeMillis();
        }
    }
}
