package com.alipay.a.a;

import com.alipay.a.b.a;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.a.a.c;

public final class h implements i, j {
    private static Map<Object, Object> a(Type type) {
        Type type2 = type;
        while (r1 != Properties.class) {
            if (r1 == Hashtable.class) {
                return new Hashtable();
            }
            if (r1 == IdentityHashMap.class) {
                return new IdentityHashMap();
            }
            if (r1 != SortedMap.class && r1 != TreeMap.class) {
                if (r1 != ConcurrentMap.class && r1 != ConcurrentHashMap.class) {
                    if (r1 != Map.class && r1 != HashMap.class) {
                        if (r1 == LinkedHashMap.class) {
                            return new LinkedHashMap();
                        }
                        if (r1 instanceof ParameterizedType) {
                            Object rawType = ((ParameterizedType) r1).getRawType();
                        } else {
                            Class cls = r1;
                            if (cls.isInterface()) {
                                throw new IllegalArgumentException(new StringBuilder("unsupport type ").append(r1).toString());
                            }
                            try {
                                return (Map) cls.newInstance();
                            } catch (Throwable e) {
                                throw new IllegalArgumentException(new StringBuilder("unsupport type ").append(r1).toString(), e);
                            }
                        }
                    }
                    return new HashMap();
                }
                return new ConcurrentHashMap();
            }
            return new TreeMap();
        }
        return new Properties();
    }

    public final Object a(Object obj) {
        Map treeMap = new TreeMap();
        for (Entry entry : ((Map) obj).entrySet()) {
            if (entry.getKey() instanceof String) {
                treeMap.put((String) entry.getKey(), f.b(entry.getValue()));
            } else {
                throw new IllegalArgumentException("Map key must be String!");
            }
        }
        return treeMap;
    }

    public final Object a(Object obj, Type type) {
        if (!obj.getClass().equals(c.class)) {
            return null;
        }
        c cVar = (c) obj;
        Map a = a(type);
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class cls = parameterizedType.getActualTypeArguments()[0];
            Type type2 = parameterizedType.getActualTypeArguments()[1];
            if (String.class == cls) {
                Iterator a2 = cVar.a();
                while (a2.hasNext()) {
                    String str = (String) a2.next();
                    if (a.a((Class) type2)) {
                        a.put(str, cVar.a(str));
                    } else {
                        a.put(str, e.a(cVar.a(str), type2));
                    }
                }
                return a;
            }
            throw new IllegalArgumentException("Deserialize Map Key must be String.class");
        }
        throw new IllegalArgumentException("Deserialize Map must be Generics!");
    }

    public final boolean a(Class<?> cls) {
        return Map.class.isAssignableFrom(cls);
    }
}
