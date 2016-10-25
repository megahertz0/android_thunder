package com.xunlei.downloadprovider.vod;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

// compiled from: VodPlayerActivity.java
final class s implements OnClickListener {
    final /* synthetic */ VodPlayerActivity a;

    s(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.mVodPlayerView.dimissWifiNotifyDialog();
        this.a.mNeedStartResume = true;
        this.a.startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), 1);
    }
}
