package com.xunlei.downloadprovider.homepage.choiceness.ui;

import com.xunlei.downloadprovider.player.a.a;

// compiled from: HomeChoicenessFragment.java
final class af implements Runnable {
    final /* synthetic */ HomeChoicenessFragment a;

    af(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final void run() {
        a a = HomeChoicenessFragment.a(this.a);
        if (a.c()) {
            a.a(a.a, true, a.f);
        }
    }
}
