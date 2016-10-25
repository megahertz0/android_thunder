package com.xiaomi.smack;

import com.xiaomi.channel.commonutils.misc.a;
import java.util.Map;

public class b implements Cloneable {
    public static String b;
    public static String c;
    private String a;
    private String d;
    private int e;
    private boolean f;
    private boolean g;
    private String h;
    private e i;

    static {
        b = "wcc-ml-test10.bj";
        c = null;
    }

    public b(Map<String, Integer> map, int i, String str, e eVar) {
        this.f = a.a;
        this.g = true;
        a(map, i, str, eVar);
    }

    private void a(Map<String, Integer> map, int i, String str, e eVar) {
        this.d = b();
        this.e = i;
        this.a = str;
        this.i = eVar;
    }

    public static final String b() {
        return c != null ? c : a.a() ? "sandbox.xmpush.xiaomi.com" : a.b() ? "10.237.14.141" : "app.chat.xiaomi.net";
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public byte[] a() {
        return null;
    }

    public void b(String str) {
        this.h = str;
    }

    public String c() {
        return this.a;
    }

    public void c(String str) {
        this.d = str;
    }

    public String d() {
        return this.h;
    }

    public int e() {
        return this.e;
    }

    public String f() {
        return this.d;
    }

    public boolean g() {
        return this.f;
    }
}
