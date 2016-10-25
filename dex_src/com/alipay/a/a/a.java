package com.alipay.a.a;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.a.a.b;

public final class a implements i, j {
    public final Object a(Object obj) {
        Object[] objArr = (Object[]) obj;
        List arrayList = new ArrayList();
        for (Object obj2 : objArr) {
            arrayList.add(f.b(obj2));
        }
        return arrayList;
    }

    public final Object a(Object obj, Type type) {
        if (!obj.getClass().equals(b.class)) {
            return null;
        }
        b bVar = (b) obj;
        if (type instanceof GenericArrayType) {
            throw new IllegalArgumentException("Does not support generic array!");
        }
        Type componentType = ((Class) type).getComponentType();
        int size = bVar.a.size();
        Object newInstance = Array.newInstance(componentType, size);
        for (int i = 0; i < size; i++) {
            Array.set(newInstance, i, e.a(bVar.a(i), componentType));
        }
        return newInstance;
    }

    public final boolean a(Class<?> cls) {
        return cls.isArray();
    }
}
