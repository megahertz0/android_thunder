package com.xunlei.downloadprovider.commonview.dialog;

import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: XLAlarmDialog.java
final class a implements OnClickListener {
    final /* synthetic */ XLAlarmDialog a;

    a(XLAlarmDialog xLAlarmDialog) {
        this.a = xLAlarmDialog;
    }

    public final void onClick(View view) {
        ((DialogInterface.OnClickListener) this.a.mLeftBtn.getTag()).onClick(this.a, 0);
    }
}
