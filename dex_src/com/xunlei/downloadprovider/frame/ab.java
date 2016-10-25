package com.xunlei.downloadprovider.frame;

// compiled from: MainTabActivity.java
final class ab implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ MainTabActivity b;

    ab(MainTabActivity mainTabActivity, boolean z) {
        this.b = mainTabActivity;
        this.a = z;
    }

    public final void run() {
        if (this.a && MainTabActivity.c(this.b) && !MainTabActivity.d(this.b)) {
            this.b.a(true);
        }
    }
}
