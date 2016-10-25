package com.xunlei.downloadprovider.filemanager.model;

import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

// compiled from: FileManagerUtil.java
final class d extends Thread {
    final /* synthetic */ List a;
    final /* synthetic */ Handler b;

    d(List list, Handler handler) {
        this.a = list;
        this.b = handler;
    }

    public final void run() {
        int size = this.a.size();
        List arrayList = new ArrayList(size * 12);
        for (int i = 0; i < size; i++) {
            i iVar = (i) this.a.get(i);
            if (iVar.a) {
                b.a(iVar.c(), arrayList);
            }
        }
        b.c(arrayList, this.b);
    }
}
