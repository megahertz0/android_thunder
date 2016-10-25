package com.xunlei.downloadprovider.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

// compiled from: CommonHandler.java
public abstract class b<T> extends Handler {
    private WeakReference<T> a;

    public abstract void a(T t, Message message);

    public b(T t, Looper looper) {
        super(looper);
        this.a = new WeakReference(t);
    }

    public void handleMessage(Message message) {
        Object obj = this.a.get();
        if (obj != null) {
            a(obj, message);
        }
    }
}
