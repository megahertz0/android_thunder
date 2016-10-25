package com.xunlei.downloadprovider.download.taskDetail.a;

import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.util.r;
import java.util.ArrayList;
import java.util.List;

// compiled from: TaskDetailAdModelProxy.java
public class b implements a {
    private static final String a;
    private static b b;

    // compiled from: TaskDetailAdModelProxy.java
    private abstract class a {
        protected a a;
        protected final int b;
        protected final com.xunlei.downloadprovider.ad.recommend.a.b.b<h> c;
        protected final List<h> d;

        public abstract void a();

        public a(int i, List<h> list, com.xunlei.downloadprovider.ad.recommend.a.b.b<h> bVar) {
            this.a = null;
            this.b = i;
            this.c = bVar;
            this.d = list;
        }

        public final void a(a aVar) {
            this.a = aVar;
        }

        protected final void b() {
            if (this.d.size() < this.b && this.a != null) {
                this.a.a();
            } else if (this.d.isEmpty()) {
                this.c.a(com.xunlei.downloadprovider.ad.recommend.a.b.a.a);
            } else {
                this.c.a(this.d);
            }
        }
    }

    // compiled from: TaskDetailAdModelProxy.java
    private class b extends a {
        public b(int i, List<h> list, com.xunlei.downloadprovider.ad.recommend.a.b.b<h> bVar) {
            super(i, list, bVar);
        }

        public final void a() {
            new e().a(this.b, new c(this));
        }
    }

    // compiled from: TaskDetailAdModelProxy.java
    private class c extends a {
        public c(int i, List<h> list, com.xunlei.downloadprovider.ad.recommend.a.b.b<h> bVar) {
            super(i, list, bVar);
        }

        public final void a() {
            new i().a(this.b, new d(this));
        }
    }

    static {
        a = b.class.getSimpleName();
    }

    private b() {
    }

    public static b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    public static void b() {
        b = null;
    }

    public final void a(int i, com.xunlei.downloadprovider.ad.recommend.a.b.b<h> bVar) {
        AD_TYPE a = com.xunlei.downloadprovider.ad.common.b.a(r.c().e.a().j);
        new StringBuilder("loadAds adType: ").append(a.name());
        List arrayList = new ArrayList(i);
        if (a == AD_TYPE.SOURCE_GDT_FLAG) {
            new b(i, arrayList, bVar).a();
            return;
        }
        a cVar = new c(i, arrayList, bVar);
        cVar.a(new b(i, arrayList, bVar));
        cVar.a();
    }
}
