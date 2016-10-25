package com.tencent.a.a.a.a;

import android.content.Context;

public abstract class f {
    protected Context a;

    protected f(Context context) {
        this.a = null;
        this.a = context;
    }

    public final void a(c cVar) {
        if (cVar != null) {
            String toString = cVar.toString();
            if (a()) {
                a(h.g(toString));
            }
        }
    }

    protected abstract void a(String str);

    protected abstract boolean a();

    protected abstract String b();

    public final c o() {
        String f = a() ? h.f(b()) : null;
        return f != null ? c.e(f) : null;
    }
}
