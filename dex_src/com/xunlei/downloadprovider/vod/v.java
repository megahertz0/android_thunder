package com.xunlei.downloadprovider.vod;

// compiled from: VodPlayerActivity.java
final class v implements Runnable {
    final /* synthetic */ VodPlayerActivity a;

    v(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void run() {
        if (this.a.mIsVisibleToUser) {
            this.a.onVisible();
            this.a.mIsResumed = true;
        } else if (this.a.mUIHandler != null) {
            VodPlayerActivity.TAG;
            this.a.mUIHandler.removeCallbacks(this.a.mResumeRunnable);
            this.a.mUIHandler.post(this.a.mResumeRunnable);
        }
    }
}
