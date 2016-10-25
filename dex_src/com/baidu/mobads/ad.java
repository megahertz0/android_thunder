package com.baidu.mobads;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ad extends WebViewClient {
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        a(webView, str);
        return true;
    }

    private void a(WebView webView, String str) {
        if (webView != null) {
            try {
                webView.loadUrl(str);
            } catch (Exception e) {
            }
        }
    }
}
