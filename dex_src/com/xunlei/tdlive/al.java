package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: LivePlayerActivity.java
class al implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ ak b;

    al(ak akVar, String str) {
        this.b = akVar;
        this.a = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        LivePlayerActivity.b(this.b.a.e, this.a);
    }
}
