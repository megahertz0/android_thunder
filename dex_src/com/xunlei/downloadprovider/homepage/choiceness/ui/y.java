package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.widget.ListView;
import com.android.volley.f;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.e;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.a.a;
import com.xunlei.downloadprovider.homepage.choiceness.a.b;
import com.xunlei.downloadprovider.homepage.choiceness.a.c;
import com.xunlei.downloadprovider.homepage.choiceness.a.h;
import com.xunlei.downloadprovider.homepage.choiceness.a.i;
import com.xunlei.downloadprovider.homepage.choiceness.a.j;
import com.xunlei.downloadprovider.homepage.choiceness.a.k;
import com.xunlei.downloadprovider.homepage.choiceness.a.l;

// compiled from: HomeChoicenessFragment.java
final class y implements e<ListView> {
    final /* synthetic */ HomeChoicenessFragment a;

    y(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        ChoicenessReporter.a(this.a.n);
        a k = this.a.h;
        a.a zVar = new z(this);
        new StringBuilder("refreshDataFromServer--mMaxSortId=").append(k.b);
        if (k.a(zVar)) {
            h hVar = k.a;
            long j = k.b;
            long b = k.b();
            b bVar = new b(k, zVar);
            com.xunlei.downloadprovidercommon.b.a.a aVar = new com.xunlei.downloadprovidercommon.b.a.a(h.a(new StringBuilder("http://api-shoulei-ssl.xunlei.com/homepage/api/refreshpage?id=").append(j).toString(), b), new i(hVar, bVar), new j(hVar, bVar), (byte) 0);
            aVar.setShouldCache(false);
            aVar.setRetryPolicy(new f(10000, 1, 1.0f));
            hVar.a(aVar);
        }
        HomeChoicenessFragment.d();
    }

    public final void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        ChoicenessReporter.c();
        a k = this.a.h;
        a.a abVar = new ab(this);
        new StringBuilder("getNextPageData--mMinSortId=").append(k.c);
        if (k.a(abVar)) {
            long j = k.c;
            if (j == Long.MAX_VALUE) {
                j = 0;
            }
            h hVar = k.a;
            long b = k.b();
            c cVar = new c(k, abVar);
            com.xunlei.downloadprovidercommon.b.a.a aVar = new com.xunlei.downloadprovidercommon.b.a.a(h.a(new StringBuilder("http://api-shoulei-ssl.xunlei.com/homepage/api/nextpage?id=").append(j).toString(), b), new k(hVar, cVar), new l(hVar, cVar), (byte) 0);
            aVar.setShouldCache(false);
            aVar.setRetryPolicy(new f(5000, 1, 1.0f));
            hVar.a(aVar);
        }
        HomeChoicenessFragment.d();
    }
}
