package com.xunlei.downloadprovider.vod;

import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;

// compiled from: VodPlayerActivity.java
final class r implements d {
    final /* synthetic */ long a;
    final /* synthetic */ VodPlayerActivity b;

    r(VodPlayerActivity vodPlayerActivity, long j) {
        this.b = vodPlayerActivity;
        this.a = j;
    }

    public final void a(int i, int i2, boolean z, Object obj) {
        if (i == 0 && i2 == 0 && LoginHelper.a().f()) {
            com.xunlei.downloadprovider.service.downloads.task.d.a();
            com.xunlei.downloadprovider.service.downloads.task.d.a(this.a);
        }
    }
}
