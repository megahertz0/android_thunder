package com.xunlei.downloadprovider.service.downloads.task.a;

import android.os.Handler.Callback;
import android.os.Message;

// compiled from: MessageThread.java
final class l implements Callback {
    final /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    public final boolean handleMessage(Message message) {
        try {
            return this.a.c != null && this.a.c.handleMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
