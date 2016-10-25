package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

// compiled from: XZBWebviewActivity.java
final class bo extends WebChromeClient {
    final /* synthetic */ XZBWebviewActivity a;

    bo(XZBWebviewActivity xZBWebviewActivity) {
        this.a = xZBWebviewActivity;
    }

    public final void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        this.a.f.setText(str);
    }
}
