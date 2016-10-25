package com.xunlei.common.accelerator.js;

import android.webkit.WebView;

public class KNJsCallBack {
    public static void callBack(WebView webView, String str, String str2) {
        if (str2 != null) {
            webView.loadUrl(new StringBuilder("javascript: ").append(str2).append("('").append(str).append("')").toString());
        }
    }
}
