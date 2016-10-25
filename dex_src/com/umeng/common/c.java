package com.umeng.common;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.message.PushAgent;
import com.umeng.message.proguard.j;

// compiled from: Res.java
public class c {
    private static final String a;
    private static c b;
    private static Class e;
    private static Class f;
    private static Class g;
    private static Class h;
    private static Class i;
    private static Class j;
    private static Class k;
    private static Class l;
    private Context c;
    private String d;

    static {
        a = c.class.getName();
        e = null;
        f = null;
        g = null;
        h = null;
        i = null;
        j = null;
        k = null;
        l = null;
    }

    private c(Context context) {
        this.c = context.getApplicationContext();
        UmLog.d(a, new StringBuilder("packageName=").append(this.c.getPackageName()).toString());
        try {
            f = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$drawable");
        } catch (ClassNotFoundException e) {
            UmLog.e(a, e.getMessage());
        }
        try {
            g = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$layout");
        } catch (ClassNotFoundException e2) {
            UmLog.e(a, e2.getMessage());
        }
        try {
            e = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$id");
        } catch (ClassNotFoundException e22) {
            UmLog.e(a, e22.getMessage());
        }
        try {
            h = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$anim");
        } catch (ClassNotFoundException e222) {
            UmLog.e(a, e222.getMessage());
        }
        try {
            i = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$style");
        } catch (ClassNotFoundException e2222) {
            UmLog.e(a, e2222.getMessage());
        }
        try {
            j = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$string");
        } catch (ClassNotFoundException e22222) {
            UmLog.e(a, e22222.getMessage());
        }
        try {
            k = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$array");
        } catch (ClassNotFoundException e222222) {
            UmLog.e(a, e222222.getMessage());
        }
        try {
            l = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$raw");
        } catch (ClassNotFoundException e2222222) {
            UmLog.e(a, e2222222.getMessage());
        }
    }

    public static c a(Context context) {
        if (b == null) {
            b = new c(context);
        }
        return b;
    }

    public int a(String str) {
        return a(h, str);
    }

    public int b(String str) {
        return a(e, str);
    }

    public int c(String str) {
        return a(f, str);
    }

    public int d(String str) {
        return a(g, str);
    }

    public int e(String str) {
        return a(i, str);
    }

    public int f(String str) {
        return a(j, str);
    }

    public int g(String str) {
        return a(k, str);
    }

    public int h(String str) {
        return a(l, str);
    }

    private int a(Class<?> cls, String str) {
        if (cls == null) {
            UmLog.e(a, new StringBuilder("getRes(null,").append(str).append(j.t).toString());
            throw new IllegalArgumentException(new StringBuilder("ResClass is not initialized. Please make sure you have added neccessary resources. Also make sure you have ").append(this.c.getPackageName()).append(".R$* configured in obfuscation. field=").append(str).toString());
        }
        try {
            return cls.getField(str).getInt(str);
        } catch (Exception e) {
            UmLog.e(a, new StringBuilder("getRes(").append(cls.getName()).append(", ").append(str).append(j.t).toString());
            UmLog.e(a, "Error getting resource. Make sure you have copied all resources (res/) from SDK to your project. ");
            UmLog.e(a, e.getMessage());
            return -1;
        }
    }

    public void i(String str) {
        this.d = str;
    }

    public String a() {
        return TextUtils.isEmpty(this.d) ? this.c.getPackageName() : this.d;
    }
}
