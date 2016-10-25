package com.xunlei.downloadprovider.vod;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;

// compiled from: VodPlayerActivity.java
final class t implements OnCancelListener {
    final /* synthetic */ VodPlayerActivity a;

    t(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        this.a.mNeedStartResume = false;
        this.a.mVodPlayerView.dimissWifiNotifyDialog();
        VodPlayerActivity.TAG;
        XLToast.c(this.a, XLToastType.XLTOAST_TYPE_NORMAL, this.a.getResources().getString(2131233159));
        this.a.finishDelay();
    }
}
