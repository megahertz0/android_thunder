package com.xunlei.downloadprovider.web;

import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: CopyrightIntermediatePageActivity.java
final class d extends WebChromeClient {
    final /* synthetic */ CopyrightIntermediatePageActivity a;

    d(CopyrightIntermediatePageActivity copyrightIntermediatePageActivity) {
        this.a = copyrightIntermediatePageActivity;
    }

    public final void onProgressChanged(WebView webView, int i) {
        if (i == 100) {
            this.a.i.setProgress(R.styleable.AppCompatTheme_buttonStyle);
            this.a.i.setVisibility(XZBDevice.Wait);
            return;
        }
        this.a.i.setProgress(i);
        this.a.i.setVisibility(0);
    }

    public final void onReceivedTitle(WebView webView, String str) {
        if (TextUtils.isEmpty(str)) {
            this.a.j.setText(this.a.c);
        } else {
            this.a.j.setText(str);
        }
    }
}
