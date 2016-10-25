package com.xunlei.tdlive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// compiled from: LivePublishEndingActivity.java
class cu extends BroadcastReceiver {
    final /* synthetic */ LivePublishEndingActivity a;

    cu(LivePublishEndingActivity livePublishEndingActivity) {
        this.a = livePublishEndingActivity;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.a(intent);
    }
}
