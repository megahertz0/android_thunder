package com.xunlei.downloadprovider.web.browser;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.xunlei.downloadprovider.web.sniff.SnifferResultsFragment.d;

// compiled from: BrowserActivity.java
final class c extends WebChromeClient {
    final /* synthetic */ BrowserActivity a;

    c(BrowserActivity browserActivity) {
        this.a = browserActivity;
    }

    public final void onProgressChanged(WebView webView, int i) {
        BrowserTitleBarFragment b = BrowserActivity.b(this.a);
        if (b.b != null) {
            b.b.setProgress(i);
        }
        if (BrowserActivity.c(this.a) != null) {
            d c = BrowserActivity.c(this.a);
            BrowserActivity.d(this.a);
            c.a(i);
        }
    }

    public final void onReceivedTitle(WebView webView, String str) {
        String str2 = BrowserActivity.a;
        BrowserActivity.b(this.a).a(webView.getUrl());
        BrowserActivity.a(this.a, str);
        BrowserActivity.a(this.a, str, webView.getUrl());
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (BrowserActivity.d(this.a) != null) {
            BrowserActivity.d(this.a).a().a(consoleMessage);
        }
        return super.onConsoleMessage(consoleMessage);
    }
}
