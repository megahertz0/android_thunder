package com.xunlei.common.httpclient.request;

import java.io.InputStream;

// compiled from: RequestParams.java
class b$a {
    public InputStream a;
    public String b;
    private String c;

    public b$a(InputStream inputStream, String str, String str2) {
        this.a = inputStream;
        this.c = str;
        this.b = str2;
    }

    public final String a() {
        return this.c != null ? this.c : "nofilename";
    }
}
