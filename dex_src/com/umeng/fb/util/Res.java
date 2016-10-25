package com.umeng.fb.util;

import android.content.Context;
import com.umeng.message.proguard.j;

public class Res {
    private static final String a;
    private static Res b;
    private static String c;
    private static Class d;
    private static Class e;
    private static Class f;
    private static Class g;
    private static Class h;
    private static Class i;
    private static Class j;
    private static Class k;

    static {
        a = Res.class.getName();
        b = null;
        c = null;
        d = null;
        e = null;
        f = null;
        g = null;
        h = null;
        i = null;
        j = null;
        k = null;
    }

    private Res(String str) {
        try {
            e = Class.forName(str + ".R$drawable");
        } catch (ClassNotFoundException e) {
            Log.b(a, e.getMessage());
        }
        try {
            f = Class.forName(str + ".R$layout");
        } catch (ClassNotFoundException e2) {
            Log.b(a, e2.getMessage());
        }
        try {
            d = Class.forName(str + ".R$id");
        } catch (ClassNotFoundException e22) {
            Log.b(a, e22.getMessage());
        }
        try {
            g = Class.forName(str + ".R$anim");
        } catch (ClassNotFoundException e222) {
            Log.b(a, e222.getMessage());
        }
        try {
            h = Class.forName(str + ".R$style");
        } catch (ClassNotFoundException e2222) {
            Log.b(a, e2222.getMessage());
        }
        try {
            i = Class.forName(str + ".R$string");
        } catch (ClassNotFoundException e22222) {
            Log.b(a, e22222.getMessage());
        }
        try {
            j = Class.forName(str + ".R$array");
        } catch (ClassNotFoundException e222222) {
            Log.b(a, e222222.getMessage());
        }
        try {
            k = Class.forName(str + ".R$color");
        } catch (ClassNotFoundException e2222222) {
            Log.b(a, e2222222.getMessage());
        }
    }

    public static synchronized Res getInstance(Context context) {
        Res res;
        synchronized (Res.class) {
            if (b == null) {
                c = c != null ? c : context.getPackageName();
                b = new Res(c);
            }
            res = b;
        }
        return res;
    }

    public static void setPackageName(String str) {
        c = str;
    }

    public int a(String str) {
        return a(g, str);
    }

    public int b(String str) {
        return a(d, str);
    }

    public int c(String str) {
        return a(e, str);
    }

    public int d(String str) {
        return a(f, str);
    }

    public int e(String str) {
        return a(h, str);
    }

    public int f(String str) {
        return a(i, str);
    }

    public int g(String str) {
        return a(j, str);
    }

    public int h(String str) {
        return a(k, str);
    }

    private int a(Class<?> cls, String str) {
        if (cls == null) {
            Log.b(a, new StringBuilder("getRes(null,").append(str).append(j.t).toString());
            throw new IllegalArgumentException(new StringBuilder("ResClass is not initialized. Please make sure you have added necessary resources. Also make sure you have ").append(c).append(".R$* configured in obfuscation. field=").append(str).toString());
        }
        try {
            return cls.getField(str).getInt(str);
        } catch (Exception e) {
            Log.b(a, new StringBuilder("getRes(").append(cls.getName()).append(", ").append(str).append(j.t).toString());
            Log.b(a, "Error getting resource. Make sure you have copied all resources (res/) from SDK to your project. ");
            Log.b(a, e.getMessage());
            return -1;
        }
    }
}
