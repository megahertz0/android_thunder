package com.xunlei.common.member.act;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xunlei.common.base.XLLog;

class XLQRCodeLoginAuthActivity$WebViewTask extends AsyncTask<Void, Void, Boolean> {
    CookieManager cookieManager;
    final /* synthetic */ XLQRCodeLoginAuthActivity this$0;

    private XLQRCodeLoginAuthActivity$WebViewTask(XLQRCodeLoginAuthActivity xLQRCodeLoginAuthActivity) {
        this.this$0 = xLQRCodeLoginAuthActivity;
        this.cookieManager = null;
    }

    protected void onPreExecute() {
        CookieSyncManager.createInstance(this.this$0);
        this.cookieManager = CookieManager.getInstance();
        this.cookieManager.setAcceptCookie(true);
    }

    protected Boolean doInBackground(Void... voidArr) {
        SystemClock.sleep(100);
        return Boolean.valueOf(false);
    }

    protected void onPostExecute(Boolean bool) {
        XLQRCodeLoginAuthActivity.access$500(this.this$0, this.cookieManager, XLQRCodeLoginAuthActivity.access$300(this.this$0), XLQRCodeLoginAuthActivity.access$400(this.this$0));
        WebSettings settings = this.this$0.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        this.this$0.mWebView.clearCache(true);
        XLQRCodeLoginAuthActivity.access$600(this.this$0, this.this$0.mWebView);
        this.this$0.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return XLQRCodeLoginAuthActivity$WebViewTask.this.this$0.webViewPreLoadUrl(webView, str);
            }

            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
            }
        });
        this.this$0.mWebView.loadUrl(XLQRCodeLoginAuthActivity.access$300(this.this$0));
        XLLog.v(XLQRCodeLoginAuthActivity.access$200(this.this$0), new StringBuilder("load remote login url = ").append(XLQRCodeLoginAuthActivity.access$300(this.this$0)).toString());
    }
}
