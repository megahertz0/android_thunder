package com.bumptech.glide.load.resource;

import java.io.OutputStream;

// compiled from: NullEncoder.java
public final class a<T> implements com.bumptech.glide.load.a<T> {
    private static final a<?> a;

    static {
        a = new a();
    }

    public static <T> com.bumptech.glide.load.a<T> b() {
        return a;
    }

    public final boolean a(T t, OutputStream outputStream) {
        return false;
    }

    public final String a() {
        return com.umeng.a.d;
    }
}
