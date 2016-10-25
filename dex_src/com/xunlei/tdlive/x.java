package com.xunlei.tdlive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// compiled from: LivePlayEndingActivity.java
class x extends BroadcastReceiver {
    final /* synthetic */ LivePlayEndingActivity a;

    x(LivePlayEndingActivity livePlayEndingActivity) {
        this.a = livePlayEndingActivity;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.a(intent);
    }
}
