package com.xunlei.downloadprovider.i.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;

// compiled from: Update.java
final class e implements OnKeyListener {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (4 != i) {
            return 84 == i;
        } else {
            if (c.n(this.a)) {
                c.u(this.a);
                return true;
            }
            dialogInterface.dismiss();
            return true;
        }
    }
}
