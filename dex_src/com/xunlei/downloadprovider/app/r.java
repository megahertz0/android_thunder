package com.xunlei.downloadprovider.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: NotificationActivity.java
final class r implements OnClickListener {
    final /* synthetic */ NotificationActivity a;

    r(NotificationActivity notificationActivity) {
        this.a = notificationActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (NotificationActivity.b != null) {
            NotificationActivity.b.obtainMessage(0).sendToTarget();
        }
        this.a.e.dismiss();
    }
}
