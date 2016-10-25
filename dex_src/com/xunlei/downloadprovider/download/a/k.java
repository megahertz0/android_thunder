package com.xunlei.downloadprovider.download.a;

import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;

// compiled from: DownloadCenterControl.java
final class k implements d {
    final /* synthetic */ long a;
    final /* synthetic */ a b;

    k(a aVar, long j) {
        this.b = aVar;
        this.a = j;
    }

    public final void a(int i, int i2, boolean z, Object obj) {
        if (i == 0 && i2 == 0 && LoginHelper.a().f()) {
            com.xunlei.downloadprovider.service.downloads.task.d.a();
            com.xunlei.downloadprovider.service.downloads.task.d.a(this.a);
        }
    }
}
