package com.xunlei.tdlive;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

// compiled from: WebBrowserActivity.java
class fq implements DownloadListener {
    final /* synthetic */ WebBrowserActivity a;

    fq(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
        }
    }
}
