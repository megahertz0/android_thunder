package com.xunlei.downloadprovider.service.downloads.task.a;

import android.os.Handler;
import android.os.Message;
import com.xunlei.downloadprovider.service.downloads.task.a.g.a;

// compiled from: DownloadCore.java
final class i extends Handler {
    final /* synthetic */ a a;

    i(a aVar) {
        this.a = aVar;
    }

    public final void handleMessage(Message message) {
        new StringBuilder("start :").append(message.toString());
        this.a.a(message);
    }
}
