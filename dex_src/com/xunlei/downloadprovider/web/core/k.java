package com.xunlei.downloadprovider.web.core;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.DownloadListener;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.url.DownData;

// compiled from: ThunderWebView.java
final class k implements DownloadListener {
    final /* synthetic */ ThunderWebView a;

    k(ThunderWebView thunderWebView) {
        this.a = thunderWebView;
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        if (!TextUtils.isEmpty(str) && str.endsWith("?jump=sjxlmp4")) {
            String substring = str.substring(0, str.indexOf("?jump=sjxlmp4"));
            if (!substring.isEmpty()) {
                Uri parse = Uri.parse(substring);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(parse, "video/*");
                this.a.o.startActivity(intent);
                return;
            }
        }
        if (this.a.f != null) {
            if (this.a.a.getTitle() != null) {
                this.a.f.b(this.a.a, this.a.a.getTitle());
            }
            ThunderWebView.a(this.a, new DownData(null, str, this.a.r));
            StatReporter.reportOverallDownload("browser");
        }
    }
}
