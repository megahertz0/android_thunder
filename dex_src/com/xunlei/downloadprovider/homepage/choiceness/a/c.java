package com.xunlei.downloadprovider.homepage.choiceness.a;

import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;
import com.xunlei.downloadprovider.search.b.b;
import java.util.List;

// compiled from: ChoicenessDataHelper.java
public final class c implements b<com.xunlei.downloadprovider.homepage.choiceness.a.a.b> {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    public c(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public final /* synthetic */ void a(Object obj) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.b bVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.b) obj;
        if (bVar != null) {
            List list = bVar.c;
            if (list != null) {
                new StringBuilder("getNextPageData onDataResponse--size=").append(list.size());
            }
            a.a(this.b, bVar.e);
            a.b(this.b, bVar);
            a.a(this.b, list);
            if (!(list == null || list.isEmpty())) {
                this.b.c();
            }
        }
        this.b.a();
        this.a.a(this.b.k, bVar);
    }
}
