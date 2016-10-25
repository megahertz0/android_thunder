package com.xunlei.tdlive.play.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.analytics.b.c;

// compiled from: UserConnectMicPresenter.java
class ax implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ av c;

    ax(av avVar, String str, String str2) {
        this.c = avVar;
        this.a = str;
        this.b = str2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.c.b(this.a, this.b, i == 1 ? c.f : "0");
        dialogInterface.dismiss();
        this.c.d();
    }
}
