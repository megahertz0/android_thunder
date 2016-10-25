package com.xunlei.downloadprovider.vod;

import android.os.Handler;
import com.aplayer.aplayerandroid.APlayerAndroid.f;

// compiled from: VodPlayerActivity.java
final class m implements f {
    final /* synthetic */ VodPlayerActivity a;

    m(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void a(String str) {
        VodPlayerActivity.TAG;
        if (this.a.mUIHandler != null) {
            this.a.mSuspendType = 1;
            if (this.a.mUIHandler != null) {
                this.a.mUIHandler.removeCallbacks(this.a.mOpenTimoutRunnable);
            } else {
                this.a.mUIHandler = new Handler(this.a.mTaskOperateMessageListener);
            }
            if ("0x0".contentEquals(str)) {
                VodPlayerActivity.TAG;
                new StringBuilder("pos=>").append(this.a.mCurPlayPos).append(",dur=>").append(this.a.mVideoDuration);
                this.a.mCurPlayPos = this.a.mVideoDuration;
                this.a.savePlayInfo();
                this.a.onPlayEnd();
            } else if ("0x1".contentEquals(str)) {
                this.a.savePlayInfo();
                if (this.a.playAnother) {
                    this.a.playAnother = false;
                    VodPlayerActivity.TAG;
                    new StringBuilder("play another to open =>").append(this.a.mToPlayUrl);
                    this.a.mBufferCount = 0;
                    this.a.mNotReportSpeedBfTextShow = true;
                    this.a.mLoadingTime = System.currentTimeMillis();
                    this.a.mVodPlayerView.setPlayerSate(0);
                    this.a.postTimeoutRunnable();
                    this.a.aPlayerAndroid.Open(this.a.mToPlayUrl);
                } else if (this.a.handleNewIntent) {
                    this.a.handleNewIntent = false;
                    this.a.handleIntent();
                }
            } else {
                this.a.handlePlayingError(str);
            }
        }
    }
}
