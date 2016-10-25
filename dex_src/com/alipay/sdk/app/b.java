package com.alipay.sdk.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.util.k;
import com.alipay.sdk.widget.a;

public final class b extends WebViewClient {
    boolean a;
    private Activity b;
    private boolean c;
    private Handler d;
    private a e;
    private Runnable f;

    public b(Activity activity) {
        this.f = new f(this);
        this.b = activity;
        this.d = new Handler(this.b.getMainLooper());
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.a = true;
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        com.alipay.sdk.app.statistic.a.a(c.a, c.p, "\u8bc1\u4e66\u9519\u8bef");
        if (this.c) {
            sslErrorHandler.proceed();
            this.c = false;
            return;
        }
        this.b.runOnUiThread(new c(this, sslErrorHandler));
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return k.a(webView, str, this.b);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.e == null) {
            this.e = new a(this.b, a.a);
        }
        this.e.a();
        this.d.postDelayed(this.f, StatisticConfig.MIN_UPLOAD_INTERVAL);
        super.onPageStarted(webView, str, bitmap);
    }

    public final void onPageFinished(WebView webView, String str) {
        b();
        this.d.removeCallbacks(this.f);
    }

    private void a() {
        if (this.e == null) {
            this.e = new a(this.b, a.a);
        }
        this.e.a();
    }

    private void b() {
        if (this.e != null) {
            this.e.b();
        }
        this.e = null;
    }

    private boolean c() {
        return this.a;
    }
}
