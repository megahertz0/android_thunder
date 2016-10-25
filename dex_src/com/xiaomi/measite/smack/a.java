package com.xiaomi.measite.smack;

import com.xiaomi.smack.d;
import com.xiaomi.smack.f;
import com.xiaomi.smack.util.b;
import com.xiaomi.smack.util.m;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class a implements com.xiaomi.smack.debugger.a {
    public static boolean a;
    private SimpleDateFormat b;
    private com.xiaomi.smack.a c;
    private f d;
    private d e;
    private Writer f;
    private Reader g;
    private com.xiaomi.smack.util.f h;
    private m i;

    static {
        a = false;
    }

    public a(com.xiaomi.smack.a aVar, Writer writer, Reader reader) {
        this.b = new SimpleDateFormat("hh:mm:ss aaa");
        this.c = null;
        this.d = null;
        this.e = null;
        this.c = aVar;
        this.f = writer;
        this.g = reader;
        e();
    }

    private void e() {
        com.xiaomi.smack.util.a aVar = new com.xiaomi.smack.util.a(this.g);
        this.h = new b(this);
        aVar.a(this.h);
        b bVar = new b(this.f);
        this.i = new c(this);
        bVar.a(this.i);
        this.g = aVar;
        this.f = bVar;
        this.d = new d(this);
        this.e = new e(this);
    }

    public Reader a() {
        return this.g;
    }

    public Reader a(Reader reader) {
        ((com.xiaomi.smack.util.a) this.g).b(this.h);
        com.xiaomi.smack.util.a aVar = new com.xiaomi.smack.util.a(reader);
        aVar.a(this.h);
        this.g = aVar;
        return this.g;
    }

    public Writer a(Writer writer) {
        ((b) this.f).b(this.i);
        b bVar = new b(writer);
        bVar.a(this.i);
        this.f = bVar;
        return this.f;
    }

    public Writer b() {
        return this.f;
    }

    public f c() {
        return this.d;
    }

    public f d() {
        return null;
    }
}
