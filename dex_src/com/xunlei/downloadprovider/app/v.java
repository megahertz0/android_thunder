package com.xunlei.downloadprovider.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

// compiled from: NotificationActivity.java
final class v implements OnDismissListener {
    final /* synthetic */ NotificationActivity a;

    v(NotificationActivity notificationActivity) {
        this.a = notificationActivity;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        this.a.finish();
    }
}
