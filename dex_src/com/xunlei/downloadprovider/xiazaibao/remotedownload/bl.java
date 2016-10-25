package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: XZBWebviewActivity.java
final class bl implements OnClickListener {
    final /* synthetic */ XZBWebviewActivity a;

    bl(XZBWebviewActivity xZBWebviewActivity) {
        this.a = xZBWebviewActivity;
    }

    public final void onClick(View view) {
        if (this.a.a.canGoBack()) {
            this.a.a.goBack();
        } else {
            this.a.finish();
        }
    }
}
