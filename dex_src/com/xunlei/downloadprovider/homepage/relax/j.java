package com.xunlei.downloadprovider.homepage.relax;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: RelaxPicBrowseActivity.java
final class j extends WebViewClient {
    final /* synthetic */ RelaxPicBrowseActivity a;

    j(RelaxPicBrowseActivity relaxPicBrowseActivity) {
        this.a = relaxPicBrowseActivity;
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslErrorHandler.proceed();
    }

    public final void onPageFinished(WebView webView, String str) {
        this.a.e.setVisibility(XZBDevice.Wait);
        this.a.g.setVisibility(0);
    }
}
