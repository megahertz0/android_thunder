package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: LivePlayerActivity.java
class ao implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ an b;

    ao(an anVar, String str) {
        this.b = anVar;
        this.a = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        LivePlayerActivity.b(this.b.a.b, this.a);
    }
}
