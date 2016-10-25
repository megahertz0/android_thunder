package com.bumptech.glide.load.b.b;

import android.content.Context;
import com.bumptech.glide.load.a.f;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.e;
import com.bumptech.glide.load.b.k;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import java.io.InputStream;

// compiled from: HttpUrlGlideUrlLoader.java
public final class a implements m<e, InputStream> {
    private final k<e, e> a;

    // compiled from: HttpUrlGlideUrlLoader.java
    public static class a implements n<e, InputStream> {
        private final k<e, e> a;

        public a() {
            this.a = new k(500);
        }

        public final m<e, InputStream> a(Context context, c cVar) {
            return new a(this.a);
        }
    }

    public final /* synthetic */ com.bumptech.glide.load.a.c a(Object obj, int i, int i2) {
        e eVar = (e) obj;
        if (this.a != null) {
            k kVar = this.a;
            Object a = a.a(eVar);
            Object b = kVar.a.b(a);
            a.a();
            e eVar2 = (e) b;
            if (eVar2 == null) {
                kVar = this.a;
                kVar.a.b(a.a(eVar), eVar);
            } else {
                eVar = eVar2;
            }
        }
        return new f(obj);
    }

    public a() {
        this(null);
    }

    public a(k<e, e> kVar) {
        this.a = kVar;
    }
}
