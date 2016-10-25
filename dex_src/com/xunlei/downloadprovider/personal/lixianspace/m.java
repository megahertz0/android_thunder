package com.xunlei.downloadprovider.personal.lixianspace;

// compiled from: LixianSpaceFragment.java
final class m implements Runnable {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    public final void run() {
        if (LixianSpaceFragment.l(this.a.a) != LixianSpaceFragment$b.d) {
            LixianSpaceFragment.q(this.a.a).b();
            LixianSpaceFragment.p(this.a.a);
        }
    }
}
