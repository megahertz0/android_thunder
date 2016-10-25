package com.umeng.socialize.view;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

// compiled from: OauthDialog.java
class f extends WebChromeClient {
    final /* synthetic */ OauthDialog a;

    f(OauthDialog oauthDialog) {
        this.a = oauthDialog;
    }

    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        if (i < 90) {
            this.a.e.setVisibility(0);
        } else {
            this.a.o.sendEmptyMessage(1);
        }
    }
}
