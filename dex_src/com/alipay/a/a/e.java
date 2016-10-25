package com.alipay.a.a;

import com.alipay.a.b.a;
import com.alipay.sdk.util.h;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.a.a.b;
import org.a.a.c;

public final class e {
    static List<i> a;

    static {
        List arrayList = new ArrayList();
        a = arrayList;
        arrayList.add(new l());
        a.add(new d());
        a.add(new c());
        a.add(new h());
        a.add(new k());
        a.add(new b());
        a.add(new a());
        a.add(new g());
    }

    public static final <T> T a(Object obj, Type type) {
        for (i iVar : a) {
            if (iVar.a(a.a(type))) {
                T a = iVar.a(obj, type);
                if (a != null) {
                    return a;
                }
            }
        }
        return null;
    }

    public static final Object a(String str, Type type) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Object trim = str.trim();
        return (trim.startsWith("[") && trim.endsWith("]")) ? a(new b(trim), type) : (trim.startsWith("{") && trim.endsWith(h.d)) ? a(new c(trim), type) : a(trim, type);
    }
}
