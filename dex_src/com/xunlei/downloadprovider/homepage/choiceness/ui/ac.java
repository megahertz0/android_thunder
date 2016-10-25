package com.xunlei.downloadprovider.homepage.choiceness.ui;

import com.xunlei.downloadprovider.homepage.choiceness.a.a.b;
import java.util.List;

// compiled from: HomeChoicenessFragment.java
final class ac implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ b b;
    final /* synthetic */ ab c;

    ac(ab abVar, List list, b bVar) {
        this.c = abVar;
        this.a = list;
        this.b = bVar;
    }

    public final void run() {
        HomeChoicenessFragment.a(this.c.a.a, true, this.a, this.b);
    }
}
