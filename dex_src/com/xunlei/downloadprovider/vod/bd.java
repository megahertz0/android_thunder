package com.xunlei.downloadprovider.vod;

import android.content.Context;
import android.os.Bundle;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivityFragment;
import com.xunlei.downloadprovider.member.login.LoginHelper.c;

// compiled from: VodUtil.java
final class bd implements c {
    final /* synthetic */ Context a;
    final /* synthetic */ ap b;
    final /* synthetic */ Bundle c;

    bd(Context context, ap apVar, Bundle bundle) {
        this.a = context;
        this.b = apVar;
        this.c = bundle;
    }

    public final void a(int i) {
        if (i == 0 && DownloadCenterActivityFragment.a) {
            VodUtil.c(this.a, this.b, this.c);
        }
    }
}
