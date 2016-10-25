package com.xunlei.downloadprovider.vod;

import com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.VideoSize;
import com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.a;

// compiled from: VodPlayerView.java
final class as implements a {
    final /* synthetic */ VodPlayerView a;

    as(VodPlayerView vodPlayerView) {
        this.a = vodPlayerView;
    }

    public final void a(VideoSize videoSize) {
        this.a.autoHideControlBar(true);
        this.a.changeVideoScreenSize(videoSize);
    }
}
