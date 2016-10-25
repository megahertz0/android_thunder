package com.xunlei.downloadprovider.filemanager.model;

import android.os.Handler;
import com.xunlei.downloadprovider.filemanager.ui.g.a;

// compiled from: FileManagerUtil.java
final class f extends Thread {
    final /* synthetic */ a a;
    final /* synthetic */ Handler b;

    f(a aVar, Handler handler) {
        this.a = aVar;
        this.b = handler;
    }

    public final void run() {
        this.a.a = b.c(this.a, this.b);
        this.b.obtainMessage(b.d, this.a).sendToTarget();
    }
}
