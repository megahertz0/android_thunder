package com.xunlei.tdlive;

import android.app.Dialog;

// compiled from: LivePublishEndingActivity.java
class da implements Runnable {
    final /* synthetic */ Dialog a;
    final /* synthetic */ LivePublishEndingActivity b;

    da(LivePublishEndingActivity livePublishEndingActivity, Dialog dialog) {
        this.b = livePublishEndingActivity;
        this.a = dialog;
    }

    public void run() {
        if (!this.b.k) {
            this.a.dismiss();
            this.b.finish();
            if (this.b.i.getCount() > 0) {
                this.b.a(this.b.i.a(0));
            }
        }
    }
}
