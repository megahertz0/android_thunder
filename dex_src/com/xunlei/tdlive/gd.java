package com.xunlei.tdlive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.t;

// compiled from: XLLiveApplication.java
class gd extends BroadcastReceiver {
    final /* synthetic */ gb a;

    gd(gb gbVar) {
        this.a = gbVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (ac.a()) {
            this.a.d = false;
            t.a(true);
        }
    }
}
