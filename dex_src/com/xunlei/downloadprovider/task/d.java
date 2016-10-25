package com.xunlei.downloadprovider.task;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: ThunderTask.java
final class d implements OnClickListener {
    final /* synthetic */ ThunderTask a;

    d(ThunderTask thunderTask) {
        this.a = thunderTask;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.mCreateExsitTaskErrorDialog.dismiss();
        this.a.onClickDialogCancel();
    }
}
