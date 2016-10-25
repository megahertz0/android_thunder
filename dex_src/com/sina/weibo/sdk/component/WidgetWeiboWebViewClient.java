package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.component.WidgetRequestParam.WidgetRequestCallback;
import com.sina.weibo.sdk.utils.Utility;

class WidgetWeiboWebViewClient extends WeiboWebViewClient {
    private Activity mAct;
    private WeiboAuthListener mListener;
    private WidgetRequestCallback mWidgetCallback;
    private WidgetRequestParam mWidgetRequestParam;

    public WidgetWeiboWebViewClient(Activity activity, WidgetRequestParam widgetRequestParam) {
        this.mAct = activity;
        this.mWidgetRequestParam = widgetRequestParam;
        this.mWidgetCallback = widgetRequestParam.getWidgetRequestCallback();
        this.mListener = widgetRequestParam.getAuthListener();
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.mCallBack != null) {
            this.mCallBack.onPageStartedCallBack(webView, str, bitmap);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.mCallBack != null) {
            this.mCallBack.shouldOverrideUrlLoadingCallBack(webView, str);
        }
        boolean startsWith = str.startsWith(WeiboSdkBrowser.BROWSER_CLOSE_SCHEME);
        if (!str.startsWith(WeiboSdkBrowser.BROWSER_CLOSE_SCHEME) && !str.startsWith(WeiboSdkBrowser.BROWSER_WIDGET_SCHEME)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        Bundle parseUri = Utility.parseUri(str);
        if (!(parseUri.isEmpty() || this.mListener == null)) {
            this.mListener.onComplete(parseUri);
        }
        if (this.mWidgetCallback != null) {
            this.mWidgetCallback.onWebViewResult(str);
        }
        if (startsWith) {
            WeiboSdkBrowser.closeBrowser(this.mAct, this.mWidgetRequestParam.getAuthListenerKey(), this.mWidgetRequestParam.getWidgetRequestCallbackKey());
        }
        return true;
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
}
