package com.inmobi.ads;

import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import com.inmobi.ads.b.f;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

// compiled from: ImpressionTracker.java
class n {
    private static final String a;
    private final ap b;
    private final Map<View, b> c;
    private final Map<View, b> d;
    private final Handler e;
    private final c f;
    private final long g;
    private c h;
    private a i;

    // compiled from: ImpressionTracker.java
    static interface a {
        void a(View view, Object obj);
    }

    // compiled from: ImpressionTracker.java
    private static class b {
        Object a;
        int b;
        int c;
        long d;

        b(Object obj, int i, int i2) {
            this.d = Long.MAX_VALUE;
            this.a = obj;
            this.b = i;
            this.c = i2;
        }

        void a() {
            this.d = SystemClock.uptimeMillis();
        }
    }

    // compiled from: ImpressionTracker.java
    class c implements Runnable {
        private final ArrayList<View> b;

        c() {
            this.b = new ArrayList();
        }

        public void run() {
            for (Entry entry : n.this.d.entrySet()) {
                View view = (View) entry.getKey();
                b bVar = (b) entry.getValue();
                if (n.b(bVar.d, bVar.c)) {
                    n.this.i.a(view, n.this);
                    this.b.add(view);
                }
            }
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                n.this.a((View) it.next());
            }
            this.b.clear();
            if (!n.this.d.isEmpty()) {
                n.this.f();
            }
        }
    }

    static {
        a = n.class.getSimpleName();
    }

    n(f fVar, ap apVar, a aVar) {
        this(new WeakHashMap(), new WeakHashMap(), apVar, new Handler(), fVar, aVar);
    }

    n(Map<View, b> map, Map<View, b> map2, ap apVar, Handler handler, f fVar, a aVar) {
        this.c = map;
        this.d = map2;
        this.b = apVar;
        this.g = (long) fVar.d();
        this.h = new c() {
            public void a(List<View> list, List<View> list2) {
                for (View view : list) {
                    b bVar = (b) n.this.c.get(view);
                    if (bVar == null) {
                        n.this.a(view);
                    } else {
                        b bVar2 = (b) n.this.d.get(view);
                        if (bVar2 == null || !n.this.equals(n.this)) {
                            bVar.a();
                            n.this.d.put(view, bVar);
                        }
                    }
                }
                for (View view2 : list2) {
                    n.this.d.remove(view2);
                }
                n.this.f();
            }
        };
        this.b.a(this.h);
        this.e = handler;
        this.f = new c();
        this.i = aVar;
    }

    void a(View view, Object obj, f fVar) {
        b bVar = (b) this.c.get(view);
        if (bVar == null || !bVar.a.equals(obj)) {
            a(view);
            bVar = new b(obj, fVar.a(), fVar.b());
            this.c.put(view, bVar);
            this.b.a(view, obj, bVar.b);
        }
    }

    void a(View view) {
        this.c.remove(view);
        this.d.remove(view);
        this.b.a(view);
    }

    View a(Object obj) {
        View view;
        for (Entry entry : this.c.entrySet()) {
            if (((b) entry.getValue()).a.equals(obj)) {
                view = (View) entry.getKey();
                break;
            }
        }
        view = null;
        if (view != null) {
            a(view);
        }
        return view;
    }

    void a() {
        Logger.a(InternalLogLevel.INTERNAL, a, "Impression Tracker paused");
        this.b.g();
        this.e.removeCallbacksAndMessages(null);
        this.d.clear();
    }

    void b() {
        Logger.a(InternalLogLevel.INTERNAL, a, "Impression Tracker resumed");
        for (Entry entry : this.c.entrySet()) {
            this.b.a((View) entry.getKey(), ((b) entry.getValue()).a, ((b) entry.getValue()).b);
        }
        f();
        this.b.d();
    }

    void c() {
        this.c.clear();
        this.d.clear();
        this.b.g();
        this.e.removeMessages(0);
    }

    boolean d() {
        return !this.c.isEmpty();
    }

    void e() {
        c();
        this.b.e();
        this.h = null;
    }

    void f() {
        if (!this.e.hasMessages(0)) {
            this.e.postDelayed(this.f, this.g);
        }
    }

    private static boolean b(long j, int i) {
        return SystemClock.uptimeMillis() - j >= ((long) i);
    }
}
