package com.bumptech.glide;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.manager.d;
import com.bumptech.glide.manager.e;
import com.bumptech.glide.manager.h;
import com.bumptech.glide.manager.i;
import com.bumptech.glide.manager.j;
import com.bumptech.glide.manager.m;
import com.bumptech.glide.manager.n;
import com.umeng.message.MsgConstant;

// compiled from: RequestManager.java
public final class g implements i {
    public final Context a;
    public final h b;
    public final n c;
    public final e d;
    public final b e;
    private final m f;

    // compiled from: RequestManager.java
    public final class a<A, T> {
        public final com.bumptech.glide.load.b.m<A, T> a;
        public final Class<T> b;

        public a(com.bumptech.glide.load.b.m<A, T> mVar, Class<T> cls) {
            this.a = mVar;
            this.b = cls;
        }
    }

    // compiled from: RequestManager.java
    class b {
        b() {
        }
    }

    // compiled from: RequestManager.java
    private static class c implements com.bumptech.glide.manager.c.a {
        private final n a;

        public c(n nVar) {
            this.a = nVar;
        }

        public final void a(boolean z) {
            if (z) {
                n nVar = this.a;
                for (com.bumptech.glide.f.b bVar : com.bumptech.glide.h.h.a(nVar.a)) {
                    if (!bVar.f() && !bVar.h()) {
                        bVar.d();
                        if (nVar.c) {
                            nVar.b.add(bVar);
                        } else {
                            bVar.b();
                        }
                    }
                }
            }
        }
    }

    public g(Context context, h hVar, m mVar) {
        n nVar = new n();
        d dVar = new d();
        this(context, hVar, mVar, nVar);
    }

    private g(Context context, h hVar, m mVar, n nVar) {
        i eVar;
        this.a = context.getApplicationContext();
        this.b = hVar;
        this.f = mVar;
        this.c = nVar;
        this.d = e.a(context);
        this.e = new b(this);
        com.bumptech.glide.manager.c.a cVar = new c(nVar);
        if ((context.checkCallingOrSelfPermission(MsgConstant.PERMISSION_ACCESS_NETWORK_STATE) == 0 ? 1 : null) != null) {
            eVar = new e(context, cVar);
        } else {
            eVar = new j();
        }
        if (com.bumptech.glide.h.h.c()) {
            new Handler(Looper.getMainLooper()).post(new h(this, hVar));
        } else {
            hVar.a(this);
        }
        hVar.a(eVar);
    }

    public final void a() {
        e eVar = this.d;
        eVar.b.a();
        eVar.c.a();
    }

    public final void b_() {
        n nVar = this.c;
        for (com.bumptech.glide.f.b bVar : com.bumptech.glide.h.h.a(nVar.a)) {
            bVar.c();
        }
        nVar.b.clear();
    }

    public final <T> b<T> a(Class<T> cls) {
        com.bumptech.glide.load.b.m a = e.a((Class) cls, this.a);
        com.bumptech.glide.load.b.m b = e.b(cls, this.a);
        if (a == null && b == null) {
            throw new IllegalArgumentException(new StringBuilder("Unknown type ").append(cls).append(". You must provide a Model of a type for which there is a registered ModelLoader, if you are using a custom model, you must first call Glide#register with a ModelLoaderFactory for your custom model class").toString());
        }
        return new b(cls, a, b, this.a, this.d, this.c, this.b, this.e);
    }

    public final void b() {
        com.bumptech.glide.h.h.a();
        n nVar = this.c;
        nVar.c = false;
        for (com.bumptech.glide.f.b bVar : com.bumptech.glide.h.h.a(nVar.a)) {
            if (!bVar.f() && !bVar.h() && !bVar.e()) {
                bVar.b();
            }
        }
        nVar.b.clear();
    }

    public final void c() {
        com.bumptech.glide.h.h.a();
        n nVar = this.c;
        nVar.c = true;
        for (com.bumptech.glide.f.b bVar : com.bumptech.glide.h.h.a(nVar.a)) {
            if (bVar.e()) {
                bVar.d();
                nVar.b.add(bVar);
            }
        }
    }
}
