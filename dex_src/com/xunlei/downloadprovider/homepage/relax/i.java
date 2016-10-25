package com.xunlei.downloadprovider.homepage.relax;

import com.tencent.tauth.Tencent;
import com.xunlei.downloadprovider.homepage.relax.c.a.a;

// compiled from: RelaxPicBrowseActivity.java
final class i implements a {
    final /* synthetic */ RelaxPicBrowseActivity a;

    i(RelaxPicBrowseActivity relaxPicBrowseActivity) {
        this.a = relaxPicBrowseActivity;
    }

    public final void a() {
        if (this.a.k != null) {
            this.a.k.removeMessages(10002);
            this.a.k.sendEmptyMessage(10002);
            this.a.k = null;
        }
    }

    public final void a(String str) {
        if (this.a.k != null) {
            this.a.k.removeMessages(Tencent.REQUEST_LOGIN);
            this.a.k.obtainMessage(Tencent.REQUEST_LOGIN, str).sendToTarget();
            this.a.k = null;
        }
    }
}
