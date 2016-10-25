package com.xunlei.downloadprovider.web.core;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsPromptResult;

// compiled from: ThunderWebViewDialog.java
final class v implements OnClickListener {
    final /* synthetic */ JsPromptResult a;

    v(JsPromptResult jsPromptResult) {
        this.a = jsPromptResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.cancel();
    }
}
