package com.xunlei.downloadprovider.web;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.b;

// compiled from: CopyrightIntermediatePageActivity.java
final class c extends WebViewClient {
    final /* synthetic */ CopyrightIntermediatePageActivity a;

    c(CopyrightIntermediatePageActivity copyrightIntermediatePageActivity) {
        this.a = copyrightIntermediatePageActivity;
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        if (!(i == -10 && str2 != null && str2.startsWith("sohuvideo://")) && (this.a.e instanceof WebView)) {
            this.a.e.clearView();
            if (b.a(BrothersApplication.a().getApplicationContext())) {
                this.a.o.setText(2131232764);
                this.a.p.setVisibility(XZBDevice.Wait);
                this.a.n.setImageResource(R.drawable.bg_page_gone);
            } else {
                this.a.o.setText(R.string.invalid_network);
                this.a.p.setText(2131231032);
                this.a.p.setVisibility(0);
                this.a.n.setImageResource(R.drawable.bg_invalid_network);
            }
            this.a.e.setVisibility(XZBDevice.Wait);
            this.a.l.setVisibility(0);
        }
    }

    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.a.g.b();
    }
}
