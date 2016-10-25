package com.xunlei.downloadprovider.personal.settings;

import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xunlei.downloadprovider.businessutil.b;

// compiled from: HelpActivity.java
final class q extends WebViewClient {
    final /* synthetic */ HelpActivity a;

    q(HelpActivity helpActivity) {
        this.a = helpActivity;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str != null) {
            try {
                webView.loadUrl(str);
            } catch (Exception e) {
                new StringBuilder("loadUrl error ").append(e.getMessage());
            }
        }
        return true;
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        new StringBuilder("loadUrl onPageStarted url:").append(str).append(" title:").append(webView.getTitle());
        super.onPageStarted(webView, str, bitmap);
    }

    public final void onPageFinished(WebView webView, String str) {
        new StringBuilder("loadUrl onPageFinished url:").append(str).append(" title:").append(webView.getTitle());
        this.a.a(webView);
        if (str.contains("pricechanges.html") && this.a.h == 0) {
            Editor edit = b.a().a.getSharedPreferences("settingstate", 0).edit();
            edit.putBoolean("isClicked2131756897", true);
            edit.commit();
        }
        super.onPageFinished(webView, str);
    }
}
