package com.xunlei.downloadprovider.download.tasklist.a;

import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import java.util.Comparator;

// compiled from: TaskDataSource.java
final class c implements Comparator<e> {
    c() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        e eVar = (e) obj;
        e eVar2 = (e) obj2;
        if (eVar == eVar2) {
            return 0;
        }
        long j = eVar.b().mCreateTime;
        long j2 = eVar2.b().mCreateTime;
        if (j != j2) {
            return j > j2 ? -1 : 1;
        } else {
            return 0;
        }
    }
}
