package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

// compiled from: LivePlayerDialog.java
class bz implements OnDismissListener {
    final /* synthetic */ au a;

    bz(au auVar) {
        this.a = auVar;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.a.a.mPlayButtonLayout.showAnimation(true);
        au.a(this.a, null);
    }
}
