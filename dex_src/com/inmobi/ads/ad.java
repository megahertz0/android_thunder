package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.InMobiStrandAdapter.NativeStrandAdListener;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.inmobi.ads.af.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

// compiled from: NativeStrandPositioningController.java
class ad {
    private static final String a;
    private static final NativeStrandAdListener u;
    private final Context b;
    private final af c;
    private final Handler d;
    private final Runnable e;
    private final HashMap<q, WeakReference<View>> f;
    private final WeakHashMap<View, q> g;
    private final InMobiClientPositioning h;
    private r i;
    private NativeStrandAdListener j;
    private final long k;
    private ae l;
    private ae m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private final a v;

    static {
        a = ad.class.getSimpleName();
        u = new NativeStrandAdListener() {
            public final void onAdLoadSucceeded(int i) {
            }

            public final void onAdRemoved(int i) {
            }
        };
    }

    public ad(Context context, long j, InMobiClientPositioning inMobiClientPositioning) {
        this.v = new a() {
            public void a() {
                ad.this.b();
            }
        };
        this.b = context;
        this.k = j;
        this.h = inMobiClientPositioning;
        this.j = u;
        this.i = new r(this.b, this.k, this.h, this.v);
        this.c = new u(inMobiClientPositioning);
        this.f = new HashMap();
        this.g = new WeakHashMap();
        this.l = ae.c();
        this.m = ae.c();
        this.d = new Handler();
        this.e = new Runnable() {
            public void run() {
                if (ad.this.t) {
                    ad.this.g();
                    ad.this.t = false;
                }
            }
        };
        this.o = 0;
        this.p = 0;
    }

    public void a(NativeStrandAdListener nativeStrandAdListener) {
        if (nativeStrandAdListener == null) {
            nativeStrandAdListener = u;
        }
        this.j = nativeStrandAdListener;
    }

    public void a() {
        this.q = false;
        this.s = false;
        this.r = false;
        this.c.a(this.k, new a() {
            public void a(InMobiClientPositioning inMobiClientPositioning) {
                ad.this.a(inMobiClientPositioning);
            }
        });
        this.i.e();
    }

    public void a(Map<String, String> map) {
        this.i.c((Map) map);
    }

    public void a(String str) {
        this.i.a(str);
    }

    public void b() {
        if (this.q) {
            f();
            return;
        }
        if (this.s) {
            a(this.m);
        }
        this.r = true;
    }

    @SuppressLint({"UseSparseArrays"})
    public View a(int i, View view, ViewGroup viewGroup) {
        q a = this.l.a(i);
        if (a == null) {
            return null;
        }
        View a2 = a.a(view, viewGroup, this.i.i());
        if (a2 == null) {
            com.inmobi.commons.core.c.a.a().a("ads", "StrandInflationFailed", new HashMap());
            Logger.a(InternalLogLevel.INTERNAL, a, "Error inflating view even with a valid data model!");
            return null;
        }
        View view2;
        WeakReference weakReference = (WeakReference) this.f.get(a);
        if (weakReference != null) {
            view2 = (View) weakReference.get();
        } else {
            view2 = null;
        }
        if (!a2.equals(view2)) {
            a(view2);
            a(a2);
            this.f.put(a, new WeakReference(a2));
            this.g.put(a2, a);
        }
        return a2;
    }

    private void a(View view) {
        if (view != null) {
            q qVar = (q) this.g.get(view);
            if (qVar != null) {
                qVar.a(view);
                this.g.remove(view);
                this.f.remove(qVar);
            }
        }
    }

    public Object a(int i) {
        return this.l.a(i);
    }

    public boolean b(int i) {
        return this.l.d(i);
    }

    public void c(int i) {
        this.n = this.l.j(i);
        if (this.q) {
            f();
        }
    }

    public int c() {
        return 1;
    }

    public int d(int i) {
        return b(i) ? 1 : 0;
    }

    public int e(int i) {
        return this.l.g(i);
    }

    public int f(int i) {
        return this.l.h(i);
    }

    public int g(int i) {
        return this.l.i(i);
    }

    public int h(int i) {
        return this.l.j(i);
    }

    public void a(int i, int i2) {
        this.o = i;
        this.p = Math.min(i2, i + 100);
        f();
    }

    public int b(int i, int i2) {
        int[] b = this.l.b();
        int h = this.l.h(i);
        int h2 = this.l.h(i2);
        ArrayList arrayList = new ArrayList();
        for (int length = b.length - 1; length >= 0; length--) {
            int i3 = b[length];
            if (i3 >= h && i3 < h2) {
                arrayList.add(Integer.valueOf(i3));
                if (i3 < this.o) {
                    this.o--;
                }
                this.n--;
            }
        }
        int a = this.l.a(h, h2);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.j.onAdRemoved(((Integer) it.next()).intValue());
        }
        return a;
    }

    public void d() {
        b(0, this.n);
        for (q qVar : this.f.keySet()) {
            if (qVar != null) {
                qVar.c();
            }
        }
        this.f.clear();
        this.g.clear();
        this.i.f();
    }

    public void i(int i) {
        this.l.e(i);
    }

    public void j(int i) {
        this.l.f(i);
    }

    public void e() {
        this.d.removeMessages(0);
        for (q qVar : this.f.keySet()) {
            if (qVar != null) {
                qVar.c();
            }
        }
        this.i.h();
        this.f.clear();
        this.g.clear();
        this.l.a();
    }

    private boolean c(int i, int i2) {
        int i3 = i2 - 1;
        while (i <= i3 && i != -1 && i < this.n) {
            if (this.l.b(i)) {
                if (!k(i)) {
                    return false;
                }
                i3++;
            }
            i = this.l.c(i);
        }
        return true;
    }

    private void f() {
        if (!this.t) {
            this.t = true;
            this.d.post(this.e);
        }
    }

    private boolean k(int i) {
        q g = this.i.g();
        if (g == null) {
            return false;
        }
        this.l.a(g, i);
        this.n++;
        this.j.onAdLoadSucceeded(i);
        return true;
    }

    private void a(InMobiClientPositioning inMobiClientPositioning) {
        ae a = ae.a(inMobiClientPositioning);
        if (this.r) {
            a(a);
        } else {
            this.m = a;
        }
        this.s = true;
    }

    private void a(ae aeVar) {
        b(0, this.n);
        this.l = aeVar;
        g();
        this.q = true;
    }

    private void g() {
        if (c(this.o, this.p)) {
            c(this.p, this.p + 10);
        }
    }
}
