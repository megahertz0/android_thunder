package com.xunlei.downloadprovider.web.base;

import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.xunlei.xllib.a.b;

// compiled from: LongVideoDetailActivity.java
final class w extends WebChromeClient {
    final /* synthetic */ LongVideoDetailActivity a;

    w(LongVideoDetailActivity longVideoDetailActivity) {
        this.a = longVideoDetailActivity;
    }

    public final void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        if (b.a(this.a) && !TextUtils.isEmpty(str) && !"null".equalsIgnoreCase(str)) {
            this.a.h = str;
            this.a.f.setText(str);
        }
    }
}
