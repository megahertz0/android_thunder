package com.xunlei.downloadprovider.web.browser;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.DownloadListener;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.url.DownData;

// compiled from: BrowserActivity.java
final class a implements DownloadListener {
    final /* synthetic */ BrowserActivity a;

    a(BrowserActivity browserActivity) {
        this.a = browserActivity;
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        if (!TextUtils.isEmpty(str) && str.endsWith("?jump=sjxlmp4")) {
            String substring = str.substring(0, str.indexOf("?jump=sjxlmp4"));
            if (!substring.isEmpty()) {
                Uri parse = Uri.parse(substring);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(parse, "video/*");
                this.a.startActivity(intent);
            }
        }
        this.a.b(new DownData(null, str, BrowserActivity.a(this.a).g()), "browser/browser");
        StatReporter.reportOverallDownload("browser");
    }
}
