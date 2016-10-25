package com.xunlei.downloadprovider.service.downloads.task.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

// compiled from: MessageThread.java
public final class k extends Thread implements Executor {
    public Handler a;
    private Looper b;
    private Callback c;

    public k(String str, Callback callback) {
        super(str);
        this.c = callback;
    }

    public final void run() {
        Looper.prepare();
        this.b = Looper.myLooper();
        this.a = new Handler(this.b, new l(this));
        Looper.loop();
    }

    public final void execute(Runnable runnable) {
        Handler handler = this.a;
        if (handler == null) {
            throw new RejectedExecutionException();
        }
        handler.post(runnable);
    }
}
