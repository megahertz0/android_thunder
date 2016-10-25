package com.xunlei.downloadprovider.task;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;

// compiled from: ThunderTask.java
final class c implements OnClickListener {
    final /* synthetic */ long a;
    final /* synthetic */ ThunderTask b;

    c(ThunderTask thunderTask, long j) {
        this.b = thunderTask;
        this.a = j;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        DownloadCenterActivity.a(this.b, this.a, "alarmDialog");
    }
}
