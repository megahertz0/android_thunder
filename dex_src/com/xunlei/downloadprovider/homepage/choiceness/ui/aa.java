package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.widget.AbsListView;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.b;
import com.xunlei.downloadprovider.player.a.a;
import java.util.List;

// compiled from: HomeChoicenessFragment.java
final class aa implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ b b;
    final /* synthetic */ z c;

    aa(z zVar, List list, b bVar) {
        this.c = zVar;
        this.a = list;
        this.b = bVar;
    }

    public final void run() {
        HomeChoicenessFragment.a(this.c.a.a, false, this.a, this.b);
        HomeChoicenessFragment.a(this.c.a.a);
        a.a((AbsListView) HomeChoicenessFragment.g(this.c.a.a).getRefreshableView());
        HomeChoicenessFragment.i(this.c.a.a);
    }
}
