package com.nostra13.universalimageloader.core;

// compiled from: LoadAndDisplayImageTask.java
final class l implements Runnable {
    final /* synthetic */ i a;

    l(i iVar) {
        this.a = iVar;
    }

    public final void run() {
        this.a.e.onLoadingCancelled(this.a.b, this.a.c.getWrappedView());
    }
}
