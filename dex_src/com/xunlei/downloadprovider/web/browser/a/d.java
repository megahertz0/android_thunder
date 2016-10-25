package com.xunlei.downloadprovider.web.browser.a;

import android.os.Build.VERSION;
import android.webkit.DownloadListener;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;
import com.xunlei.downloadprovider.web.core.a.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: XLWebViewCore.java
public final class d {
    protected WebView a;
    protected final b b;
    protected final c c;
    protected final a d;
    protected boolean e;
    public boolean f;
    private a g;

    // compiled from: XLWebViewCore.java
    public static final class a {
        public boolean a;

        public a() {
            this.a = false;
        }
    }

    public final void a() {
        if (this.a != null) {
            this.a.stopLoading();
        }
    }

    public final boolean c() {
        return this.a != null ? this.a.canGoBack() : false;
    }

    public final boolean e() {
        return this.a != null ? this.a.canGoForward() : false;
    }

    public final String g() {
        return this.a != null ? this.a.getUrl() : null;
    }

    public final String h() {
        return this.a != null ? this.a.getTitle() : null;
    }

    public final WebBackForwardList i() {
        return this.a != null ? this.a.copyBackForwardList() : null;
    }

    public final void j() {
        if (this.a != null) {
            this.a.requestFocus();
        }
    }

    public final a k() {
        if (this.g == null) {
            this.g = new a();
        }
        return this.g;
    }

    public d() {
        this.b = new e(this);
        this.c = new f(this);
        this.d = new g(this);
        this.e = false;
        this.f = false;
        b.a().b();
    }

    public final void a(WebView webView) {
        this.a = webView;
        if (webView != null && webView != null) {
            webView.setWebChromeClient(this.d);
            webView.setWebViewClient(this.c);
            webView.setDownloadListener(this.b);
        }
    }

    public final WebView l() {
        WebView webView = this.a;
        this.a = null;
        return webView;
    }

    public final void a(WebViewClient webViewClient) {
        this.c.a = webViewClient;
    }

    public final void a(DownloadListener downloadListener) {
        this.b.a = downloadListener;
    }

    public final void a(WebChromeClient webChromeClient) {
        this.d.a = webChromeClient;
    }

    public final boolean m() {
        return this.e;
    }

    protected final void a(boolean z) {
        this.e = z;
    }

    public final void b() {
        this.f = true;
        if (this.a != null) {
            this.a.reload();
        }
    }

    public final void d() {
        this.f = true;
        if (this.a != null) {
            this.a.goBack();
        }
    }

    public final void f() {
        this.f = true;
        if (this.a != null) {
            this.a.goForward();
        }
    }

    public final void a(String str) {
        this.f = true;
        if (this.a == null) {
            return;
        }
        if (VERSION.SDK_INT < 19 || str == null || !str.startsWith(BaseJsInterface.JS_PREFIX)) {
            this.a.loadUrl(str);
        } else {
            this.a.evaluateJavascript(str.substring(XZBDevice.Success), null);
        }
    }
}
