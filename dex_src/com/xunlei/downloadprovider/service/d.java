package com.xunlei.downloadprovider.service;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.service.DownloadService.a;

// compiled from: DownloadService.java
final class d implements ServiceConnection {
    static final /* synthetic */ boolean a;

    static {
        a = !DownloadService.class.desiredAssertionStatus();
    }

    d() {
    }

    @SuppressLint({"Assert"})
    public final void onServiceDisconnected(ComponentName componentName) {
        if (!a) {
            throw new AssertionError();
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        DownloadService.h = ((a) iBinder).a.a;
        DownloadService.h.e = new b(DownloadService.h.g);
        DownloadService.h.a(DownloadService.h.e);
    }
}
