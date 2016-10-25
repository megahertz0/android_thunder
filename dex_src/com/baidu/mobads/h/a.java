package com.baidu.mobads.h;

import android.content.Context;
import com.baidu.mobads.interfaces.IXAdContainerFactory;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.j.m;

public class a {
    private static IXAdContainerFactory e;
    public double a;
    private Context b;
    private Class<?> c;
    private double d;
    private Boolean f;
    private IXAdLogger g;

    public a(Class<?> cls, Context context, double d, Boolean bool) {
        this.c = null;
        this.a = 0.1d;
        this.g = m.a().f();
        this.c = cls;
        this.b = context;
        this.d = d;
        this.f = bool;
    }

    public IXAdContainerFactory a() {
        if (e == null) {
            e = (IXAdContainerFactory) this.c.getDeclaredConstructor(new Class[]{Context.class}).newInstance(new Object[]{this.b});
            this.a = e.getRemoteVersion();
            e.setDebugMode(this.f);
            e.handleShakeVersion(this.d, "8.27");
        }
        return e;
    }

    public void b() {
        e = null;
    }
}
