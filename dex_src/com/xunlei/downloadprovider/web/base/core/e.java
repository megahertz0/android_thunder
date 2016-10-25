package com.xunlei.downloadprovider.web.base.core;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import com.xunlei.xllib.a.b;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: CustomWebView.java
final class e extends f {
    final /* synthetic */ CustomWebView a;

    e(CustomWebView customWebView) {
        this.a = customWebView;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!str.startsWith("weixin://wap/pay?")) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.a.getContext().startActivity(intent);
        return true;
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        CustomWebView.a(this.a, str);
        webView.getSettings().setBlockNetworkImage(true);
    }

    public final void onPageFinished(WebView webView, String str) {
        this.a.d();
        if (!b.a(this.a.getContext()) || "data:text/html,chromewebdata".equalsIgnoreCase(str)) {
            this.a.b();
        }
        webView.getSettings().setBlockNetworkImage(false);
        super.onPageFinished(webView, str);
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        this.a.d();
        webView.stopLoading();
        this.a.h();
        if (!b.a(this.a.getContext())) {
            CustomWebView.f(this.a).setErrorType(SimpleLog.LOG_LEVEL_DEBUG);
        } else if (i == 404) {
            CustomWebView.f(this.a).setErrorType(1);
        }
        this.a.b();
    }
}
