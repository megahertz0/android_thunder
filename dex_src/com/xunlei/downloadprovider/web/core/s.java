package com.xunlei.downloadprovider.web.core;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

// compiled from: ThunderWebViewDialog.java
final class s implements OnClickListener {
    final /* synthetic */ JsResult a;

    s(JsResult jsResult) {
        this.a = jsResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.cancel();
    }
}
