package com.xunlei.tdlive.play.view;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: UserInfoWindowHelper.java
class as implements OnClickListener {
    final /* synthetic */ Runnable a;
    final /* synthetic */ ah b;

    as(ah ahVar, Runnable runnable) {
        this.b = ahVar;
        this.a = runnable;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 1) {
            this.a.run();
        }
        dialogInterface.dismiss();
    }
}
