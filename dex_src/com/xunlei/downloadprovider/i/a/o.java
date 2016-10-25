package com.xunlei.downloadprovider.i.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: Update.java
final class o implements OnClickListener {
    final /* synthetic */ c a;

    o(c cVar) {
        this.a = cVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        c.p(this.a);
        this.a.a();
    }
}
