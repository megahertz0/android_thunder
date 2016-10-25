package com.xunlei.downloadprovider.vod;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;

// compiled from: VodPlayerActivity.java
final class w implements OnClickListener {
    final /* synthetic */ VodPlayerActivity a;

    w(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.mNeedStartResume = false;
        this.a.mVodPlayerView.dimissWifiNotifyDialog();
        XLToast.c(this.a, XLToastType.XLTOAST_TYPE_NORMAL, this.a.getResources().getString(2131233159));
        this.a.finishDelay();
    }
}
