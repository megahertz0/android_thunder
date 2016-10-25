package com.umeng.socialize.utils;

import java.lang.reflect.Proxy;

public class Dummy {
    public static <T> T get(Class<T> cls, T t) {
        if (t != null) {
            return t;
        }
        if (cls.isInterface()) {
            return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new AnonymousClass_1());
        }
        try {
            return cls.newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
