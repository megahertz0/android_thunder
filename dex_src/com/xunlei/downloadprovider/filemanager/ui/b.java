package com.xunlei.downloadprovider.filemanager.ui;

import java.util.ArrayList;
import java.util.List;

// compiled from: FileDetailsWindow.java
public final class b extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    public b(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    public final void run() {
        List arrayList = new ArrayList();
        com.xunlei.downloadprovider.filemanager.model.b.a(this.a, arrayList);
        a.a(this.b).obtainMessage(a.a(), arrayList).sendToTarget();
    }
}
