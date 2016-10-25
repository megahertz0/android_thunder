package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.utils.Utility;

class ShareWeiboWebViewClient extends WeiboWebViewClient {
    private static final String RESP_PARAM_CODE = "code";
    private static final String RESP_PARAM_MSG = "msg";
    private static final String RESP_SUCC_CODE = "0";
    private Activity mAct;
    private WeiboAuthListener mListener;
    private ShareRequestParam mShareRequestParam;

    public ShareWeiboWebViewClient(Activity activity, ShareRequestParam shareRequestParam) {
        this.mAct = activity;
        this.mShareRequestParam = shareRequestParam;
        this.mListener = shareRequestParam.getAuthListener();
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
        if (!str.startsWith(WeiboSdkBrowser.BROWSER_CLOSE_SCHEME)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        Bundle parseUri = Utility.parseUri(str);
        if (!(parseUri.isEmpty() || this.mListener == null)) {
            this.mListener.onComplete(parseUri);
        }
        CharSequence string = parseUri.getString(RESP_PARAM_CODE);
        String string2 = parseUri.getString(RESP_PARAM_MSG);
        if (TextUtils.isEmpty(string)) {
            this.mShareRequestParam.sendSdkCancleResponse(this.mAct);
        } else if (RESP_SUCC_CODE.equals(string)) {
            this.mShareRequestParam.sendSdkOkResponse(this.mAct);
        } else {
            this.mShareRequestParam.sendSdkErrorResponse(this.mAct, string2);
        }
        WeiboSdkBrowser.closeBrowser(this.mAct, this.mShareRequestParam.getAuthListenerKey(), null);
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
        this.mShareRequestParam.sendSdkErrorResponse(this.mAct, str);
        WeiboSdkBrowser.closeBrowser(this.mAct, this.mShareRequestParam.getAuthListenerKey(), null);
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (this.mCallBack != null) {
            this.mCallBack.onReceivedSslErrorCallBack(webView, sslErrorHandler, sslError);
        }
        sslErrorHandler.cancel();
        this.mShareRequestParam.sendSdkErrorResponse(this.mAct, "ReceivedSslError");
        WeiboSdkBrowser.closeBrowser(this.mAct, this.mShareRequestParam.getAuthListenerKey(), null);
    }
}
