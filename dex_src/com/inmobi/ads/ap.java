package com.inmobi.ads;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

// compiled from: VisibilityTracker.java
abstract class ap {
    static final a a;
    private static final String b;
    private final ArrayList<View> c;
    private long d;
    private boolean e;
    private final Map<View, d> f;
    private final a g;
    private c h;
    private final b i;
    private final Handler j;
    private boolean k;

    // compiled from: VisibilityTracker.java
    static interface c {
        void a(List<View> list, List<View> list2);
    }

    // compiled from: VisibilityTracker.java
    static interface a {
        boolean a(View view, View view2, int i, Object obj);
    }

    // compiled from: VisibilityTracker.java
    class b implements Runnable {
        private final ArrayList<View> b;
        private final ArrayList<View> c;

        b() {
            this.c = new ArrayList();
            this.b = new ArrayList();
        }

        public void run() {
            ap.this.k = false;
            for (Entry entry : ap.this.f.entrySet()) {
                View view = (View) entry.getKey();
                int i = ap.this;
                if (ap.this.g.a(((d) entry.getValue()).c, view, i, ((d) entry.getValue()).d)) {
                    this.b.add(view);
                } else {
                    this.c.add(view);
                }
            }
            if (ap.this.h != null) {
                ap.this.h.a(this.b, this.c);
            }
            this.b.clear();
            this.c.clear();
            ap.this.b();
        }
    }

    // compiled from: VisibilityTracker.java
    static class d {
        int a;
        long b;
        View c;
        Object d;

        d() {
        }
    }

    protected abstract int a();

    protected abstract void b();

    static {
        b = ap.class.getSimpleName();
        a = new a() {
            private final Rect a;

            {
                this.a = new Rect();
            }

            public final boolean a(View view, View view2, int i, Object obj) {
                if (view2 == null || view2.getVisibility() != 0 || view.getParent() == null || !view2.getGlobalVisibleRect(this.a)) {
                    return false;
                }
                long height = ((long) view2.getHeight()) * ((long) view2.getWidth());
                return height > 0 && (((long) this.a.height()) * ((long) this.a.width())) * 100 >= height * ((long) i);
            }
        };
    }

    ap() {
        this(a);
    }

    ap(a aVar) {
        this(new WeakHashMap(10), aVar, new Handler(Looper.getMainLooper()));
    }

    private ap(Map<View, d> map, a aVar, Handler handler) {
        this.d = 0;
        this.e = true;
        this.f = map;
        this.g = aVar;
        this.j = handler;
        this.i = new b();
        this.c = new ArrayList(50);
    }

    void a(c cVar) {
        this.h = cVar;
    }

    public void c() {
        this.j.removeCallbacksAndMessages(null);
        this.k = false;
        this.e = true;
    }

    public void d() {
        this.e = false;
        h();
    }

    public boolean f() {
        return this.e;
    }

    protected void a(View view, Object obj, int i) {
        a(view, view, obj, i);
    }

    protected void a(View view, View view2, Object obj, int i) {
        d dVar = (d) this.f.get(view2);
        if (dVar == null) {
            dVar = new d();
            this.f.put(view2, dVar);
            this.d++;
        }
        dVar.a = i;
        dVar.b = this.d;
        dVar.c = view;
        dVar.d = obj;
        if (this.d % 50 == 0) {
            a(this.d - 50);
        }
        if (1 == this.f.size()) {
            d();
        }
    }

    private void a(long j) {
        for (Entry entry : this.f.entrySet()) {
            if (((d) entry.getValue()).b < j) {
                this.c.add(entry.getKey());
            }
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            a((View) it.next());
        }
        this.c.clear();
    }

    protected void a(View view) {
        if (((d) this.f.remove(view)) != null) {
            this.d--;
            if (this.f.size() == 0) {
                c();
            }
        }
    }

    protected void g() {
        this.f.clear();
        this.j.removeMessages(0);
        this.k = false;
    }

    protected void e() {
        g();
        this.h = null;
        this.e = true;
    }

    protected void h() {
        if (!this.k && !this.e) {
            this.k = true;
            this.j.postDelayed(this.i, (long) a());
        }
    }
}
