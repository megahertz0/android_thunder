package com.xunlei.downloadprovider.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.util.aa;

// compiled from: NotificationActivity.java
final class q implements OnClickListener {
    final /* synthetic */ NotificationActivity a;

    q(NotificationActivity notificationActivity) {
        this.a = notificationActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        NotificationActivity.b;
        this.a.e.dismiss();
        aa.a(this.a, "is_unactive_pause", true);
    }
}
