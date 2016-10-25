package com.xunlei.common.accelerator.js;

import android.webkit.WebView;
import com.xunlei.common.accelerator.XLOnAccelListener;

public class XLOnAccelListenerImpl implements XLOnAccelListener {
    private String mCallBackName;
    private WebView mWebView;

    public XLOnAccelListenerImpl(WebView webView) {
        this.mWebView = webView;
    }

    public void setCallBackName(String str) {
        this.mCallBackName = str;
    }

    public void callBack(int i, int i2, String str) {
        KNJsCallBack.callBack(this.mWebView, KNJsBuildJson.callBackJsonBuild(i, i2, str), this.mCallBackName);
    }
}
