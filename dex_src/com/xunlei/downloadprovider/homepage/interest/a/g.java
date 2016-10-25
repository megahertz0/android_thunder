package com.xunlei.downloadprovider.homepage.interest.a;

import com.xunlei.downloadprovider.search.b.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// compiled from: InterestNetworkHelper.java
final class g implements b<i> {
    final /* synthetic */ List a;
    final /* synthetic */ f b;

    g(f fVar, List list) {
        this.b = fVar;
        this.a = list;
    }

    public final /* synthetic */ void a(Object obj) {
        i iVar = (i) obj;
        if (iVar != null) {
            List<Integer> list = iVar.a;
            if (this.a.size() != 0 || iVar.a.size() != 0) {
                this.b.b.c();
                if (list.size() == this.a.size()) {
                    int i = 0;
                    while (i < list.size() && list.get(i) == this.a.get(i)) {
                        i++;
                    }
                }
                Set<Object> treeSet = new TreeSet(this.a);
                for (Integer num : list) {
                    treeSet.add(num);
                }
                List arrayList = new ArrayList();
                for (Object obj2 : treeSet) {
                    arrayList.add(obj2);
                }
                this.b.c.a(this.b.a, arrayList, new h(this));
            }
        }
    }
}
