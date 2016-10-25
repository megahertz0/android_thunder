package com.xunlei.downloadprovider.web.base;

import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

// compiled from: WebViewNormalActivity.java
final class bp extends WebChromeClient {
    final /* synthetic */ WebViewNormalActivity a;

    bp(WebViewNormalActivity webViewNormalActivity) {
        this.a = webViewNormalActivity;
    }

    public final void onReceivedTitle(WebView webView, String str) {
        if (this.a.d != null && TextUtils.isEmpty(this.a.d.getTitleText()) && !TextUtils.isEmpty(str)) {
            this.a.d.setTitleText(str);
        }
    }
}
