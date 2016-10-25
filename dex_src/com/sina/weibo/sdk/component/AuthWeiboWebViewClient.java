package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.sina.weibo.sdk.utils.Utility;
import com.umeng.a;

class AuthWeiboWebViewClient extends WeiboWebViewClient {
    private boolean isCallBacked;
    private Activity mAct;
    private AuthRequestParam mAuthRequestParam;
    private WeiboAuthListener mListener;

    public AuthWeiboWebViewClient(Activity activity, AuthRequestParam authRequestParam) {
        this.isCallBacked = false;
        this.mAct = activity;
        this.mAuthRequestParam = authRequestParam;
        this.mListener = this.mAuthRequestParam.getAuthListener();
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.mCallBack != null) {
            this.mCallBack.onPageStartedCallBack(webView, str, bitmap);
        }
        if (!str.startsWith(this.mAuthRequestParam.getAuthInfo().getRedirectUrl()) || this.isCallBacked) {
            super.onPageStarted(webView, str, bitmap);
            return;
        }
        this.isCallBacked = true;
        handleRedirectUrl(str);
        webView.stopLoading();
        WeiboSdkBrowser.closeBrowser(this.mAct, this.mAuthRequestParam.getAuthListenerKey(), null);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.mCallBack != null) {
            this.mCallBack.shouldOverrideUrlLoadingCallBack(webView, str);
        }
        if (str.startsWith("sms:")) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.putExtra("address", str.replace("sms:", a.d));
            intent.setType("vnd.android-dir/mms-sms");
            this.mAct.startActivity(intent);
            return true;
        } else if (!str.startsWith(WeiboSdkBrowser.BROWSER_CLOSE_SCHEME)) {
            return super.shouldOverrideUrlLoading(webView, str);
        } else {
            if (this.mListener != null) {
                this.mListener.onCancel();
            }
            WeiboSdkBrowser.closeBrowser(this.mAct, this.mAuthRequestParam.getAuthListenerKey(), null);
            return true;
        }
    }

    public void onPageFinished(WebView webView, String str) {
        if (this.mCallBack != null) {
            this.mCallBack.onPageFinishedCallBack(webView, str);
        }
        super.onPageFinished(webView, str);
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        if (this.mCallBack != null) {
            this.mCallBack.onReceivedErrorCallBack(webView, i, str, str2);
        }
        super.onReceivedError(webView, i, str, str2);
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (this.mCallBack != null) {
            this.mCallBack.onReceivedSslErrorCallBack(webView, sslErrorHandler, sslError);
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    private void handleRedirectUrl(String str) {
        Bundle parseUrl = Utility.parseUrl(str);
        String string = parseUrl.getString("error");
        String string2 = parseUrl.getString("error_code");
        String string3 = parseUrl.getString("error_description");
        if (string == null && string2 == null) {
            if (this.mListener != null) {
                this.mListener.onComplete(parseUrl);
            }
        } else if (this.mListener != null) {
            this.mListener.onWeiboException(new WeiboAuthException(string2, string, string3));
        }
    }
}
