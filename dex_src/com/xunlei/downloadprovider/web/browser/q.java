package com.xunlei.downloadprovider.web.browser;

import android.webkit.ConsoleMessage;
import android.webkit.WebView;
import com.xunlei.downloadprovider.web.browser.BrowserSniffer.f;

// compiled from: BrowserSniffer.java
final class q implements f {
    final /* synthetic */ BrowserSniffer a;

    q(BrowserSniffer browserSniffer) {
        this.a = browserSniffer;
    }

    public final void a(WebView webView, String str) {
        this.a.p = false;
        if (this.a.h == 1) {
            new StringBuilder("[Step] onPageStarted:").append(str).append(String.format(" current(%s) original(%s)", new Object[]{webView.getUrl(), webView.getOriginalUrl()}));
            webView.getSettings().setBlockNetworkImage(true);
        } else if (this.a.g) {
            new StringBuilder("[Step] onPageStarted:").append(str).append(String.format(" current(%s) original(%s)", new Object[]{webView.getUrl(), webView.getOriginalUrl()}));
            if (!this.a.i.getOriginalUrl().equals(str)) {
                this.a.c();
                if (this.a.q) {
                    this.a.l.c(this.a, null);
                }
            }
        }
    }

    public final void b(WebView webView, String str) {
        if (this.a.h == 1) {
            new StringBuilder("[Step] onPageFinished:").append(str).append(String.format(" current(%s) original(%s)", new Object[]{webView.getUrl(), webView.getOriginalUrl()}));
            if (this.a.p) {
                new StringBuilder("[Skip] onPageFinished:").append(str).append(String.format(" current(%s) original(%s)", new Object[]{webView.getUrl(), webView.getOriginalUrl()}));
                return;
            }
            this.a.a(webView, str);
            return;
        }
        new StringBuilder("[Step] onPageFinished:").append(str).append(String.format(" current(%s) original(%s)", new Object[]{webView.getUrl(), webView.getOriginalUrl()}));
        if (this.a.q) {
            this.a.f.postDelayed(new s(new r(this), webView, str), 500);
        } else {
            webView.getSettings().setBlockNetworkImage(false);
        }
    }

    public final boolean a(ConsoleMessage consoleMessage) {
        consoleMessage.message();
        return false;
    }
}
