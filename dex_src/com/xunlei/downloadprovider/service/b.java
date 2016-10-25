package com.xunlei.downloadprovider.service;

import android.os.Handler;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianTask;

// compiled from: DownloadEngine.java
public final class b extends XLLixianListener {
    final /* synthetic */ Handler a;
    final /* synthetic */ DownloadEngine b;

    public b(DownloadEngine downloadEngine, Handler handler) {
        this.b = downloadEngine;
        this.a = handler;
    }

    public final boolean OnCreateLixianTask(int i, String str, int i2, XLLixianTask xLLixianTask, Object obj) {
        if (i != 0) {
            this.a.obtainMessage(117).sendToTarget();
            return true;
        }
        this.a.obtainMessage(116).sendToTarget();
        return false;
    }
}
