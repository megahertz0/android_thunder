package com.bumptech.glide.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// compiled from: DefaultConnectivityMonitor.java
final class f extends BroadcastReceiver {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void onReceive(Context context, Intent intent) {
        boolean z = this.a.b;
        this.a.b = e.a(context);
        if (z != this.a.b) {
            this.a.a.a(this.a.b);
        }
    }
}
