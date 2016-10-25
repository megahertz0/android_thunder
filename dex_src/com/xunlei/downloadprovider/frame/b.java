package com.xunlei.downloadprovider.frame;

// compiled from: BaseViewPagerFragment.java
final class b implements Runnable {
    final /* synthetic */ BaseViewPagerFragment a;

    b(BaseViewPagerFragment baseViewPagerFragment) {
        this.a = baseViewPagerFragment;
    }

    public final void run() {
        if (BaseViewPagerFragment.a(this.a) != null) {
            BaseViewPagerFragment.a(this.a).setOffscreenPageLimit(this.a.c().length);
        }
    }
}
