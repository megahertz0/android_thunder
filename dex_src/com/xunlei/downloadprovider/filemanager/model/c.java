package com.xunlei.downloadprovider.filemanager.model;

import android.os.Handler;
import java.util.List;

// compiled from: FileManagerUtil.java
final class c extends Thread {
    final /* synthetic */ List a;
    final /* synthetic */ Handler b;

    c(List list, Handler handler) {
        this.a = list;
        this.b = handler;
    }

    public final void run() {
        b.c(this.a, this.b);
    }
}
