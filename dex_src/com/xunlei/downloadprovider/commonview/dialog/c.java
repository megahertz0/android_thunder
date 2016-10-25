package com.xunlei.downloadprovider.commonview.dialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: XLAlarmDialog.java
final class c implements OnClickListener {
    final /* synthetic */ XLAlarmDialog a;

    c(XLAlarmDialog xLAlarmDialog) {
        this.a = xLAlarmDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
