package com.android.volley;

import com.android.volley.p.a;

// compiled from: RequestQueue.java
final class q implements a {
    final /* synthetic */ Object a;
    final /* synthetic */ p b;

    q(p pVar, Object obj) {
        this.b = pVar;
        this.a = obj;
    }

    public final boolean a(Request<?> request) {
        return request.getTag() == this.a;
    }
}
