package com.bumptech.glide.load.b;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

// compiled from: GenericLoaderFactory.java
public final class c {
    private static final m c;
    private final Map<Class, Map<Class, n>> a;
    private final Map<Class, Map<Class, m>> b;
    private final Context d;

    static {
        c = new d();
    }

    public c(Context context) {
        this.a = new HashMap();
        this.b = new HashMap();
        this.d = context.getApplicationContext();
    }

    public final synchronized <T, Y> n<T, Y> a(Class<T> cls, Class<Y> cls2, n<T, Y> nVar) {
        n<T, Y> nVar2;
        this.b.clear();
        Map map = (Map) this.a.get(cls);
        if (map == null) {
            map = new HashMap();
            this.a.put(cls, map);
        }
        nVar2 = (n) map.put(cls2, nVar);
        if (nVar2 != null) {
            for (Map map2 : this.a.values()) {
                if (map2.containsValue(nVar2)) {
                    nVar2 = null;
                    break;
                }
            }
        }
        return nVar2;
    }

    public final synchronized <T, Y> m<T, Y> a(Class<T> cls, Class<Y> cls2) {
        m<T, Y> mVar;
        Map map = (Map) this.b.get(cls);
        if (map != null) {
            mVar = (m) map.get(cls2);
        } else {
            mVar = null;
        }
        if (mVar == null) {
            n nVar;
            m<T, Y> mVar2;
            map = (Map) this.a.get(cls);
            if (map != null) {
                nVar = (n) map.get(cls2);
            } else {
                nVar = null;
            }
            if (nVar == null) {
                n nVar2 = nVar;
                for (Class cls3 : this.a.keySet()) {
                    if (cls3.isAssignableFrom(cls)) {
                        map = (Map) this.a.get(cls3);
                        if (map != null) {
                            nVar = (n) map.get(cls2);
                            if (nVar != null) {
                                break;
                            }
                            nVar2 = nVar;
                        }
                    }
                    nVar = nVar2;
                    nVar2 = nVar;
                }
                nVar = nVar2;
            }
            if (nVar != null) {
                m a = nVar.a(this.d, this);
                a((Class) cls, (Class) cls2, a);
            } else {
                a((Class) cls, (Class) cls2, c);
                mVar2 = mVar;
            }
            mVar = mVar2;
        } else if (c.equals(mVar)) {
            mVar = null;
        }
        return mVar;
    }

    private <T, Y> void a(Class<T> cls, Class<Y> cls2, m<T, Y> mVar) {
        Map map = (Map) this.b.get(cls);
        if (map == null) {
            map = new HashMap();
            this.b.put(cls, map);
        }
        map.put(cls2, mVar);
    }
}
