package com.xunlei.tdlive.util;

import com.android.volley.Request;

// compiled from: HttpUtils.java
private class f$g implements f$b$a {
    private Request<?> a;

    public f$g(Request<?> request) {
        this.a = request;
    }

    public void a() {
        this.a.cancel();
    }
}
