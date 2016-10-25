package com.xunlei.downloadprovider.util;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;

// compiled from: XLAlarmDialogActivity.java
final class ae implements OnClickListener {
    final /* synthetic */ XLAlarmDialogActivity a;

    ae(XLAlarmDialogActivity xLAlarmDialogActivity) {
        this.a = xLAlarmDialogActivity;
    }

    public final void onClick(View view) {
        this.a.finish();
        DownloadCenterActivity.a(this.a, this.a.j, "alarmDialog");
    }
}
