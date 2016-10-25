package com.xunlei.downloadprovider.web.core;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.core.ThunderWebView.CurrentShowState;
import com.xunlei.downloadprovider.web.core.a.a;
import com.xunlei.downloadprovider.web.core.a.b;
import com.xunlei.downloadprovider.web.w;
import com.xunlei.tdlive.R;
import com.xunlei.xllib.b.g;

// compiled from: ThunderWebView.java
final class i extends WebViewClient {
    final /* synthetic */ ThunderWebView a;

    i(ThunderWebView thunderWebView) {
        this.a = thunderWebView;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        a c = b.a().c();
        if (c == null || !c.a(webView.getUrl(), str)) {
            this.a.setCanLoadingShow(true);
            if (str.endsWith("?jump=sjxlmp4")) {
                Uri parse = Uri.parse(str.substring(0, str.indexOf("?jump=sjxlmp4")));
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(parse, "video/*");
                this.a.o.startActivity(intent);
                return true;
            } else if (this.a.f == null) {
                return false;
            } else {
                String c2 = com.xunlei.downloadprovider.url.b.c(str);
                if (c2 != null) {
                    ThunderWebView.a(this.a, new DownData(null, c2, this.a.getRefUrl()));
                    StatReporter.reportOverallDownload("browser");
                    return true;
                } else if (com.xunlei.downloadprovider.url.b.a(str) || com.xunlei.downloadprovider.url.b.e(str) || com.xunlei.downloadprovider.url.b.b(str) || str.startsWith("thunder://") || str.startsWith("ed2k://") || com.xunlei.downloadprovider.url.b.f(str)) {
                    ThunderWebView.a(this.a, new DownData(null, str, this.a.getRefUrl()));
                    StatReporter.reportOverallDownload("browser");
                    return true;
                } else {
                    this.a.e(str);
                    return this.a.f.a(webView, str);
                }
            }
        }
        this.a.e;
        new StringBuilder("\r\n--------\r\nadblock viewU:").append(webView.getUrl()).append("\r\nadblock overU:").append(str);
        return true;
    }

    public final void onLoadResource(WebView webView, String str) {
        super.onLoadResource(webView, str);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.a.e;
        if (this.a.m != null) {
            this.a.m.a();
        }
        webView.setBackgroundResource(R.color.white);
        this.a.setTextHintColor(this.a.getResources().getColor(R.color.TextAppearanceEntrySecondaryTitle));
        if (this.a.n != null) {
            c e = this.a.n;
            if (!(e.e == null || e.a == null || !e.a.d)) {
                com.xunlei.downloadprovider.web.core.b.a aVar = e.e;
                if (!(str == null || aVar.a == null || ((String) aVar.a.get(str)) != null)) {
                    long currentTimeMillis = System.currentTimeMillis();
                    String a = g.a((com.xunlei.downloadprovider.a.b.d() + currentTimeMillis).getBytes());
                    aVar.a.put(str, a);
                    w.a();
                    a = w.a("PageLoad", "start", a, str, String.valueOf(currentTimeMillis));
                    w.a();
                    w.a(a, aVar.b);
                }
            }
        }
        if (this.a.g == CurrentShowState.show_error) {
            webView.clearView();
        }
        if (!ThunderWebView.b(str)) {
            false;
            ThunderWebView.h();
            this.a.setTextHintColor(this.a.getResources().getColor(R.color.TextAppearanceEntryPrimaryTitle));
        }
        this.a.setBlockNetworkImage(true);
        if (this.a.f != null) {
            this.a.f;
        }
        this.a.setCurrentUrl(str);
    }

    public final void onPageFinished(WebView webView, String str) {
        boolean z = false;
        this.a.e;
        if (this.a.m != null) {
            this.a.m.b();
        }
        this.a.setBlockNetworkImage(false);
        if (this.a.f != null) {
            this.a.f.a();
        }
        if (ThunderWebView.b(str)) {
            ThunderWebView.i();
        }
        this.a.setDetailPageWebCoverGone(true);
        if (com.xunlei.xllib.a.b.a(BrothersApplication.a().getApplicationContext())) {
            if (BrowserUtil.b(str) || this.a.g == CurrentShowState.show_error) {
                z = true;
            } else {
                z = true;
            }
        }
        this.a.setCurShowView(z ? CurrentShowState.show_webview : CurrentShowState.show_error);
        this.a.setCanLoadingShow(true);
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.a.e;
        new StringBuilder("onReceivedError : errorCode = ").append(i).append(" , description = ").append(str).append(" , failingUrl = ").append(str2);
        if (this.a.a instanceof WebView) {
            this.a.a.clearView();
        }
        if (this.a.m != null) {
            this.a.m.b();
        }
        this.a.a.loadUrl("javascript:document.body.innerHTML=\"\";");
        this.a.setCurShowView(CurrentShowState.show_error);
    }
}
