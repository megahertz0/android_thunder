package com.xunlei.downloadprovider.filemanager.a;

import android.os.Handler;
import com.xunlei.downloadprovider.filemanager.model.a;

// compiled from: FileByTypeAndParentLoader.java
public final class b extends Thread {
    final /* synthetic */ Handler a;
    final /* synthetic */ a b;

    public b(a aVar, Handler handler) {
        this.b = aVar;
        this.a = handler;
    }

    public final void run() {
        this.a.obtainMessage(c.a, a.a().a(this.b.b, this.b.c)).sendToTarget();
    }
}
