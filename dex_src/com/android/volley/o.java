package com.android.volley;

// compiled from: Request.java
final class o implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ long b;
    final /* synthetic */ Request c;

    o(Request request, String str, long j) {
        this.c = request;
        this.a = str;
        this.b = j;
    }

    public final void run() {
        this.c.mEventLog.a(this.a, this.b);
        this.c.mEventLog.a(toString());
    }
}
