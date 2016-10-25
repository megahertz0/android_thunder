package com.xunlei.downloadprovider.download.e;

import android.content.Context;
import android.content.Intent;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;

// compiled from: DownloadListIntentHandler.java
public final class a extends com.xunlei.downloadprovider.thirdpart.a {
    public a(Context context, Intent intent) {
        super(context, intent);
    }

    public final void a() {
        if (this.b != null && this.c != null) {
            DownloadCenterActivity.a(this.b, DLCenterEntry.icon.toString());
        }
    }
}
