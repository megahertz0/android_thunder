package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Proxy;

public final class x {
    private g a;
    private z b;

    public x(g gVar) {
        this.a = gVar;
        this.b = new z(this);
    }

    public final g a() {
        return this.a;
    }

    public final <T> T a(Class<T> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new y(this.a, cls, this.b));
    }
}
