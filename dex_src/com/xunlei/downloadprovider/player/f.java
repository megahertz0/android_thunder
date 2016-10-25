package com.xunlei.downloadprovider.player;

import android.os.Handler;
import android.os.Message;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: MediaPlayerControllerView.java
final class f extends Handler {
    final /* synthetic */ MediaPlayerControllerView a;

    f(MediaPlayerControllerView mediaPlayerControllerView) {
        this.a = mediaPlayerControllerView;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case SimpleLog.LOG_LEVEL_TRACE:
                this.a.c();
            default:
                break;
        }
    }
}
