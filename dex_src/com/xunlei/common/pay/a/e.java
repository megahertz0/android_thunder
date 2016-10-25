package com.xunlei.common.pay.a;

import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.param.XLPayParam;

// compiled from: XLPayTask.java
public abstract class e {
    private static int c;
    public int a;
    public XLOnPayListener b;
    private int d;
    private int e;
    private Object f;

    public abstract void a(XLPayParam xLPayParam);

    public abstract void c();

    public abstract XLPayParam d();

    static {
        c = 3000000;
    }

    public e() {
        this.d = 0;
        this.a = 0;
        this.e = 0;
        this.f = null;
        this.b = null;
    }

    public final void a() {
        int i = c;
        c = i + 1;
        this.d = i;
    }

    public final int b() {
        return this.d;
    }

    private int g() {
        return this.a;
    }

    private int h() {
        return 0;
    }

    public final void a(Object obj) {
        this.f = obj;
    }

    public final void a(XLOnPayListener xLOnPayListener) {
        this.b = xLOnPayListener;
    }

    public static f e() {
        return f.a();
    }

    public final Object f() {
        return this.f;
    }

    public final void a(Object... objArr) {
        f.a().a(objArr);
    }
}
