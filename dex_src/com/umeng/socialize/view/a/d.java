package com.umeng.socialize.view.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

// compiled from: ACProgressCustom.java
class d implements OnDismissListener {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (c.a(this.a) != null) {
            c.a(this.a).cancel();
            c.a(this.a, null);
        }
        if (c.b(this.a) != null) {
            c.b(this.a).clear();
            c.a(this.a, null);
        }
        c.a(this.a, 0);
        c.b(this.a, 0);
        c.a(this.a, null);
    }
}
