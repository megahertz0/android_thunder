package com.xunlei.analytics.b;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.xunlei.analytics.c.b;
import com.xunlei.analytics.c.e;
import com.xunlei.analytics.config.a;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import java.util.Map.Entry;

public class d {
    private static final char[] a;

    static {
        a = new char[]{'\n', '\t'};
    }

    public static c a(Context context, String str, String str2, HashMap<String, String> hashMap) {
        e eVar = new e();
        eVar.a(e.a(context).a());
        eVar.b(b.c(context));
        eVar.c(b.b(context));
        eVar.d(VERSION.RELEASE);
        eVar.e("8");
        eVar.f(Build.MODEL);
        eVar.i(a.c());
        eVar.j(b.a());
        eVar.k(f.a());
        eVar.n(b.a(context));
        eVar.m(String.valueOf(b.d(context)));
        eVar.a((HashMap) hashMap);
        return new c(a.a(), str, str2, System.currentTimeMillis(), eVar);
    }

    private static String a(e eVar, String str) {
        if (eVar == null) {
            return BuildConfig.VERSION_NAME;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("pub_guid=").append(a(eVar.a())).append(",");
        stringBuilder.append("pub_mac=").append(a(eVar.b())).append(",");
        stringBuilder.append("pub_imei=").append(a(eVar.c())).append(",");
        stringBuilder.append("pub_os=").append(a(eVar.d())).append(",");
        stringBuilder.append("pub_sdkversion=").append(a(eVar.e())).append(",");
        stringBuilder.append("pub_phonetype=").append(a(eVar.f())).append(",");
        stringBuilder.append("pub_longitude=").append(a(eVar.g())).append(",");
        stringBuilder.append("pub_dimension=").append(a(eVar.h())).append(",");
        stringBuilder.append("pub_channelid=").append(a(eVar.i())).append(",");
        stringBuilder.append("pub_ip=").append(a(eVar.j())).append(",");
        stringBuilder.append("pub_session_id=").append(a(eVar.k())).append(",");
        if (str != c.e) {
            stringBuilder.append("pub_pageid=").append(a(eVar.l())).append(",");
        }
        stringBuilder.append("pub_network=").append(a(eVar.m())).append(",");
        stringBuilder.append("pub_sv=").append(a(eVar.n())).append(",");
        if (eVar.o() != null) {
            for (Entry entry : eVar.o().entrySet()) {
                stringBuilder.append((String) entry.getKey()).append("=").append(a((String) entry.getValue())).append(",");
            }
        }
        if (a.h() != null) {
            for (Entry entry2 : a.h().entrySet()) {
                stringBuilder.append((String) entry2.getKey()).append("=").append(a((String) entry2.getValue())).append(",");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private static String a(String str) {
        String a = b.a(str);
        return a == null ? BuildConfig.VERSION_NAME : a;
    }

    private static String a(String str, char c) {
        return (str == null || str.indexOf(c) == -1) ? str : str.replace(String.valueOf(c), String.valueOf(c));
    }

    private static String a(String str, char[] cArr) {
        if (cArr != null) {
            for (char c : cArr) {
                str = a(str, c);
            }
        }
        return str;
    }

    public static String b(Context context, String str, String str2, HashMap<String, String> hashMap) {
        c a = a(context, str, str2, hashMap);
        String a2 = a(a.e(), str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a(a.a(), a)).append("\t");
        stringBuilder.append(a(a.b(), a)).append("\t");
        stringBuilder.append(a(a.c(), a)).append("\t");
        stringBuilder.append(a.d()).append("\t");
        stringBuilder.append(a(a2, a));
        return stringBuilder.toString();
    }
}
