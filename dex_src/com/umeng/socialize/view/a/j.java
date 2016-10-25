package com.umeng.socialize.view.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

// compiled from: ACProgressPie.java
class j implements OnDismissListener {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.a.c != null) {
            this.a.c.cancel();
            this.a.c = null;
        }
        this.a.d = 0;
        this.a.b = null;
    }
}
