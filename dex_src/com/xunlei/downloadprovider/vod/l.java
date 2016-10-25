package com.xunlei.downloadprovider.vod;

import com.aplayer.aplayerandroid.APlayerAndroid.d;

// compiled from: VodPlayerActivity.java
final class l implements d {
    final /* synthetic */ VodPlayerActivity a;

    l(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void a(boolean z) {
        VodPlayerActivity.TAG;
        if (this.a.mOldPlayer != null) {
            this.a.mOldPlayer.Destroy();
            this.a.mOldPlayer = null;
        }
        if (!this.a.mOpenTimeOut) {
            if (this.a.mUIHandler != null) {
                this.a.mUIHandler.removeCallbacks(this.a.mOpenTimoutRunnable);
            }
            if (!z) {
                this.a.onOpenVideoFailed();
            } else if (this.a.mIsVisibleToUser) {
                this.a.onOpenVideoSuccess();
            } else {
                this.a.mIsOpenningBeforePaused = true;
            }
            this.a.mPlayInfoReady = true;
        }
    }
}
