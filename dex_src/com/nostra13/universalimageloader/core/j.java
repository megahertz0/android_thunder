package com.nostra13.universalimageloader.core;

// compiled from: LoadAndDisplayImageTask.java
final class j implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ i c;

    j(i iVar, int i, int i2) {
        this.c = iVar;
        this.a = i;
        this.b = i2;
    }

    public final void run() {
        this.c.c.getWrappedView();
    }
}
