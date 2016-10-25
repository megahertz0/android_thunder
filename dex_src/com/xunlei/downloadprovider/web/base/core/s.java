package com.xunlei.downloadprovider.web.base.core;

import android.webkit.WebView;
import android.webkit.WebViewClient;

// compiled from: WebTitleBar.java
public final class s extends WebViewClient {
    final /* synthetic */ WebTitleBar a;

    public s(WebTitleBar webTitleBar) {
        this.a = webTitleBar;
    }

    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        WebTitleBar.c(this.a);
    }
}
