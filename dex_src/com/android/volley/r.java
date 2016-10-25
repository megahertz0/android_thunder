package com.android.volley;

// compiled from: Response.java
public final class r<T> {
    public final T a;
    public final com.android.volley.b.a b;
    public final w c;
    public boolean d;

    // compiled from: Response.java
    public static interface a {
        void onErrorResponse(w wVar);
    }

    // compiled from: Response.java
    public static interface b<T> {
        void onResponse(T t);
    }

    public static <T> r<T> a(T t, com.android.volley.b.a aVar) {
        return new r(t, aVar);
    }

    public static <T> r<T> a(w wVar) {
        return new r(wVar);
    }

    private r(T t, com.android.volley.b.a aVar) {
        this.d = false;
        this.a = t;
        this.b = aVar;
        this.c = null;
    }

    private r(w wVar) {
        this.d = false;
        this.a = null;
        this.b = null;
        this.c = wVar;
    }
}
