package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: LivePlayerDialog.java
class bj implements OnClickListener {
    final /* synthetic */ bi a;

    bj(bi biVar) {
        this.a = biVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (au.i(this.a.a)) {
            au.d(this.a.a, true);
            au.a(this.a.a, "\u5e10\u53f7\u88ab\u8e22");
        }
        if (!au.b(this.a.a, false)) {
            this.a.a.onBackPressed();
        }
    }
}
