package com.alipay.a.a;

import com.alipay.a.b.a;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;

public final class b implements i, j {
    private static Collection<Object> a(Class<?> cls, Type type) {
        if (cls == AbstractCollection.class) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet();
        }
        if (cls.isAssignableFrom(TreeSet.class)) {
            return new TreeSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(EnumSet.class)) {
            return EnumSet.noneOf(type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class);
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException(new StringBuilder("create instane error, class ").append(cls.getName()).toString());
        }
    }

    public final Object a(Object obj) {
        List arrayList = new ArrayList();
        for (Object obj2 : (Iterable) obj) {
            arrayList.add(f.b(obj2));
        }
        return arrayList;
    }

    public final Object a(Object obj, Type type) {
        int i = 0;
        if (!obj.getClass().equals(org.a.a.b.class)) {
            return null;
        }
        org.a.a.b bVar = (org.a.a.b) obj;
        Collection a = a(a.a(type), type);
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            while (i < bVar.a.size()) {
                a.add(e.a(bVar.a(i), type2));
                i++;
            }
            return a;
        }
        throw new IllegalArgumentException("Does not support the implement for generics.");
    }

    public final boolean a(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls);
    }
}
