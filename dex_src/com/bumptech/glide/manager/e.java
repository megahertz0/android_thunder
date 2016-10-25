package com.bumptech.glide.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.bumptech.glide.manager.c.a;

// compiled from: DefaultConnectivityMonitor.java
public final class e implements c {
    final a a;
    boolean b;
    private final Context c;
    private boolean d;
    private final BroadcastReceiver e;

    public e(Context context, a aVar) {
        this.e = new f(this);
        this.c = context.getApplicationContext();
        this.a = aVar;
    }

    static boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public final void b_() {
    }

    public final void b() {
        if (!this.d) {
            this.b = a(this.c);
            this.c.registerReceiver(this.e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.d = true;
        }
    }

    public final void c() {
        if (this.d) {
            this.c.unregisterReceiver(this.e);
            this.d = false;
        }
    }
}
