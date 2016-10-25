package com.xunlei.tdlive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xunlei.tdlive.util.ac;

// compiled from: LivePlayerActivity.java
class ab extends BroadcastReceiver {
    final /* synthetic */ LivePlayerActivity a;

    ab(LivePlayerActivity livePlayerActivity) {
        this.a = livePlayerActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (!ac.a(1) && ac.a() && !this.a.p) {
            this.a.showToast(this.a.getString(R.string.no_wifi_for_flow_tips), 0, R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
            this.a.p = true;
        }
    }
}
