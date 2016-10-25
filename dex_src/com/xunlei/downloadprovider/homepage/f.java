package com.xunlei.downloadprovider.homepage;

// compiled from: HomeFragment.java
final class f implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ HomeFragment b;

    f(HomeFragment homeFragment, int i) {
        this.b = homeFragment;
        this.a = i;
    }

    public final void run() {
        this.b.a.setCurrentTab(this.a);
    }
}
