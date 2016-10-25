package com.xunlei.downloadprovider.web.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

// compiled from: CommentDialog.java
final class c implements OnDismissListener {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        if (this.a.b == null) {
            this.a.c.put(Long.valueOf(-1), this.a.a.getText().toString());
        } else {
            this.a.c.put(Long.valueOf(this.a.b.a), this.a.a.getText().toString());
        }
        if (this.a.i != null) {
            this.a.i.onDismiss(dialogInterface);
        }
    }
}
