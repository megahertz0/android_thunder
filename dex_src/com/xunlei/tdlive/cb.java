package com.xunlei.tdlive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// compiled from: LivePlayerDialog.java
class cb extends BroadcastReceiver {
    final /* synthetic */ au a;

    cb(au auVar) {
        this.a = auVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (au.B(this.a) == null || !au.B(this.a).isShowing()) {
            au.a(this.a, "gift", intent.getIntExtra("giftid", -1), false);
        }
    }
}
