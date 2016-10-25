package com.xunlei.downloadprovider.vod;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: VodPlayerActivity.java
final class u implements OnClickListener {
    final /* synthetic */ VodPlayerActivity a;

    u(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.getOnlineVideoUrl();
        this.a.mVodPlayerView.dimissWifiNotifyDialog();
    }
}
