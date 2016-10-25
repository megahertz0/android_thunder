package com.xunlei.downloadprovider.i.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;

// compiled from: Update.java
final class q implements OnKeyListener {
    final /* synthetic */ c a;

    q(c cVar) {
        this.a = cVar;
    }

    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (4 != i) {
            return false;
        }
        if (c.r(this.a) == 0) {
            c.s(this.a);
        } else {
            dialogInterface.dismiss();
            c.p(this.a);
            this.a.a();
        }
        return true;
    }
}
