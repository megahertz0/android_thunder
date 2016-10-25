package com.xunlei.downloadprovider.util;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: XLAlarmDialogActivity.java
final class af implements OnClickListener {
    final /* synthetic */ XLAlarmDialogActivity a;

    af(XLAlarmDialogActivity xLAlarmDialogActivity) {
        this.a = xLAlarmDialogActivity;
    }

    public final void onClick(View view) {
        this.a.finish();
    }
}
