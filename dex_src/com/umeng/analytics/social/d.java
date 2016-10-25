package com.umeng.analytics.social;

// compiled from: UMResult.java
public class d {
    private int a;
    private String b;
    private String c;
    private Exception d;

    public d(int i) {
        this.a = -1;
        this.b = d;
        this.c = d;
        this.d = null;
        this.a = i;
    }

    public d(int i, Exception exception) {
        this.a = -1;
        this.b = d;
        this.c = d;
        this.d = null;
        this.a = i;
        this.d = exception;
    }

    public Exception a() {
        return this.d;
    }

    public int b() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public String c() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String d() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String toString() {
        return new StringBuilder("status=").append(this.a).append("\r\nmsg:  ").append(this.b).append("\r\ndata:  ").append(this.c).toString();
    }
}
