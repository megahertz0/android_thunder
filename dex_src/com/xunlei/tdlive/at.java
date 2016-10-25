package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: LivePlayerActivity.java
class at implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ LivePlayerActivity b;

    at(LivePlayerActivity livePlayerActivity, String str) {
        this.b = livePlayerActivity;
        this.a = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.b.b(this.a);
    }
}
