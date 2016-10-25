package com.xunlei.downloadprovider.homepage.choiceness.ui;

import com.xunlei.downloadprovider.search.b.a;
import java.util.List;

// compiled from: HomeChoicenessFragment.java
final class q implements a<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> {
    final /* synthetic */ HomeChoicenessFragment a;

    q(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final void a(List<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> list) {
        if (this.a.a.isEmpty()) {
            this.a.a.a(list);
            if (!this.a.a.isEmpty()) {
                this.a.g();
                this.a.f();
            }
            if (this.a.a.isEmpty()) {
                this.a.a();
            } else {
                this.a.i.postDelayed(new af(this.a), 500);
            }
        }
    }
}
