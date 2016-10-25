package com.xunlei.downloadprovider.filemanager.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// compiled from: FileManagerUtil.java
final class g extends Thread {
    final /* synthetic */ List a;

    g(List list) {
        this.a = list;
    }

    public final void run() {
        List arrayList = new ArrayList();
        for (int i = 0; i < this.a.size(); i++) {
            String str = (String) this.a.get(i);
            if (new File(str).isDirectory()) {
                b.a(str, arrayList);
            } else {
                i iVar = new i();
                iVar.a(str);
                arrayList.add(iVar);
            }
        }
        b.b(arrayList);
    }
}
