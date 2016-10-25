package com.xunlei.tdlive;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

// compiled from: CollectService.java
private final class a$b extends Handler {
    final /* synthetic */ a a;

    public a$b(a aVar, Looper looper) {
        this.a = aVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        this.a.a((Intent) message.obj);
    }
}
