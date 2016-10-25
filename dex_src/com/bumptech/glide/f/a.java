package com.bumptech.glide.f;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.f.a.d;
import com.bumptech.glide.f.b.h;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.c;
import com.bumptech.glide.load.engine.e;
import com.bumptech.glide.load.engine.g;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.f;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.Future;

// compiled from: GenericRequest.java
public final class a<A, T, Z, R> implements b, h, e {
    private static final Queue<a<?, ?, ?, ?>> a;
    private j<?> A;
    private c B;
    private long C;
    private int D;
    private final String b;
    private b c;
    private Drawable d;
    private int e;
    private int f;
    private int g;
    private Context h;
    private f<Z> i;
    private com.bumptech.glide.e.f<A, T, Z, R> j;
    private c k;
    private A l;
    private Class<R> m;
    private boolean n;
    private Priority o;
    private com.bumptech.glide.f.b.j<R> p;
    private d<? super A, R> q;
    private float r;
    private com.bumptech.glide.load.engine.b s;
    private d<R> t;
    private int u;
    private int v;
    private DiskCacheStrategy w;
    private Drawable x;
    private Drawable y;
    private boolean z;

    // compiled from: GenericRequest.java
    private enum a {
        ;

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = 5;
            f = 6;
            g = 7;
            h = 8;
            i = new int[]{a, b, c, d, e, f, g, h};
        }
    }

    static {
        a = com.bumptech.glide.h.h.a(0);
    }

    public static <A, T, Z, R> a<A, T, Z, R> a(com.bumptech.glide.e.f<A, T, Z, R> fVar, A a, b bVar, Context context, Priority priority, com.bumptech.glide.f.b.j<R> jVar, float f, Drawable drawable, int i, Drawable drawable2, int i2, Drawable drawable3, int i3, d<? super A, R> dVar, c cVar, com.bumptech.glide.load.engine.b bVar2, f<Z> fVar2, Class<R> cls, boolean z, d<R> dVar2, int i4, int i5, DiskCacheStrategy diskCacheStrategy) {
        a<A, T, Z, R> aVar = (a) a.poll();
        if (aVar == null) {
            aVar = new a();
        }
        aVar.j = fVar;
        aVar.l = a;
        aVar.c = bVar;
        aVar.d = drawable3;
        aVar.e = i3;
        aVar.h = context.getApplicationContext();
        aVar.o = priority;
        aVar.p = jVar;
        aVar.r = f;
        aVar.x = drawable;
        aVar.f = i;
        aVar.y = drawable2;
        aVar.g = i2;
        aVar.q = dVar;
        aVar.k = cVar;
        aVar.s = bVar2;
        aVar.i = fVar2;
        aVar.m = cls;
        aVar.n = z;
        aVar.t = dVar2;
        aVar.u = i4;
        aVar.v = i5;
        aVar.w = diskCacheStrategy;
        aVar.D = a.a;
        if (a != null) {
            a("ModelLoader", fVar.e(), "try .using(ModelLoader)");
            a("Transcoder", fVar.f(), "try .as*(Class).transcode(ResourceTranscoder)");
            a("Transformation", fVar2, "try .transform(UnitTransformation.get())");
            if (diskCacheStrategy.cacheSource()) {
                a("SourceEncoder", fVar.c(), "try .sourceEncoder(Encoder) or .diskCacheStrategy(NONE/RESULT)");
            } else {
                a("SourceDecoder", fVar.b(), "try .decoder/.imageDecoder/.videoDecoder(ResourceDecoder) or .diskCacheStrategy(ALL/SOURCE)");
            }
            if (diskCacheStrategy.cacheSource() || diskCacheStrategy.cacheResult()) {
                a("CacheDecoder", fVar.a(), "try .cacheDecoder(ResouceDecoder) or .diskCacheStrategy(NONE)");
            }
            if (diskCacheStrategy.cacheResult()) {
                a("Encoder", fVar.d(), "try .encode(ResourceEncoder) or .diskCacheStrategy(NONE/SOURCE)");
            }
        }
        return aVar;
    }

    private a() {
        this.b = String.valueOf(hashCode());
    }

    public final void a() {
        this.j = null;
        this.l = null;
        this.h = null;
        this.p = null;
        this.x = null;
        this.y = null;
        this.d = null;
        this.q = null;
        this.k = null;
        this.i = null;
        this.t = null;
        this.z = false;
        this.B = null;
        a.offer(this);
    }

    private static void a(String str, Object obj, String str2) {
        if (obj == null) {
            StringBuilder stringBuilder = new StringBuilder(str);
            stringBuilder.append(" must not be null");
            stringBuilder.append(", ");
            stringBuilder.append(str2);
            throw new NullPointerException(stringBuilder.toString());
        }
    }

    public final void b() {
        this.C = com.bumptech.glide.h.d.a();
        if (this.l == null) {
            a(null);
            return;
        }
        this.D = a.c;
        if (com.bumptech.glide.h.h.a(this.u, this.v)) {
            a(this.u, this.v);
        } else {
            this.p.a((h) this);
        }
        if (!f()) {
            Object obj;
            if (this.D == a.e) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj == null && j()) {
                this.p.c(i());
            }
        }
        if (Log.isLoggable("GenericRequest", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            a(new StringBuilder("finished run method in ").append(com.bumptech.glide.h.d.a(this.C)).toString());
        }
    }

    public final void c() {
        com.bumptech.glide.h.h.a();
        if (this.D != a.g) {
            this.D = a.f;
            if (this.B != null) {
                c cVar = this.B;
                c cVar2 = cVar.a;
                e eVar = cVar.b;
                com.bumptech.glide.h.h.a();
                if (cVar2.f || cVar2.g) {
                    if (cVar2.h == null) {
                        cVar2.h = new HashSet();
                    }
                    cVar2.h.add(eVar);
                } else {
                    cVar2.a.remove(eVar);
                    if (!(!cVar2.a.isEmpty() || cVar2.g || cVar2.f || cVar2.e)) {
                        com.bumptech.glide.load.engine.h hVar = cVar2.i;
                        hVar.b = true;
                        com.bumptech.glide.load.engine.a aVar = hVar.a;
                        aVar.d = true;
                        aVar.b.c();
                        Future future = cVar2.j;
                        if (future != null) {
                            future.cancel(true);
                        }
                        cVar2.e = true;
                        cVar2.b.a(cVar2, cVar2.c);
                    }
                }
                this.B = null;
            }
            if (this.A != null) {
                b(this.A);
            }
            if (j()) {
                this.p.b(i());
            }
            this.D = a.g;
        }
    }

    public final void d() {
        c();
        this.D = a.h;
    }

    public final boolean e() {
        return this.D == a.b || this.D == a.c;
    }

    public final boolean f() {
        return this.D == a.d;
    }

    public final boolean g() {
        return f();
    }

    public final boolean h() {
        return this.D == a.f || this.D == a.g;
    }

    private Drawable i() {
        if (this.x == null && this.f > 0) {
            this.x = this.h.getResources().getDrawable(this.f);
        }
        return this.x;
    }

    public final void a(int i, int i2) {
        if (Log.isLoggable("GenericRequest", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            a(new StringBuilder("Got onSizeReady in ").append(com.bumptech.glide.h.d.a(this.C)).toString());
        }
        if (this.D == a.c) {
            this.D = a.b;
            int round = Math.round(this.r * ((float) i));
            int round2 = Math.round(this.r * ((float) i2));
            com.bumptech.glide.load.a.c a = this.j.e().a(this.l, round, round2);
            if (a == null) {
                a(new Exception(new StringBuilder("Failed to load model: '").append(this.l).append("'").toString()));
                return;
            }
            j a2;
            g gVar;
            c cVar;
            com.bumptech.glide.load.resource.e.c f = this.j.f();
            if (Log.isLoggable("GenericRequest", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                a(new StringBuilder("finished setup for calling load in ").append(com.bumptech.glide.h.d.a(this.C)).toString());
            }
            this.z = true;
            com.bumptech.glide.load.engine.b bVar = this.s;
            b bVar2 = this.c;
            com.bumptech.glide.e.f fVar = this.j;
            f fVar2 = this.i;
            Priority priority = this.o;
            boolean z = this.n;
            DiskCacheStrategy diskCacheStrategy = this.w;
            com.bumptech.glide.h.h.a();
            long a3 = com.bumptech.glide.h.d.a();
            b eVar = new e(a.b(), bVar2, round, round2, fVar.a(), fVar.b(), fVar2, fVar.d(), f, fVar.c());
            if (z) {
                a2 = bVar.b.a(eVar);
                if (a2 == null) {
                    a2 = null;
                } else if (a2 instanceof g) {
                    gVar = (g) a2;
                } else {
                    gVar = new g(a2, true);
                }
                if (a2 != null) {
                    a2.d();
                    bVar.d.put(eVar, new com.bumptech.glide.load.engine.b.e(eVar, a2, bVar.a()));
                }
            } else {
                a2 = null;
            }
            if (a2 != null) {
                a(a2);
                if (Log.isLoggable("Engine", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                    com.bumptech.glide.load.engine.b.a("Loaded resource from cache", a3, eVar);
                }
                cVar = null;
            } else {
                if (z) {
                    WeakReference weakReference = (WeakReference) bVar.d.get(eVar);
                    if (weakReference != null) {
                        gVar = (g) weakReference.get();
                        if (gVar != null) {
                            gVar.d();
                        } else {
                            bVar.d.remove(eVar);
                        }
                    } else {
                        a2 = null;
                    }
                } else {
                    a2 = null;
                }
                if (a2 != null) {
                    a(a2);
                    if (Log.isLoggable("Engine", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                        com.bumptech.glide.load.engine.b.a("Loaded resource from active resources", a3, eVar);
                    }
                    cVar = null;
                } else {
                    c cVar2 = (c) bVar.a.get(eVar);
                    if (cVar2 != null) {
                        cVar2.a((e) this);
                        if (Log.isLoggable("Engine", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                            com.bumptech.glide.load.engine.b.a("Added to existing load", a3, eVar);
                        }
                        cVar = new c(this, cVar2);
                    } else {
                        com.bumptech.glide.load.engine.b.a aVar = bVar.c;
                        c cVar3 = new c(eVar, aVar.a, aVar.b, z, aVar.c);
                        Runnable hVar = new com.bumptech.glide.load.engine.h(cVar3, new com.bumptech.glide.load.engine.a(eVar, round, round2, a, fVar, fVar2, f, bVar.e, diskCacheStrategy, priority), priority);
                        bVar.a.put(eVar, cVar3);
                        cVar3.a((e) this);
                        cVar3.i = hVar;
                        cVar3.j = cVar3.d.submit(hVar);
                        if (Log.isLoggable("Engine", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                            com.bumptech.glide.load.engine.b.a("Started new load", a3, eVar);
                        }
                        cVar = new c(this, cVar3);
                    }
                }
            }
            this.B = cVar;
            this.z = this.A != null;
            if (Log.isLoggable("GenericRequest", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                a(new StringBuilder("finished onSizeReady in ").append(com.bumptech.glide.h.d.a(this.C)).toString());
            }
        }
    }

    private boolean j() {
        return this.k == null || this.k.b(this);
    }

    private boolean k() {
        return this.k == null || !this.k.i();
    }

    public final void a(j<?> jVar) {
        if (jVar == null) {
            a(new Exception(new StringBuilder("Expected to receive a Resource<R> with an object of ").append(this.m).append(" inside, but instead got null.").toString()));
            return;
        }
        Object a = jVar.a();
        if (a == null || !this.m.isAssignableFrom(a.getClass())) {
            b(jVar);
            a(new Exception(new StringBuilder("Expected to receive an object of ").append(this.m).append(" but instead got ").append(a != null ? a.getClass() : com.umeng.a.d).append("{").append(a).append("} inside Resource{").append(jVar).append("}.").append(a != null ? com.umeng.a.d : " To indicate failure return a null Resource object, rather than a Resource object containing null data.").toString()));
            return;
        }
        Object obj;
        if (this.k == null || this.k.a(this)) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            b(jVar);
            this.D = a.d;
            return;
        }
        boolean k = k();
        this.D = a.d;
        this.A = jVar;
        if (this.q != null) {
            this.q.a(this.l);
        }
        this.p.a(a, this.t.a(this.z, k));
        if (this.k != null) {
            this.k.c(this);
        }
        if (Log.isLoggable("GenericRequest", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            a(new StringBuilder("Resource ready in ").append(com.bumptech.glide.h.d.a(this.C)).append(" size: ").append(((double) jVar.b()) * 9.5367431640625E-7d).append(" fromCache: ").append(this.z).toString());
        }
    }

    public final void a(Exception exception) {
        this.D = a.e;
        if (this.q != null) {
            k();
        }
        if (j()) {
            Drawable drawable;
            if (this.l == null) {
                if (this.d == null && this.e > 0) {
                    this.d = this.h.getResources().getDrawable(this.e);
                }
                drawable = this.d;
            } else {
                drawable = null;
            }
            if (drawable == null) {
                if (this.y == null && this.g > 0) {
                    this.y = this.h.getResources().getDrawable(this.g);
                }
                drawable = this.y;
            }
            if (drawable == null) {
                drawable = i();
            }
            this.p.d(drawable);
        }
    }

    private void a(String str) {
        new StringBuilder().append(str).append(" this: ").append(this.b);
    }

    private void b(j jVar) {
        com.bumptech.glide.h.h.a();
        if (jVar instanceof g) {
            ((g) jVar).e();
            this.A = null;
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }
}
