package com.xunlei.downloadprovider.web.core;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.webkit.JsPromptResult;

// compiled from: ThunderWebViewDialog.java
final class x implements OnCancelListener {
    final /* synthetic */ JsPromptResult a;

    x(JsPromptResult jsPromptResult) {
        this.a = jsPromptResult;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        this.a.cancel();
        dialogInterface.dismiss();
    }
}
