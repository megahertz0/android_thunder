package com.xunlei.downloadprovider.web.record;

import com.xunlei.downloadprovider.web.record.aa.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

// compiled from: RecordPageView.java
final class x implements b {
    final /* synthetic */ RecordPageView a;

    x(RecordPageView recordPageView) {
        this.a = recordPageView;
    }

    public final void a(ArrayList<t> arrayList, int i, long j, boolean z) {
        if (!RecordPageView.k(this.a)) {
            if (i == 2) {
                RecordPageView.f(this.a).clear();
                RecordPageView.f(this.a).addAll(arrayList);
                RecordPageView.g(this.a);
                RecordPageView.l(this.a).a(j, false, i);
            } else if (i == 4) {
                int i2;
                List<t> arrayList2 = new ArrayList();
                for (i2 = 0; i2 < arrayList.size(); i2++) {
                    arrayList2.add(arrayList.get(i2));
                }
                for (i2 = 0; i2 < RecordPageView.f(this.a).size(); i2++) {
                    arrayList2.add(RecordPageView.f(this.a).get(i2));
                }
                if (arrayList2.size() > 100) {
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        it.next();
                        if (arrayList2.size() - 100 > 0) {
                            it.remove();
                        }
                    }
                }
                HashSet hashSet = new HashSet();
                Object arrayList3 = new ArrayList();
                for (t tVar : arrayList2) {
                    if (hashSet.add(tVar)) {
                        arrayList3.add(tVar);
                    }
                }
                arrayList2.clear();
                if (arrayList3.size() > 0) {
                    RecordPageView.f(this.a).clear();
                    RecordPageView.f(this.a).addAll(arrayList3);
                    arrayList3.clear();
                    RecordPageView.g(this.a);
                }
                RecordPageView.l(this.a).a(j, false, i);
            } else if (i == 1) {
                RecordPageView.l(this.a).a(j, z, i);
            } else if (i == 3) {
                RecordPageView.l(this.a).a(j, false, i);
            }
        }
    }

    public final void a(int i) {
        if (!RecordPageView.k(this.a)) {
            RecordPageView.l(this.a).a(i);
        }
    }
}
