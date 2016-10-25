package com.xunlei.downloadprovider.vod;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: VodPlayerActivity.java
final class x implements OnClickListener {
    final /* synthetic */ VodPlayerActivity a;

    x(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.mVodPlayerView.dimissWifiNotifyDialog();
        if (this.a.mShowDLNADialog != null) {
            boolean booleanValue;
            try {
                booleanValue = ((Boolean) this.a.cls.getMethod("isDialogShowing", new Class[0]).invoke(this.a.mShowDLNADialog, new Object[0])).booleanValue();
            } catch (Exception e) {
                e.printStackTrace();
                booleanValue = false;
            }
            if (booleanValue) {
                return;
            }
        }
        if (!this.a.isPlaying()) {
            this.a.startPlayer(false);
        }
    }
}
