package com.xunlei.analytics.config;

import android.content.Context;
import android.text.TextUtils;
import com.xunlei.analytics.b.a;
import com.xunlei.analytics.b.c;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class b {
    private static ConcurrentHashMap<String, Long> a;

    static {
        a = new ConcurrentHashMap();
    }

    public static void a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context can not is null");
        }
        a(context.getClass().getName());
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("page must be named");
        }
        a.put(str, Long.valueOf(System.currentTimeMillis()));
        a.a().b();
    }

    private static void a(String str, long j, long j2) {
        long j3 = j2 - j;
        HashMap hashMap = new HashMap();
        hashMap.put("pub_pageid", str);
        hashMap.put("start", String.valueOf(j));
        hashMap.put("end", String.valueOf(j2));
        hashMap.put("stay_period", String.valueOf(j3));
        a.a().a(c.e, c.e, hashMap);
    }

    public static void b(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context can not is null");
        }
        b(context.getClass().getName());
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("page must be named");
        }
        Long l = (Long) a.remove(str);
        if (l == null) {
            throw new IllegalArgumentException("please call 'onPageStart(%s)' before onPageEnd");
        }
        a(str, l.longValue(), System.currentTimeMillis());
        a.a().b();
    }
}
