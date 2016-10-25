package com.xunlei.downloadprovider.web.browser.a;

import android.graphics.Bitmap;
import android.webkit.WebView;
import com.xunlei.downloadprovider.web.core.a.a;
import com.xunlei.downloadprovider.web.core.a.b;

// compiled from: XLWebViewCore.java
final class f extends c {
    protected int b;
    final /* synthetic */ d c;

    f(d dVar) {
        this.c = dVar;
        this.b = 0;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Object obj = 1;
        if (this.b > 1) {
            new StringBuilder("WebPage Redirection: ").append(str).append(" RefUrl: ").append(webView.getUrl());
            int i = 1;
        } else {
            Object obj2 = null;
        }
        String url = webView.getUrl();
        a c = b.a().c();
        if (c == null || !c.a(url, str)) {
            obj = null;
        } else {
            new StringBuilder("\r\n--------\r\nadblock viewU:").append(url).append("\r\nadblock overU:").append(str);
        }
        if (!(obj == null || r0 != null || this.c.f)) {
            new StringBuilder("[AdBlock] block shouldOverrideUrlLoading: ").append(str).append(" RefUrl: ").append(webView.getUrl());
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.c.a(true);
        this.b++;
        super.onPageStarted(webView, str, bitmap);
    }

    public final void onPageFinished(WebView webView, String str) {
        this.c.a(false);
        this.b = 0;
        this.c.f = false;
        super.onPageFinished(webView, str);
    }
}
