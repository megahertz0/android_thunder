package com.umeng.socialize.view;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;

// compiled from: OauthDialog.java
class g extends b {
    final /* synthetic */ OauthDialog a;

    g(OauthDialog oauthDialog) {
        this.a = oauthDialog;
        super(null);
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslErrorHandler.cancel();
    }
}
