package com.xunlei.downloadprovider.web.core;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.webkit.JsResult;

// compiled from: ThunderWebViewDialog.java
final class r implements OnCancelListener {
    final /* synthetic */ JsResult a;

    r(JsResult jsResult) {
        this.a = jsResult;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        if (this.a != null) {
            this.a.confirm();
        }
        dialogInterface.dismiss();
    }
}
