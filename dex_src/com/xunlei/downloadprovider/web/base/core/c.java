package com.xunlei.downloadprovider.web.base.core;

import android.os.Handler;
import android.os.Message;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: CustomWebView.java
final class c extends Handler {
    final /* synthetic */ CustomWebView a;

    c(CustomWebView customWebView) {
        this.a = customWebView;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case SimpleLog.LOG_LEVEL_TRACE:
                CustomWebView.a(this.a);
            case SimpleLog.LOG_LEVEL_DEBUG:
                CustomWebView.b(this.a);
            default:
                break;
        }
    }
}
