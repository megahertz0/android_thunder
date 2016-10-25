package com.inmobi.commons.core.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.inmobi.commons.a.a;
import java.util.HashMap;

// compiled from: KeyValueStore.java
public final class c {
    private static HashMap<String, c> a;
    private static final Object b;
    private SharedPreferences c;

    static {
        a = new HashMap();
        b = new Object();
    }

    private c(Context context, String str) {
        this.c = context.getSharedPreferences(str, 0);
    }

    public static String a(String str) {
        return new StringBuilder("com.im.keyValueStore.").append(str).toString();
    }

    public static c a(Context context, String str) {
        String a = a(str);
        c cVar = (c) a.get(a);
        if (cVar == null) {
            synchronized (b) {
                cVar = (c) a.get(a);
                if (cVar != null) {
                } else {
                    cVar = new c(context, a);
                    a.put(a, cVar);
                }
            }
        }
        return cVar;
    }

    public static c b(String str) {
        return a(a.b(), str);
    }

    public final void a(String str, String str2) {
        Editor edit = this.c.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public final String b(String str, String str2) {
        return this.c.getString(str, str2);
    }

    public final void a(String str, int i) {
        Editor edit = this.c.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public final int b(String str, int i) {
        return this.c.getInt(str, i);
    }

    public final void a(String str, long j) {
        Editor edit = this.c.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public final long b(String str, long j) {
        return this.c.getLong(str, j);
    }

    public final void a(String str, boolean z) {
        Editor edit = this.c.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public final boolean b(String str, boolean z) {
        return this.c.getBoolean(str, z);
    }
}
