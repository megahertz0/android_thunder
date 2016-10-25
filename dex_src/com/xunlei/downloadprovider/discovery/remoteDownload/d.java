package com.xunlei.downloadprovider.discovery.remoteDownload;

import android.text.TextUtils;
import com.xunlei.downloadprovider.member.login.LoginHelper.c;

// compiled from: RemoteDownloadActivity.java
final class d implements c {
    final /* synthetic */ RemoteDownloadActivity a;

    d(RemoteDownloadActivity remoteDownloadActivity) {
        this.a = remoteDownloadActivity;
    }

    public final void a(int i) {
        this.a.d = true;
        if (i == 0) {
            this.a.e = true;
            if (TextUtils.isEmpty(this.a.f)) {
                RemoteDownloadActivity.b.a("http://wap.yuancheng.xunlei.com/index.html");
                return;
            }
            RemoteDownloadActivity.b.a(RemoteDownloadActivity.b(this.a.f));
            return;
        }
        this.a.e = false;
    }
}
