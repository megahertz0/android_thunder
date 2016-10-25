package com.xunlei.tdlive.play.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: UserConnectMicPresenter.java
class ay implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ av c;

    ay(av avVar, int i, String str) {
        this.c = avVar;
        this.a = i;
        this.b = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 1) {
            if (this.c.h != null) {
                this.c.h.a();
            }
            this.c.c(this.a, this.b);
        }
        dialogInterface.dismiss();
    }
}
