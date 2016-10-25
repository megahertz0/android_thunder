package com.xunlei.downloadprovider.i.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: Update.java
final class p implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ c b;

    p(c cVar, int i) {
        this.b = cVar;
        this.a = i;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        c.p(this.b);
        if (this.a != 0) {
            c.q(this.b);
            this.b.a(c.a(this.b).f);
        }
    }
}
