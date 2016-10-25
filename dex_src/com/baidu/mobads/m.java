package com.baidu.mobads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.SslErrorHandler;

class m implements OnClickListener {
    final /* synthetic */ SslErrorHandler a;
    final /* synthetic */ j b;

    m(j jVar, SslErrorHandler sslErrorHandler) {
        this.b = jVar;
        this.a = sslErrorHandler;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.proceed();
    }
}
