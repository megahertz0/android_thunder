package com.xiaomi.push.service.module;

import android.content.Context;
import android.text.TextUtils;

public class b {
    private String a;
    private String b;
    private String c;
    private int d;
    private ClassLoader e;
    private Object f;

    public b(String str, String str2, ClassLoader classLoader, String str3, int i) {
        this.d = 0;
        this.a = str;
        this.b = str2;
        this.e = classLoader;
        this.d = i;
        this.c = str3;
        try {
            if (!TextUtils.isEmpty(str3)) {
                this.f = this.e.loadClass(str3).newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ClassLoader a() {
        return this.e;
    }

    public void a(Context context) {
        if (this.f != null) {
            try {
                this.e.loadClass(this.c).getMethod("onCreate", new Class[]{Context.class, String.class}).invoke(this.f, new Object[]{context, this.b});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
