package com.xunlei.downloadprovider.ad.common;

import android.os.Handler;
import java.util.ArrayList;

// compiled from: RequestTimeoutController.java
public final class d {
    public int a;
    int b;
    public ArrayList<a> c;
    public boolean d;
    public boolean e;
    Handler f;

    // compiled from: RequestTimeoutController.java
    public static interface a {
        void a();
    }

    public d(int i) {
        this.f = new e(this);
        this.b = i;
        this.a = i;
        this.c = new ArrayList();
    }

    public final void a(a aVar) {
        this.c.add(aVar);
    }

    public final void a() {
        this.f.sendEmptyMessageDelayed(-1, 1000);
    }
}
