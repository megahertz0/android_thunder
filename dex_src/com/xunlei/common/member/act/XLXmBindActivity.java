package com.xunlei.common.member.act;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.sys.a;
import com.xunlei.common.member.XLErrorCode;

public class XLXmBindActivity extends Activity {
    protected WebView mWebView;

    final class XLWebChromeClient extends WebChromeClient {
        XLWebChromeClient() {
        }

        public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            new Builder(XLXmBindActivity.this).setTitle("\u6765\u81ea\u7f51\u9875\u7684\u63d0\u793a").setMessage(str2.toString()).setPositiveButton(17039370, new AnonymousClass_1(this, jsResult)).setCancelable(false).create().show();
            return true;
        }
    }

    public XLXmBindActivity() {
        this.mWebView = null;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mWebView = new WebView(this);
        setContentView(this.mWebView);
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        this.mWebView.setWebChromeClient(new XLWebChromeClient());
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                XLXmBindActivity.this.webViewPreLoadUrl(str);
                return true;
            }

            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();
            }
        });
        String stringExtra = getIntent().getStringExtra("xl_bind_url");
        clearWebViewCache();
        this.mWebView.loadUrl(stringExtra);
    }

    private void clearWebViewCache() {
        CookieSyncManager.createInstance(getApplicationContext());
        CookieManager.getInstance().removeAllCookie();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void reply(int i, String str, String str2, String str3) {
        Intent intent = new Intent();
        if (i == 0) {
            intent.putExtra("xl_bind_tid", str);
            intent.putExtra("xl_bind_ttoken", str2);
            intent.putExtra("xl_bind_tsign", str3);
        }
        setResult(i, intent);
    }

    protected boolean webViewPreLoadUrl(String str) {
        if (str.indexOf("result=") != -1) {
            extractXiaoMiToken(str);
        }
        return false;
    }

    public String extractSingle(String str, String str2) {
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return null;
        }
        indexOf += str2.length();
        int indexOf2 = str.indexOf(a.b, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        return str.substring(indexOf, indexOf2);
    }

    public void extractXiaoMiToken(String str) {
        String extractSingle = extractSingle(str, "third_id=");
        if (extractSingle != null) {
            String extractSingle2 = extractSingle(str, "sign=");
            if (extractSingle2 != null) {
                String extractSingle3 = extractSingle(str, "mi_token=");
                if (extractSingle3 != null) {
                    reply(0, extractSingle, extractSingle3, extractSingle2);
                    finish();
                    return;
                }
            }
        }
        reply(XLErrorCode.AUTH_USER_ERROR, null, null, null);
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            return true;
        }
        reply(XLErrorCode.AUTH_USER_CANCLE, null, null, null);
        return super.onKeyDown(i, keyEvent);
    }
}
