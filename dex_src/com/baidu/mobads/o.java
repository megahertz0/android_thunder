package com.baidu.mobads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;

class o implements OnKeyListener {
    final /* synthetic */ SslErrorHandler a;
    final /* synthetic */ j b;

    o(j jVar, SslErrorHandler sslErrorHandler) {
        this.b = jVar;
        this.a = sslErrorHandler;
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 4) {
            return false;
        }
        this.a.cancel();
        dialogInterface.dismiss();
        return true;
    }
}
