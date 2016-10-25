package com.xunlei.downloadprovider.frame;

import android.app.Activity;
import com.xunlei.downloadprovider.i.a.c;

// compiled from: MainTabActivity.java
final class x implements Runnable {
    final /* synthetic */ MainTabActivity a;

    x(MainTabActivity mainTabActivity) {
        this.a = mainTabActivity;
    }

    public final void run() {
        String str = MainTabActivity.a;
        c.a((Activity) this.a, false);
    }
}
