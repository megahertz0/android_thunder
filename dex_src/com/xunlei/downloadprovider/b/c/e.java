package com.xunlei.downloadprovider.b.c;

// compiled from: BpFuture.java
public abstract class e implements j {
    protected boolean i;

    public abstract void run();

    public void cancel() {
        this.i = true;
    }

    public boolean isCanceled() {
        return this.i;
    }
}
