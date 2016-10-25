package com.xunlei.downloadprovider.web.browser.a;

import android.webkit.DownloadListener;

// compiled from: XLWebDownloadListener.java
public class b implements DownloadListener {
    public DownloadListener a;

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        if (this.a != null) {
            this.a.onDownloadStart(str, str2, str3, str4, j);
        }
    }
}
