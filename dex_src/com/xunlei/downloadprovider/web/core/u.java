package com.xunlei.downloadprovider.web.core;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.webkit.JsResult;

// compiled from: ThunderWebViewDialog.java
final class u implements OnCancelListener {
    final /* synthetic */ JsResult a;

    u(JsResult jsResult) {
        this.a = jsResult;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        this.a.cancel();
        dialogInterface.dismiss();
    }
}
