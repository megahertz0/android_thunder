package com.xunlei.downloadprovider.j;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.i;
import com.android.volley.p;
import com.android.volley.s;
import com.android.volley.toolbox.d;
import com.android.volley.toolbox.e;
import com.android.volley.toolbox.g;
import com.android.volley.toolbox.h;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// compiled from: VolleyRequestManager.java
public class a {
    static final /* synthetic */ boolean a;
    private static a c;
    private static p d;
    private static p e;
    private Context b;
    private Executor f;
    private ExecutorService g;

    static {
        boolean z;
        if (a.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
        c = null;
        d = null;
        e = null;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    public static p b() {
        return a().d();
    }

    public static p c() {
        return a().e();
    }

    public static void a(Context context) {
        if (a || context != null) {
            a().b = context.getApplicationContext();
            return;
        }
        throw new AssertionError();
    }

    public final synchronized p d() {
        if (e == null) {
            e = a(this.b, this.f);
        }
        return e;
    }

    public final synchronized p e() {
        if (d == null) {
            d = a(this.b, null);
        }
        return d;
    }

    private static p a(Context context, Executor executor) {
        g hVar;
        s gVar;
        File file = new File(context.getCacheDir(), "volley");
        String str = "volley/0";
        try {
            String packageName = context.getPackageName();
            str = packageName + "/" + context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (NameNotFoundException e) {
        }
        if (VERSION.SDK_INT >= 9) {
            hVar = new h();
        } else {
            e eVar = new e(AndroidHttpClient.newInstance(str));
        }
        i aVar = new com.android.volley.toolbox.a(hVar);
        if (executor == null) {
            gVar = new com.android.volley.g(new Handler(Looper.getMainLooper()));
        } else {
            gVar = new com.android.volley.g(executor);
        }
        p pVar = new p(new d(file), aVar, 4, gVar);
        pVar.a();
        return pVar;
    }

    private a() {
        this.b = null;
        this.f = new b(this);
    }

    static /* synthetic */ ExecutorService a(a aVar) {
        if (aVar.g == null) {
            aVar.g = Executors.newCachedThreadPool();
        }
        return aVar.g;
    }
}
