package com.xunlei.downloadprovider.qrcode;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xunlei.tdlive.R;

// compiled from: ScancodeIntroduceActivity.java
final class m extends WebViewClient {
    final /* synthetic */ ScancodeIntroduceActivity a;

    m(ScancodeIntroduceActivity scancodeIntroduceActivity) {
        this.a = scancodeIntroduceActivity;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str != null) {
            webView.loadUrl(str);
        }
        return true;
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    public final void onPageFinished(WebView webView, String str) {
        this.a.b.setVisibility(0);
        this.a.e.setVisibility(0);
        this.a.a.setBackgroundResource(R.drawable.common_layout_content_bkg);
        super.onPageFinished(webView, str);
    }
}
