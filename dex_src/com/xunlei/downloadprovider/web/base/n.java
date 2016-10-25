package com.xunlei.downloadprovider.web.base;

import com.xunlei.downloadprovider.a.g;

// compiled from: KandanListActivity.java
final class n implements Runnable {
    final /* synthetic */ KandanListActivity a;

    n(KandanListActivity kandanListActivity) {
        this.a = kandanListActivity;
    }

    public final void run() {
        this.a.t.getHeight();
        KandanListActivity.a;
        int height = this.a.t.getHeight() + g.a(this.a, 63.0f);
        this.a.s.getLayoutParams().height = height;
        this.a.B.getLayoutParams().height = height;
        this.a.q.requestLayout();
        this.a.A.setAlpha((((float) (this.a.x - 2)) * 1.0f) / ((float) (this.a.w - 2)));
    }
}
