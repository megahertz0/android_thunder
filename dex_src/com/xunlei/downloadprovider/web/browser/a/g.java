package com.xunlei.downloadprovider.web.browser.a;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;

// compiled from: XLWebViewCore.java
final class g extends a {
    final /* synthetic */ d b;

    g(d dVar) {
        this.b = dVar;
    }

    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        if (!this.b.k().a) {
            return super.onJsAlert(webView, str, str2, jsResult);
        }
        jsResult.cancel();
        new StringBuilder("[AdBlock] block onJsAlert: ").append(str).append(" Message: ").append(str2);
        return true;
    }

    public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        if (!this.b.k().a) {
            return super.onJsConfirm(webView, str, str2, jsResult);
        }
        jsResult.cancel();
        new StringBuilder("[AdBlock] block onJsConfirm: ").append(str).append(" Message: ").append(str2);
        return true;
    }

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (!this.b.k().a) {
            return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }
        jsPromptResult.cancel();
        new StringBuilder("[AdBlock] block onJsPrompt: ").append(str).append(" Message: ").append(str2);
        return true;
    }
}
