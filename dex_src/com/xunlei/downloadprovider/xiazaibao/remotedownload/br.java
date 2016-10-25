package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.webkit.WebView;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.XZBWebviewActivity.DownloadTaskListJsInterface;

// compiled from: XZBWebviewActivity.java
final class br implements Runnable {
    final /* synthetic */ DownloadTaskListJsInterface a;

    br(DownloadTaskListJsInterface downloadTaskListJsInterface) {
        this.a = downloadTaskListJsInterface;
    }

    public final void run() {
        WebView webView = this.a.this$0.a;
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            this.a.this$0.finish();
        }
    }
}
