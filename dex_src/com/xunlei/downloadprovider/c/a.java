package com.xunlei.downloadprovider.c;

import android.text.TextUtils;
import com.android.volley.p;
import com.xunlei.downloadprovider.c.a.b;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.c.a.d;
import com.xunlei.downloadprovider.c.a.e;
import com.xunlei.downloadprovider.c.a.f;
import com.xunlei.downloadprovider.c.a.h;
import java.util.List;

// compiled from: CommentManager.java
public class a {
    public static final String a;
    public p b;
    public e c;
    public int d;
    public f e;
    private d f;
    private f g;

    // compiled from: CommentManager.java
    public static interface a<T> {
        void a(b bVar);

        void a(T t);
    }

    // compiled from: CommentManager.java
    public static class b {
        public int a;
        public String b;
    }

    static {
        a = a.class.getSimpleName();
    }

    public a() {
        this.d = 100;
        this.b = com.xunlei.downloadprovider.j.a.c();
    }

    public final void a(e eVar) {
        if (eVar != null) {
            this.c = eVar;
            if (!(this.e == null || TextUtils.equals(eVar.b, this.e.a))) {
                f fVar = this.e;
                fVar.a = null;
                fVar.c = 0;
                fVar.b = 0;
                fVar.e = null;
            }
            this.f = new d();
            this.f.a = this.c.b;
            this.f.d = this.c.c;
            this.f.b = this.c.a;
        }
    }

    public final void a(a<f> aVar) {
        a(aVar, "hot", "refresh", 0);
    }

    public final void a(long j, long j2, a<List<c>> aVar) {
        b bVar = new b();
        bVar.a = j2;
        bVar.b = 20;
        bVar.a(j);
        h hVar = new h(0, new StringBuilder("https://comment-shoulei-ssl.xunlei.com/comment/api/user_comment?").append(bVar.f()).toString(), null, new b(this, aVar), new h(this, aVar));
        hVar.setRetryPolicy(new com.android.volley.f(10000, 1, 1.0f));
        hVar.setTag(a);
        this.b.a(hVar);
    }

    public final void a(a<f> aVar, String str, String str2, long j) {
        if (aVar != null) {
            if (this.c == null || this.f == null) {
                throw new IllegalStateException("no comment resource attached");
            }
            this.f.f = str;
            this.f.g = str2;
            this.f.c = j;
            this.f.e = this.d;
            d dVar = this.f;
            h hVar = new h(0, new StringBuilder("https://comment-shoulei-ssl.xunlei.com/comment/api/comment?").append(dVar.f()).toString(), null, new i(this, aVar, str, str2), new j(this, aVar));
            hVar.setRetryPolicy(new com.android.volley.f(10000, 1, 1.0f));
            hVar.setTag(a);
            this.b.a(hVar);
        }
    }
}
