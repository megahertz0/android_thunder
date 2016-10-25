package com.xunlei.downloadprovider.web.record;

import com.xunlei.downloadprovider.model.b;
import com.xunlei.downloadprovider.model.i;
import java.util.ArrayList;

// compiled from: RecordServerUtils.java
final class af implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ aa b;

    af(aa aaVar, ArrayList arrayList) {
        this.b = aaVar;
        this.a = arrayList;
    }

    public final void run() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.a.size(); i++) {
            arrayList.add((b) ((t) this.a.get(i)).b);
        }
        this.a.clear();
        i.a().a(arrayList);
    }
}
