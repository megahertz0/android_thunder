package com.baidu.mobads;

import android.app.AlertDialog.Builder;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import android.webkit.WebViewClient;
import com.umeng.a;

class j extends WebViewClient {
    final /* synthetic */ b a;
    final /* synthetic */ AppActivity b;

    j(AppActivity appActivity, b bVar) {
        this.b = appActivity;
        this.a = bVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldOverrideUrlLoading(android.webkit.WebView r5, java.lang.String r6) {
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.j.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
        /*
        this = this;
        r0 = 1;
        if (r6 == 0) goto L_0x000c;
    L_0x0003:
        r1 = "about:blank";
        r1 = r6.equals(r1);	 Catch:{ Exception -> 0x003b }
        if (r1 == 0) goto L_0x000e;
    L_0x000c:
        r0 = 0;
    L_0x000d:
        return r0;
    L_0x000e:
        r1 = r4.b;	 Catch:{ Exception -> 0x003b }
        r2 = 0;
        r3 = new com.baidu.mobads.k;	 Catch:{ Exception -> 0x003b }
        r3.<init>(r4, r6);	 Catch:{ Exception -> 0x003b }
        r1.a(r5, r6, r2, r3);	 Catch:{ Exception -> 0x003b }
        r1 = r5.getHitTestResult();	 Catch:{ Exception -> 0x003b }
        if (r1 == 0) goto L_0x000d;
    L_0x001f:
        r1 = r1.getType();	 Catch:{ Exception -> 0x003b }
        if (r1 != 0) goto L_0x000d;
    L_0x0025:
        r1 = r4.b;	 Catch:{ Exception -> 0x003b }
        r1 = r1.D;	 Catch:{ Exception -> 0x003b }
        r2 = com.baidu.mobads.AppActivity.o;	 Catch:{ Exception -> 0x003b }
        r3 = "AppActivity shouldOverrideUrlLoading and hitType==0";
        r1.d(r2, r3);	 Catch:{ Exception -> 0x003b }
        r1 = r4.a;	 Catch:{ Exception -> 0x003b }
        r2 = 1;
        r1.a = r2;	 Catch:{ Exception -> 0x003b }
        goto L_0x000d;
    L_0x003b:
        r1 = move-exception;
        r2 = r4.b;
        r2 = r2.D;
        r3 = com.baidu.mobads.AppActivity.o;
        r1 = r1.getMessage();
        r2.d(r3, r1);
        goto L_0x000d;
        */
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        if (!(this.b.curWebview == null || str == null)) {
            this.b.curWebview.a = str;
        }
        this.a.b = str;
        this.a.c = false;
        if (!this.a.a) {
            a(str);
        }
    }

    public void onPageFinished(WebView webView, String str) {
        if (!this.a.a && this.a.b.equals(str)) {
            if (this.b.g == -1) {
                this.b.g = (int) (System.currentTimeMillis() - this.b.s);
            }
            if (!this.a.c) {
                this.a.c = true;
                a(this.a.b, 0);
            }
        }
        this.a.a = false;
        this.a.b = a.d;
        if (this.b.H != null) {
            this.b.H.onPageFinished(webView);
        }
        super.onPageFinished(webView, str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r7) {
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.j.a(java.lang.String):void");
        /*
        this = this;
        r0 = r6.b;
        r0 = r0.q;
        if (r0 == 0) goto L_0x0096;
    L_0x0008:
        r0 = r6.a;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.d;	 Catch:{ Exception -> 0x00a8 }
        if (r0 == 0) goto L_0x0097;
    L_0x000e:
        r0 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.A;	 Catch:{ Exception -> 0x00a8 }
        r0.n = r7;	 Catch:{ Exception -> 0x00a8 }
        r0 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.A;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.z;	 Catch:{ Exception -> 0x00a8 }
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x0030;
    L_0x0024:
        r0 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.A;	 Catch:{ Exception -> 0x00a8 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00a8 }
        r0.z = r2;	 Catch:{ Exception -> 0x00a8 }
    L_0x0030:
        r0 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.A;	 Catch:{ Exception -> 0x00a8 }
        r1 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r1 = com.baidu.mobads.AppActivity.i(r1);	 Catch:{ Exception -> 0x00a8 }
        r0.o = r1;	 Catch:{ Exception -> 0x00a8 }
        r0 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.curWebview;	 Catch:{ Exception -> 0x00a8 }
        if (r0 == 0) goto L_0x0064;
    L_0x0044:
        r0 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.A;	 Catch:{ Exception -> 0x00a8 }
        r1 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r1 = r1.curWebview;	 Catch:{ Exception -> 0x00a8 }
        r1 = r1.getContentHeight();	 Catch:{ Exception -> 0x00a8 }
        r0.p = r1;	 Catch:{ Exception -> 0x00a8 }
        r0 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.A;	 Catch:{ Exception -> 0x00a8 }
        r1 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r1 = r1.curWebview;	 Catch:{ Exception -> 0x00a8 }
        r1 = r1.getProgress();	 Catch:{ Exception -> 0x00a8 }
        r0.q = r1;	 Catch:{ Exception -> 0x00a8 }
    L_0x0064:
        r0 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.A;	 Catch:{ Exception -> 0x00a8 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00a8 }
        r1 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r4 = r1.s;	 Catch:{ Exception -> 0x00a8 }
        r2 = r2 - r4;
        r0.u = r2;	 Catch:{ Exception -> 0x00a8 }
        r0 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.A;	 Catch:{ Exception -> 0x00a8 }
        r1 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r1 = r1.v;	 Catch:{ Exception -> 0x00a8 }
        r0.v = r1;	 Catch:{ Exception -> 0x00a8 }
        r0 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.A;	 Catch:{ Exception -> 0x00a8 }
        r1 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r1 = r1.e;	 Catch:{ Exception -> 0x00a8 }
        r0.x = r1;	 Catch:{ Exception -> 0x00a8 }
        r0 = r6.a;	 Catch:{ Exception -> 0x00a8 }
        r1 = 0;
        r0.d = r1;	 Catch:{ Exception -> 0x00a8 }
    L_0x0096:
        return;
    L_0x0097:
        r0 = r6.b;	 Catch:{ Exception -> 0x00a8 }
        r0 = r0.D;	 Catch:{ Exception -> 0x00a8 }
        r1 = com.baidu.mobads.AppActivity.o;	 Catch:{ Exception -> 0x00a8 }
        r2 = "App2Activity - not send 37";
        r0.i(r1, r2);	 Catch:{ Exception -> 0x00a8 }
        goto L_0x0096;
    L_0x00a8:
        r0 = move-exception;
        r1 = r6.b;
        r1 = r1.D;
        r2 = com.baidu.mobads.AppActivity.o;
        r0 = r0.getMessage();
        r1.d(r2, r0);
        goto L_0x0096;
        */
    }

    private void a(String str, int i) {
        if (this.b.q != null) {
            try {
                this.b.A.n = str;
                if (this.b.A.A == 0) {
                    this.b.A.A = System.currentTimeMillis();
                }
                this.b.A.o = AppActivity.i(this.b);
                this.b.A.v = this.b.v;
                this.b.A.x = this.b.e;
                if (this.b.curWebview != null) {
                    this.b.A.p = this.b.curWebview.getContentHeight();
                    this.b.A.q = this.b.curWebview.getProgress();
                }
                this.b.A.u = System.currentTimeMillis() - this.b.s;
                this.a.d = true;
            } catch (Exception e) {
                this.b.D.d(AppActivity.o, e.getMessage());
            }
        }
    }

    public void onLoadResource(WebView webView, String str) {
        try {
            if (!(this.a.c || str.equals(this.a.b) || this.a.a)) {
                this.a.c = true;
                if (this.b.g == -1) {
                    this.b.g = (int) (System.currentTimeMillis() - this.b.s);
                }
                a(this.a.b, 0);
            }
            HitTestResult hitTestResult = webView.getHitTestResult();
            if (hitTestResult != null && hitTestResult.getType() > 0) {
                this.b.a(webView, str, new l(this), null);
            }
        } catch (Exception e) {
            this.b.D.d(AppActivity.o, e.getMessage());
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        Builder builder = new Builder(this.b);
        builder.setMessage("ssl\u8bc1\u4e66\u9a8c\u8bc1\u5931\u8d25\uff0c\u662f\u5426\u7ee7\u7eed\u8bbf\u95ee\u8be5\u7f51\u9875\uff1f");
        builder.setPositiveButton("\u7ee7\u7eed", new m(this, sslErrorHandler));
        builder.setNegativeButton("\u53d6\u6d88", new n(this, sslErrorHandler));
        builder.setOnKeyListener(new o(this, sslErrorHandler));
        builder.create().show();
    }
}
