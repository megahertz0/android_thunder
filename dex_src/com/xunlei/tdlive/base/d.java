package com.xunlei.tdlive.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

// compiled from: AlertDialog.java
class d implements OnCancelListener {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.a.a != null) {
            this.a.a.onClick(dialogInterface, 0);
        }
    }
}
