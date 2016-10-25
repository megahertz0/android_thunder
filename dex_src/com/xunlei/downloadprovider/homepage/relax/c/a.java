package com.xunlei.downloadprovider.homepage.relax.c;

import android.os.Handler;
import android.text.TextUtils;
import com.nostra13.universalimageloader.a.a.b.c;
import com.nostra13.universalimageloader.b.f;
import com.nostra13.universalimageloader.core.d;
import com.xunlei.downloadprovider.app.BrothersApplication;
import java.io.File;

// compiled from: ImageLoadCtrl.java
public class a {
    private static final String a;
    private static a c;
    private a b;
    private final a d;

    // compiled from: ImageLoadCtrl.java
    public static interface a {
        void a();

        void a(String str);
    }

    static {
        a = a.class.getSimpleName();
        c = null;
    }

    private a() {
        this.b = null;
        this.d = new b(this);
    }

    public static a a() {
        if (c == null) {
            synchronized (a.class) {
                c = new a();
            }
        }
        return c;
    }

    public final synchronized void a(String str, a aVar) {
        if (TextUtils.isEmpty(str)) {
            aVar.a();
        } else {
            this.b = aVar;
            com.nostra13.universalimageloader.core.c.a aVar2 = new com.nostra13.universalimageloader.core.c.a();
            aVar2.a();
            aVar2.h = false;
            aVar2.r = new Handler();
            d.a().a(str, aVar2.b(), this.d);
        }
    }

    public static String a(String str) {
        return f.a(BrothersApplication.a, true).getAbsolutePath().concat(File.separator).concat(new c().a(str));
    }
}
