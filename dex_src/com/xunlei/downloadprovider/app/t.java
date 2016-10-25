package com.xunlei.downloadprovider.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: NotificationActivity.java
final class t implements OnClickListener {
    final /* synthetic */ NotificationActivity a;

    t(NotificationActivity notificationActivity) {
        this.a = notificationActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (NotificationActivity.b != null) {
            NotificationActivity.b.obtainMessage(-1).sendToTarget();
        }
        this.a.e.dismiss();
    }
}
