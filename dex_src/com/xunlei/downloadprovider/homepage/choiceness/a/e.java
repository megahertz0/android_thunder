package com.xunlei.downloadprovider.homepage.choiceness.a;

import com.xunlei.downloadprovider.homepage.choiceness.a.a.b;
import java.util.List;

// compiled from: ChoicenessDataHelper.java
final class e implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ b b;

    e(b bVar, List list) {
        this.b = bVar;
        this.a = list;
    }

    public final void run() {
        if (this.b.b.k.isEmpty()) {
            a.b(this.b.b, this.a);
            a.a(this.b.b, this.b.b.d);
            this.b.b.a();
        }
        this.b.a.a(this.b.b.k);
    }
}
