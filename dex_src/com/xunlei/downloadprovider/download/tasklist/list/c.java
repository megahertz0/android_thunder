package com.xunlei.downloadprovider.download.tasklist.list;

import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import java.util.ArrayList;
import java.util.Collection;

// compiled from: TaskListAdapter.java
public final class c implements Runnable {
    final /* synthetic */ a a;

    public c(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        Collection arrayList = new ArrayList();
        if (this.a.a != null) {
            for (int i = 0; i < this.a.a.size() - 1; i++) {
                e eVar = (e) this.a.a.get(i);
                e eVar2 = (e) this.a.a.get(i + 1);
                if (eVar.a == 101 || eVar.a == 100) {
                    if (eVar2.a == 101 || eVar2.a == 100) {
                        arrayList.add(eVar);
                    }
                }
            }
            if (arrayList.size() != 0) {
                this.a.a.removeAll(arrayList);
                this.a.notifyDataSetChanged();
            }
        }
    }
}
