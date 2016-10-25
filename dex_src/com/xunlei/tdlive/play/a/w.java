package com.xunlei.tdlive.play.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.util.ac;

// compiled from: ReplayActivityPresenter.java
class w extends BroadcastReceiver {
    final /* synthetic */ v a;

    w(v vVar) {
        this.a = vVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (!ac.a(1) && ac.a() && !this.a.h) {
            this.a.a.showToast(this.a.a.getString(R.string.no_wifi_for_flow_tips), 0, R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
            this.a.h = true;
        }
    }
}
