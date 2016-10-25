package com.xunlei.downloadprovider.web.browser;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.umeng.a;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.url.b;
import com.xunlei.downloadprovider.web.sniff.SnifferResultsFragment.d;
import java.net.URLDecoder;

// compiled from: BrowserActivity.java
final class e extends WebViewClient {
    final /* synthetic */ BrowserActivity a;

    e(BrowserActivity browserActivity) {
        this.a = browserActivity;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String str2 = BrowserActivity.a;
        str2 = BrowserActivity.a;
        new StringBuilder("WebView - shouldOverrideUrlLoading: ").append(str).append(String.format(" Original(%s) Url(%s)", new Object[]{webView.getOriginalUrl(), webView.getUrl()}));
        str2 = webView.getUrl();
        if (str2 == null) {
            str2 = a.d;
        }
        return a(str, str2);
    }

    private boolean a(String str, String str2) {
        String path;
        boolean z;
        this.a.n = false;
        Uri parse = Uri.parse(str);
        String str3 = "browser";
        String str4 = null;
        if (parse != null && "xunleiapp".equals(parse.getScheme())) {
            path = parse.getPath();
            if (path == null || (path.equals("/hotResource") || path.equals("/resourceDetail"))) {
                DownloadCenterActivity.a(this.a, "fromBrowser");
                this.a.finish();
                return true;
            } else if (path.equals("/sharePage")) {
                str = parse.getQueryParameter("taskDownload");
                if (TextUtils.isEmpty(str)) {
                    XLToast.a(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u8be5\u94fe\u63a5\u5df2\u8fc7\u671f");
                    this.a.finish();
                    return false;
                }
                str3 = "share_h5";
                String queryParameter = parse.getQueryParameter("fileNameDetail");
                if (queryParameter != null) {
                    try {
                        queryParameter = URLDecoder.decode(queryParameter, "utf-8");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                this.a.n = true;
                str4 = queryParameter;
            }
        }
        path = b.c(str);
        if (path != null) {
            this.a.b.post(new f(this, str4, path, str2, str3));
            z = true;
        } else if (b.a(str) || b.e(str) || b.b(str) || str.startsWith("thunder://") || str.startsWith("ed2k://") || b.f(str)) {
            this.a.b.post(new g(this, str4, str, str2, str3));
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    @SuppressLint({"NewApi"})
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        String str2 = BrowserActivity.a;
        str2 = BrowserActivity.a;
        new StringBuilder("WebView - onPageStarted: ").append(str).append(String.format(" Original(%s) Url(%s)", new Object[]{webView.getOriginalUrl(), webView.getUrl()}));
        if (VERSION.SDK_INT < 17 || !this.a.isDestroyed()) {
            super.onPageStarted(webView, str, bitmap);
            this.a.k.b(true);
            this.a.a();
            this.a.e = true;
            this.a.k.a(str, true);
            this.a.k.a(true);
            BrowserActivity.g(this.a);
            if (this.a.v != null) {
                this.a.v.a().a(webView, str);
            }
            if (this.a.t != null) {
                d c = this.a.t;
                String originalUrl = webView.getOriginalUrl();
                webView.copyBackForwardList();
                c.a(str, originalUrl);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public final void onPageFinished(WebView webView, String str) {
        String originalUrl = webView.getOriginalUrl();
        String url = webView.getUrl();
        String str2 = BrowserActivity.a;
        new StringBuilder("WebView - onPageFinished: ").append(str).append(String.format(" Original(%s) Url(%s)", new Object[]{originalUrl, url}));
        if (VERSION.SDK_INT < 17 || !this.a.isDestroyed()) {
            this.a.k.b(false);
            this.a.e = false;
            BrowserActivity.g(this.a);
            this.a.k.a(false);
            this.a.k.a = str;
            if (url != null) {
                this.a.k.a(url);
            } else {
                this.a.k.a(this.a.d.a);
                this.a.a(-1, this.a.d.a);
            }
            super.onPageFinished(webView, str);
            if (this.a.v != null) {
                this.a.v.a().b(webView, str);
            }
            if (!this.a.v.g && this.a.f) {
                this.a.c();
            }
            if (this.a.t != null) {
                this.a.t.b(str, originalUrl);
            }
        }
    }

    public final void onLoadResource(WebView webView, String str) {
        super.onLoadResource(webView, str);
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        a(webView, i, str2);
        super.onReceivedError(webView, i, str, str2);
    }

    private void a(WebView webView, int i, String str) {
        webView.stopLoading();
        if (this.a.i != null) {
            if (VERSION.SDK_INT >= 19) {
                this.a.i.evaluateJavascript("javascript:document.body.innerHTML=\"\";", null);
            } else {
                this.a.i.loadUrl("javascript:document.body.innerHTML=\"\";");
            }
        }
        this.a.m = true;
        Message obtain = Message.obtain(this.a.b, 10002);
        obtain.obj = str;
        obtain.arg1 = i;
        this.a.b.sendEmptyMessage(10002);
    }

    @TargetApi(23)
    public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        if (webResourceRequest.isForMainFrame()) {
            a(webView, webResourceError.getErrorCode(), webResourceRequest.getUrl().toString());
        }
        super.onReceivedError(webView, webResourceRequest, webResourceError);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onReceivedSslError(android.webkit.WebView r3, android.webkit.SslErrorHandler r4, android.net.http.SslError r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.web.browser.e.onReceivedSslError(android.webkit.WebView, android.webkit.SslErrorHandler, android.net.http.SslError):void");
        /*
        this = this;
        if (r4 == 0) goto L_0x000c;
    L_0x0002:
        r0 = r5.getPrimaryError();	 Catch:{ Exception -> 0x0011 }
        r1 = 3;
        if (r0 != r1) goto L_0x000d;
    L_0x0009:
        r4.proceed();	 Catch:{ Exception -> 0x0011 }
    L_0x000c:
        return;
    L_0x000d:
        r4.cancel();	 Catch:{ Exception -> 0x0011 }
        goto L_0x000c;
    L_0x0011:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x000c;
        */
    }
}
