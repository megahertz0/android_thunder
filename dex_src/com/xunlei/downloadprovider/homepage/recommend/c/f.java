package com.xunlei.downloadprovider.homepage.recommend.c;

import com.xunlei.downloadprovider.homepage.recommend.feed.o;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;

// compiled from: ClickNiceRecordHelper.java
final class f implements Runnable {
    final /* synthetic */ c a;

    f(c cVar) {
        this.a = cVar;
    }

    public final void run() {
        c a = c.a();
        List arrayList = new ArrayList();
        List<b> b = a.a.b();
        List list;
        if (b != null) {
            for (b bVar : b) {
                arrayList.add(bVar.d);
            }
            list = arrayList;
        } else {
            list = null;
        }
        if (r0 != null) {
            for (String str : r0) {
                o.a().a(str, BuildConfig.VERSION_NAME);
            }
        }
    }
}
