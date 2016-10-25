package com.xunlei.downloadprovider.a.a;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import com.umeng.message.entity.UMessage;

// compiled from: NotificationManagerWrapper.java
public final class b implements a {
    private static b a;
    private Context b;
    private NotificationManager c;

    private b(Context context) {
        this.b = context;
        this.c = (NotificationManager) this.b.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b(context);
            }
            bVar = a;
        }
        return bVar;
    }

    public final void a(int i, Notification notification) {
        this.c.notify(i, notification);
    }

    public final void a(int i) {
        this.c.cancel(i);
    }

    public final void a() {
        this.c.cancelAll();
    }
}
