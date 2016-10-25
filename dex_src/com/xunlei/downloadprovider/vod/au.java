package com.xunlei.downloadprovider.vod;

import android.widget.PopupWindow.OnDismissListener;

// compiled from: VodPlayerView.java
final class au implements OnDismissListener {
    final /* synthetic */ VodPlayerView a;

    au(VodPlayerView vodPlayerView) {
        this.a = vodPlayerView;
    }

    public final void onDismiss() {
        this.a.showControlBar();
        this.a.autoHideControlBar(true);
    }
}
