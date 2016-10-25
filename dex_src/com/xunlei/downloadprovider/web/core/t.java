package com.xunlei.downloadprovider.web.core;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

// compiled from: ThunderWebViewDialog.java
final class t implements OnClickListener {
    final /* synthetic */ JsResult a;

    t(JsResult jsResult) {
        this.a = jsResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.confirm();
    }
}
