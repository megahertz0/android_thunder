package com.xunlei.downloadprovider.homepage.choiceness.a;

import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;
import java.util.List;

// compiled from: ChoicenessDataHelper.java
public final class b implements com.xunlei.downloadprovider.search.b.b<com.xunlei.downloadprovider.homepage.choiceness.a.a.b> {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    public b(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public final /* synthetic */ void a(Object obj) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.b bVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.b) obj;
        if (bVar != null) {
            List list = bVar.c;
            if (list != null) {
                new StringBuilder("refreshDataFromServer onDataResponse--size=").append(list.size());
            }
            a.a(this.b, bVar.e);
            a.a(this.b, bVar);
            a.a(this.b, list);
            if (!(list == null || list.isEmpty())) {
                this.b.c();
            }
        }
        this.b.a();
        this.a.a(this.b.k, bVar);
    }
}
