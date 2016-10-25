package com.xunlei.downloadprovider.web.browser;

import android.webkit.WebView;

// compiled from: BrowserSniffer.java
final class s implements Runnable {
    final /* synthetic */ WebView a;
    final /* synthetic */ String b;
    final /* synthetic */ r c;

    s(r rVar, WebView webView, String str) {
        this.c = rVar;
        this.a = webView;
        this.b = str;
    }

    public final void run() {
        this.c.a.a.a(this.a, this.b);
    }
}
