package com.xunlei.downloadprovider.download.center;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;

// compiled from: DownloadCenterActivity.java
final class c implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ DownloadCenterActivity b;

    c(DownloadCenterActivity downloadCenterActivity, String str) {
        this.b = downloadCenterActivity;
        this.a = str;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (!TextUtils.isEmpty(this.a)) {
            com.xunlei.downloadprovider.a.c.c(this.b, this.a);
        }
    }
}
