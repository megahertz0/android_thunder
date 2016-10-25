package com.android.volley;

// compiled from: CacheDispatcher.java
final class d implements Runnable {
    final /* synthetic */ Request a;
    final /* synthetic */ c b;

    d(c cVar, Request request) {
        this.b = cVar;
        this.a = request;
    }

    public final void run() {
        try {
            c.a(this.b).put(this.a);
        } catch (InterruptedException e) {
        }
    }
}
