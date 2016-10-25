package com.xunlei.downloadprovider.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xunlei.downloadprovider.app.BrothersApplication.d;

// compiled from: BrothersApplication.java
final class g extends BroadcastReceiver {
    final /* synthetic */ BrothersApplication a;

    g(BrothersApplication brothersApplication) {
        this.a = brothersApplication;
    }

    public final void onReceive(Context context, Intent intent) {
        BrothersApplication.o;
        int i;
        if (intent.getAction().equals("android.intent.action.MEDIA_MOUNTED")) {
            for (i = 0; i < this.a.t.size(); i++) {
                ((d) this.a.t.get(i)).a();
            }
            return;
        }
        for (i = 0; i < this.a.t.size(); i++) {
            ((d) this.a.t.get(i)).a();
        }
    }
}
