package com.inmobi.signals.a;

import java.util.Locale;

// compiled from: CellOperatorInfo.java
public class a {
    private int a;
    private int b;
    private int c;
    private int d;
    private String e;

    public a() {
        this.a = -1;
        this.b = -1;
        this.c = -1;
        this.d = -1;
    }

    public void a(int i) {
        this.a = i;
    }

    public void b(int i) {
        this.b = i;
    }

    public void c(int i) {
        this.c = i;
    }

    public void d(int i) {
        this.d = i;
    }

    public String a() {
        return (this.a == -1 && this.b == -1) ? null : this.a + "_" + this.b;
    }

    public String b() {
        return (this.c == -1 && this.d == -1) ? null : this.c + "_" + this.d;
    }

    public void a(String str) {
        this.e = str.toLowerCase(Locale.ENGLISH);
    }

    public String c() {
        return this.e;
    }
}
