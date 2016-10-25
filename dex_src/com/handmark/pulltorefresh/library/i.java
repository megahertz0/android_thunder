package com.handmark.pulltorefresh.library;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

// compiled from: PullToRefreshWebView.java
final class i extends WebChromeClient {
    final /* synthetic */ PullToRefreshWebView a;

    i(PullToRefreshWebView pullToRefreshWebView) {
        this.a = pullToRefreshWebView;
    }

    public final void onProgressChanged(WebView webView, int i) {
        if (i == 100) {
            this.a.m();
        }
    }
}
