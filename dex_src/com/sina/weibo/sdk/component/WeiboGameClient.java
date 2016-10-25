package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.sina.weibo.sdk.utils.Utility;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.a;

class WeiboGameClient extends WeiboWebViewClient {
    private Activity mAct;
    private GameRequestParam mGameRequestParam;
    private WeiboAuthListener mListener;

    public WeiboGameClient(Activity activity, GameRequestParam gameRequestParam) {
        this.mAct = activity;
        this.mGameRequestParam = gameRequestParam;
        this.mListener = this.mGameRequestParam.getAuthListener();
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
        WeiboSdkBrowser.closeBrowser(this.mAct, this.mGameRequestParam.getAuthListenerKey(), null);
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

    private void handleRedirectUrl(String str) {
        Bundle parseUrl = Utility.parseUrl(str);
        String string = parseUrl.getString("error") == null ? a.d : parseUrl.getString("error");
        String string2 = parseUrl.getString(Constants.KEY_HTTP_CODE);
        String string3 = parseUrl.getString(SocialConstants.PARAM_SEND_MSG);
        if (string == null && string2 == null) {
            if (this.mListener != null) {
                this.mListener.onComplete(parseUrl);
            }
        } else if (this.mListener != null) {
            this.mListener.onWeiboException(new WeiboAuthException(string2, string, string3));
        }
    }
}
