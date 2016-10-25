package com.xunlei.downloadprovider.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: NotificationActivity.java
final class u implements OnClickListener {
    final /* synthetic */ NotificationActivity a;

    u(NotificationActivity notificationActivity) {
        this.a = notificationActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (NotificationActivity.b != null) {
            NotificationActivity.b.obtainMessage(0, XZBDevice.DOWNLOAD_LIST_RECYCLE, -1).sendToTarget();
        }
        this.a.e.dismiss();
    }
}
