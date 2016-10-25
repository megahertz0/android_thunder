package com.xunlei.tdlive;

import android.app.Dialog;

// compiled from: LivePlayEndingActivity.java
class z implements Runnable {
    final /* synthetic */ Dialog a;
    final /* synthetic */ LivePlayEndingActivity b;

    z(LivePlayEndingActivity livePlayEndingActivity, Dialog dialog) {
        this.b = livePlayEndingActivity;
        this.a = dialog;
    }

    public void run() {
        this.a.dismiss();
        this.b.finish();
        if (this.b.i.getCount() > 0) {
            this.b.a(this.b.i.a(0));
        }
    }
}
