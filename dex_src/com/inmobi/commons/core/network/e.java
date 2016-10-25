package com.inmobi.commons.core.network;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.inmobi.commons.a.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: WebViewNetworkTask.java
public class e {
    private NetworkRequest a;
    private WebViewClient b;
    private WebView c;

    public e(NetworkRequest networkRequest, WebViewClient webViewClient) {
        this.b = webViewClient;
        this.a = networkRequest;
    }

    public void a() {
        b();
        this.c.loadUrl(this.a.j());
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void b() {
        this.c = new WebView(a.b());
        this.c.setWebViewClient(this.b);
        this.c.getSettings().setJavaScriptEnabled(true);
        this.c.getSettings().setCacheMode(XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }
}
