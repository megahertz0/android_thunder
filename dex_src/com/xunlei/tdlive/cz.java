package com.xunlei.tdlive;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: LivePublishEndingActivity.java
class cz implements OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ LivePublishEndingActivity b;

    cz(LivePublishEndingActivity livePublishEndingActivity, Dialog dialog) {
        this.b = livePublishEndingActivity;
        this.a = dialog;
    }

    public void onClick(View view) {
        this.b.k = true;
        this.a.dismiss();
    }
}
