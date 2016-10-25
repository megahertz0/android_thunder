package com.bumptech.glide;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.FragmentActivity;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.b;
import com.bumptech.glide.load.engine.b.h;
import com.bumptech.glide.load.engine.b.i;
import com.bumptech.glide.load.engine.b.j;
import com.bumptech.glide.load.engine.c.a;
import com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor;
import com.bumptech.glide.load.resource.bitmap.l;
import com.bumptech.glide.load.resource.bitmap.m;
import com.bumptech.glide.load.resource.bitmap.p;
import com.bumptech.glide.load.resource.bitmap.r;
import com.bumptech.glide.load.resource.d.f;
import com.bumptech.glide.load.resource.d.g;
import com.bumptech.glide.load.resource.e.d;
import com.bumptech.glide.manager.SupportRequestManagerFragment;
import com.bumptech.glide.manager.k;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

// compiled from: Glide.java
public class e {
    private static volatile e g;
    final b a;
    public final c b;
    public final i c;
    final d d;
    final f e;
    final f f;
    private final com.bumptech.glide.load.b.c h;
    private final DecodeFormat i;
    private final com.bumptech.glide.f.b.f j;
    private final com.bumptech.glide.e.c k;
    private final com.bumptech.glide.load.resource.bitmap.e l;
    private final l m;
    private final Handler n;
    private final a o;

    public static e a(Context context) {
        if (g == null) {
            synchronized (e.class) {
                if (g == null) {
                    Context applicationContext = context.getApplicationContext();
                    List a = new com.bumptech.glide.d.b(applicationContext).a();
                    f fVar = new f(applicationContext);
                    Iterator it = a.iterator();
                    while (it.hasNext()) {
                        it.next();
                    }
                    if (fVar.e == null) {
                        fVar.e = new FifoPriorityThreadPoolExecutor(Math.max(1, Runtime.getRuntime().availableProcessors()));
                    }
                    if (fVar.f == null) {
                        fVar.f = new FifoPriorityThreadPoolExecutor(1);
                    }
                    j jVar = new j(fVar.a);
                    if (fVar.c == null) {
                        if (VERSION.SDK_INT >= 11) {
                            fVar.c = new com.bumptech.glide.load.engine.a.f(jVar.a);
                        } else {
                            fVar.c = new com.bumptech.glide.load.engine.a.d();
                        }
                    }
                    if (fVar.d == null) {
                        fVar.d = new h(jVar.b);
                    }
                    if (fVar.h == null) {
                        fVar.h = new com.bumptech.glide.load.engine.b.f(fVar.a);
                    }
                    if (fVar.b == null) {
                        fVar.b = new b(fVar.d, fVar.h, fVar.f, fVar.e);
                    }
                    if (fVar.g == null) {
                        fVar.g = DecodeFormat.DEFAULT;
                    }
                    g = new e(fVar.b, fVar.d, fVar.c, fVar.a, fVar.g);
                    it = a.iterator();
                    while (it.hasNext()) {
                        it.next();
                        e eVar = g;
                    }
                }
            }
        }
        return g;
    }

    private e(b bVar, i iVar, c cVar, Context context, DecodeFormat decodeFormat) {
        this.j = new com.bumptech.glide.f.b.f();
        this.d = new d();
        this.a = bVar;
        this.b = cVar;
        this.c = iVar;
        this.i = decodeFormat;
        this.h = new com.bumptech.glide.load.b.c(context);
        this.n = new Handler(Looper.getMainLooper());
        this.o = new a(iVar, cVar, decodeFormat);
        this.k = new com.bumptech.glide.e.c();
        com.bumptech.glide.e.b rVar = new r(cVar, decodeFormat);
        this.k.a(InputStream.class, Bitmap.class, rVar);
        com.bumptech.glide.e.b jVar = new com.bumptech.glide.load.resource.bitmap.j(cVar, decodeFormat);
        this.k.a(ParcelFileDescriptor.class, Bitmap.class, jVar);
        com.bumptech.glide.e.b pVar = new p(rVar, jVar);
        this.k.a(com.bumptech.glide.load.b.i.class, Bitmap.class, pVar);
        rVar = new com.bumptech.glide.load.resource.c.c(context, cVar);
        this.k.a(InputStream.class, com.bumptech.glide.load.resource.c.b.class, rVar);
        this.k.a(com.bumptech.glide.load.b.i.class, com.bumptech.glide.load.resource.d.a.class, new g(pVar, rVar, cVar));
        this.k.a(InputStream.class, File.class, new com.bumptech.glide.load.resource.b.d());
        a(File.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.a.a());
        a(File.class, InputStream.class, new com.bumptech.glide.load.b.b.c.a());
        a(Integer.TYPE, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.c.a());
        a(Integer.TYPE, InputStream.class, new com.bumptech.glide.load.b.b.e.a());
        a(Integer.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.c.a());
        a(Integer.class, InputStream.class, new com.bumptech.glide.load.b.b.e.a());
        a(String.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.d.a());
        a(String.class, InputStream.class, new com.bumptech.glide.load.b.b.f.a());
        a(Uri.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.e.a());
        a(Uri.class, InputStream.class, new com.bumptech.glide.load.b.b.g.a());
        a(URL.class, InputStream.class, new com.bumptech.glide.load.b.b.h.a());
        a(com.bumptech.glide.load.b.e.class, InputStream.class, new com.bumptech.glide.load.b.b.a.a());
        a(byte[].class, InputStream.class, new com.bumptech.glide.load.b.b.b.a());
        this.d.a(Bitmap.class, m.class, new com.bumptech.glide.load.resource.e.b(context.getResources(), cVar));
        this.d.a(com.bumptech.glide.load.resource.d.a.class, com.bumptech.glide.load.resource.a.b.class, new com.bumptech.glide.load.resource.e.a(new com.bumptech.glide.load.resource.e.b(context.getResources(), cVar)));
        this.l = new com.bumptech.glide.load.resource.bitmap.e(cVar);
        this.e = new f(cVar, this.l);
        this.m = new l(cVar);
        this.f = new f(cVar, this.m);
    }

    final <T, Z> com.bumptech.glide.e.b<T, Z> a(Class<T> cls, Class<Z> cls2) {
        return this.k.a(cls, cls2);
    }

    public static void a(com.bumptech.glide.f.b.j<?> jVar) {
        com.bumptech.glide.h.h.a();
        com.bumptech.glide.f.b e = jVar.e();
        if (e != null) {
            e.c();
            jVar.a(null);
        }
    }

    private <T, Y> void a(Class<T> cls, Class<Y> cls2, n<T, Y> nVar) {
        this.h.a((Class) cls, (Class) cls2, (n) nVar);
    }

    private static <T, Y> com.bumptech.glide.load.b.m<T, Y> a(Class<T> cls, Class<Y> cls2, Context context) {
        return cls == null ? null : a(context).h.a(cls, cls2);
    }

    public static <T> com.bumptech.glide.load.b.m<T, InputStream> a(Class<T> cls, Context context) {
        return a((Class) cls, InputStream.class, context);
    }

    public static <T> com.bumptech.glide.load.b.m<T, ParcelFileDescriptor> b(Class<T> cls, Context context) {
        return a((Class) cls, ParcelFileDescriptor.class, context);
    }

    public static g b(Context context) {
        com.bumptech.glide.manager.l a = com.bumptech.glide.manager.l.a();
        Context context2 = context;
        while (context2 != null) {
            if (com.bumptech.glide.h.h.b() && !(context2 instanceof Application)) {
                g gVar;
                Activity activity;
                if (context2 instanceof FragmentActivity) {
                    activity = (FragmentActivity) context2;
                    if (com.bumptech.glide.h.h.c()) {
                        context2 = activity.getApplicationContext();
                    } else {
                        com.bumptech.glide.manager.l.a(activity);
                        SupportRequestManagerFragment a2 = a.a(activity.getSupportFragmentManager());
                        gVar = a2.a;
                        if (gVar == null) {
                            gVar = new g(activity, a2.b, a2.c);
                            a2.a = gVar;
                            return gVar;
                        }
                    }
                } else if (context2 instanceof Activity) {
                    activity = (Activity) context2;
                    if (com.bumptech.glide.h.h.c() || VERSION.SDK_INT < 11) {
                        context2 = activity.getApplicationContext();
                    } else {
                        com.bumptech.glide.manager.l.a(activity);
                        k a3 = a.a(activity.getFragmentManager());
                        gVar = a3.c;
                        if (gVar == null) {
                            gVar = new g(activity, a3.a, a3.b);
                            a3.c = gVar;
                            return gVar;
                        }
                    }
                } else if (context2 instanceof ContextWrapper) {
                    context2 = ((ContextWrapper) context2).getBaseContext();
                }
                return gVar;
            }
            return a.a(context2);
        }
        throw new IllegalArgumentException("You cannot start a load on a null Context");
    }
}
