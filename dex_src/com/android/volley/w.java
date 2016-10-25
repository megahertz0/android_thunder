package com.android.volley;

// compiled from: VolleyError.java
public class w extends Exception {
    public final l a;
    long b;

    public w() {
        this.a = null;
    }

    public w(l lVar) {
        this.a = lVar;
    }

    public w(String str, Throwable th) {
        super(str, th);
        this.a = null;
    }

    public w(Throwable th) {
        super(th);
        this.a = null;
    }
}
