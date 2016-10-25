package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xunlei.xllib.a.b;
import java.net.URISyntaxException;

// compiled from: XZBWebviewActivity.java
final class bn extends WebViewClient {
    final /* synthetic */ XZBWebviewActivity a;

    bn(XZBWebviewActivity xZBWebviewActivity) {
        this.a = xZBWebviewActivity;
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        XZBWebviewActivity.e;
        this.a.b.setVisibility(0);
        XZBWebviewActivity.h(this.a);
    }

    public final void onPageFinished(WebView webView, String str) {
        XZBWebviewActivity.e;
        super.onPageFinished(webView, str);
        this.a.c = str;
        XZBWebviewActivity.h(this.a);
        XZBWebviewActivity.i(this.a);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        XZBWebviewActivity.e;
        super.onPageStarted(webView, str, bitmap);
        XZBWebviewActivity.a();
        if (b.a(this.a)) {
            XZBWebviewActivity.g(this.a);
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str == null || !str.startsWith("weixin://")) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        try {
            webView.getContext().startActivity(Intent.parseUri(str, 1));
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return true;
        }
    }
}
