package com.xunlei.downloadprovider.ad.recommend.a;

import com.xunlei.downloadprovider.ad.common.c;
import com.xunlei.downloadprovider.ad.recommend.a.i.a;
import java.util.Arrays;
import java.util.List;

// compiled from: RecommendAdModel.java
final class j implements a {
    final /* synthetic */ c.a a;
    final /* synthetic */ i b;

    j(i iVar, c.a aVar) {
        this.b = iVar;
        this.a = aVar;
    }

    public final void a(List<com.xunlei.downloadprovider.ad.common.a> list, int i, String str) {
        i.c();
        StringBuilder append = new StringBuilder("onLoadComplete pageIndex: ").append(i).append(" positionId: ").append(str).append(" result: ");
        String toString = (list == null || list.isEmpty()) ? "null" : Arrays.toString(list.toArray());
        append.append(toString);
        if (!(list == null || list.isEmpty())) {
            com.xunlei.downloadprovider.ad.common.a a = i.a(this.b, i, (List) list);
            i.c();
            new StringBuilder("uniqueData: ").append(a == null ? "null" : a.toString());
            if (a != null) {
                i.a(this.b, i, a);
            }
        }
        i.a(this.b, i, str);
        i.a(this.b, i, this.a);
    }

    public final void a(String str, int i, String str2) {
        i.c();
        new StringBuilder("onLoadFail positionId: ").append(str2).append(" errorInfo: ").append(str);
        i.a(this.b, i, str2);
        i.a(this.b, i, this.a);
    }
}
