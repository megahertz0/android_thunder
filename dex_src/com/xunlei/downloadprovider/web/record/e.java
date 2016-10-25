package com.xunlei.downloadprovider.web.record;

import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: FavorAndHistroyActivity.java
final class e implements a {
    final /* synthetic */ FavorAndHistroyActivity a;

    e(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void a(boolean z) {
        this.a.i = z;
        this.a.c();
        this.a.b(true);
        if (this.a.h.getTabType().equals("favor") && this.a.t != null) {
            this.a.t.setVisibility(XZBDevice.Wait);
        }
    }

    public final void a(String str) {
        StartFromType startFromType = StartFromType.unknow;
        if (this.a.h == this.a.f) {
            startFromType = StartFromType.favorite;
        } else if (this.a.h == this.a.g) {
            startFromType = StartFromType.browser_history;
        }
        BrowserUtil.a();
        BrowserUtil.a(this.a, str, false, startFromType);
    }

    public final void a(int i) {
        FavorAndHistroyActivity.a(this.a, i);
    }

    public final void a() {
        if (this.a.h.d()) {
            this.a.a(this.a.getResources().getString(2131230861));
        } else {
            this.a.a(this.a.getResources().getString(2131230862));
        }
        this.a.d();
        FavorAndHistroyActivity.b(this.a, this.a.h.getSelectedSize() > 0);
    }
}
