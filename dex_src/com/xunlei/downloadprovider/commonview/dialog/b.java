package com.xunlei.downloadprovider.commonview.dialog;

import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: XLAlarmDialog.java
final class b implements OnClickListener {
    final /* synthetic */ XLAlarmDialog a;

    b(XLAlarmDialog xLAlarmDialog) {
        this.a = xLAlarmDialog;
    }

    public final void onClick(View view) {
        ((DialogInterface.OnClickListener) this.a.mRightBtn.getTag()).onClick(this.a, 0);
    }
}
