package com.xunlei.downloadprovider.frame.user;

// compiled from: UserCenterFragment.java
final class bk implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ bj c;

    bk(bj bjVar, int i, int i2) {
        this.c = bjVar;
        this.a = i;
        this.b = i2;
    }

    public final void run() {
        UserCenterFragment.a(this.c.a, this.a, this.b);
    }
}
