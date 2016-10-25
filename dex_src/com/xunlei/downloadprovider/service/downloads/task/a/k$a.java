package com.xunlei.downloadprovider.service.downloads.task.a;

// compiled from: MessageThread.java
public abstract class k$a<T> implements Runnable {
    protected T c;

    public abstract void a(T t);

    public k$a(T t) {
        this.c = t;
    }

    public final void run() {
        a(this.c);
        this.c = null;
    }
}
