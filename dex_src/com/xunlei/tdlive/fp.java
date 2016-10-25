package com.xunlei.tdlive;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.tdlive.util.ac;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: WebBrowserActivity.java
class fp extends WebViewClient {
    final /* synthetic */ WebBrowserActivity a;

    fp(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPageStarted(android.webkit.WebView r4, java.lang.String r5, android.graphics.Bitmap r6) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.fp.onPageStarted(android.webkit.WebView, java.lang.String, android.graphics.Bitmap):void");
        /*
        this = this;
        if (r5 == 0) goto L_0x0031;
    L_0x0002:
        r0 = "tdlive://";
        r0 = r5.startsWith(r0);
        if (r0 != 0) goto L_0x0014;
    L_0x000b:
        r0 = "xllive://";
        r0 = r5.startsWith(r0);
        if (r0 == 0) goto L_0x0031;
    L_0x0014:
        r0 = android.net.Uri.parse(r5);	 Catch:{ Exception -> 0x0039 }
        r1 = "platformapi";
        r2 = r0.getHost();	 Catch:{ Exception -> 0x0039 }
        r1 = r1.equals(r2);	 Catch:{ Exception -> 0x0039 }
        if (r1 == 0) goto L_0x0032;
    L_0x0025:
        r1 = r3.a;	 Catch:{ Exception -> 0x0039 }
        r2 = 0;
        com.xunlei.tdlive.DispatcherActivity.a(r1, r0, r2);	 Catch:{ Exception -> 0x0039 }
    L_0x002b:
        r4.goBack();	 Catch:{ Exception -> 0x0039 }
        r4.stopLoading();	 Catch:{ Exception -> 0x0039 }
    L_0x0031:
        return;
    L_0x0032:
        r0 = r3.a;	 Catch:{ Exception -> 0x0039 }
        r1 = 0;
        com.xunlei.tdlive.DispatcherActivity.a(r0, r5, r1);	 Catch:{ Exception -> 0x0039 }
        goto L_0x002b;
    L_0x0039:
        r0 = move-exception;
        goto L_0x0031;
        */
    }

    public void onPageFinished(WebView webView, String str) {
        if (ac.a()) {
            this.a.setTitle(this.a.mWebView.getTitle());
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str == null) {
            return false;
        }
        webView.loadUrl(str);
        return true;
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        a();
    }

    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        a();
    }

    private void a() {
        if (!ac.j()) {
            if (ac.a()) {
                XLLiveSDK.getInstance(this.a).host().setErrorViewType(this.a, this.a.mErrorView, 1);
            } else {
                XLLiveSDK.getInstance(this.a).host().setErrorViewType(this.a, this.a.mErrorView, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            }
            this.a.mErrorView.setVisibility(0);
        }
    }
}
