package com.squareup.wire;

import a.a;

// compiled from: ProtoAdapter.java
public abstract class b<E> {
    public static final b<Boolean> b;
    public static final b<Integer> c;
    public static final b<Integer> d;
    public static final b<Integer> e;
    public static final b<Integer> f;
    public static final b<Integer> g;
    public static final b<Long> h;
    public static final b<Long> i;
    public static final b<Long> j;
    public static final b<Long> k;
    public static final b<Long> l;
    public static final b<Float> m;
    public static final b<Double> n;
    public static final b<String> o;
    public static final b<a> p;
    final Class<?> a;
    private final FieldEncoding q;

    public b(FieldEncoding fieldEncoding, Class<?> cls) {
        this.q = fieldEncoding;
        this.a = cls;
    }

    static {
        b = new c(FieldEncoding.VARINT, Boolean.class);
        c = new h(FieldEncoding.VARINT, Integer.class);
        d = new i(FieldEncoding.VARINT, Integer.class);
        e = new j(FieldEncoding.VARINT, Integer.class);
        b kVar = new k(FieldEncoding.FIXED32, Integer.class);
        f = kVar;
        g = kVar;
        h = new l(FieldEncoding.VARINT, Long.class);
        i = new m(FieldEncoding.VARINT, Long.class);
        j = new n(FieldEncoding.VARINT, Long.class);
        kVar = new o(FieldEncoding.FIXED64, Long.class);
        k = kVar;
        l = kVar;
        m = new d(FieldEncoding.FIXED32, Float.class);
        n = new e(FieldEncoding.FIXED64, Double.class);
        o = new f(FieldEncoding.LENGTH_DELIMITED, String.class);
        p = new g(FieldEncoding.LENGTH_DELIMITED, a.class);
    }
}
