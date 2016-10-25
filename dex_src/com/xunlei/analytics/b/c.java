package com.xunlei.analytics.b;

public class c {
    public static final String a = "1";
    public static final String b = "2";
    public static final String c = "3";
    public static final String d = "4";
    public static final String e = "2";
    public static final String f = "1";
    private String g;
    private String h;
    private String i;
    private long j;
    private e k;

    public c(String str, String str2, String str3, long j, e eVar) {
        this.g = str;
        this.h = str2;
        this.i = str3;
        this.j = j;
        this.k = eVar;
    }

    public String a() {
        return this.g;
    }

    public void a(long j) {
        this.j = j;
    }

    public void a(e eVar) {
        this.k = eVar;
    }

    public void a(String str) {
        this.g = str;
    }

    public String b() {
        return this.h;
    }

    public void b(String str) {
        this.h = str;
    }

    public String c() {
        return this.i;
    }

    public void c(String str) {
        this.i = str;
    }

    public long d() {
        return this.j;
    }

    public e e() {
        return this.k;
    }

    public String toString() {
        return new StringBuilder("Event{appId='").append(this.g).append('\'').append(", interId='").append(this.h).append('\'').append(", eventId='").append(this.i).append('\'').append(", oprTime=").append(this.j).append(", extData=").append(this.k).append('}').toString();
    }
}
