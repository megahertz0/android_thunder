package com.inmobi.ads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.inmobi.ads.NativeStrandAsset.AssetType;
import java.util.Iterator;

// compiled from: NativeStrandLayoutInflater.java
final class ac implements a {
    private static final String a;
    private static Handler i;
    private final Context b;
    private final x c;
    private final y d;
    private b e;
    private a f;
    private int g;
    private aj h;
    private boolean j;

    // compiled from: NativeStrandLayoutInflater.java
    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ ag a;
        final /* synthetic */ ViewGroup b;

        AnonymousClass_1(ag agVar, ViewGroup viewGroup) {
            this.a = agVar;
            this.b = viewGroup;
        }

        public void run() {
            if (!ac.this.j) {
                ac.this.a(this.a, this.b, ac.this.a());
            }
        }
    }

    // compiled from: NativeStrandLayoutInflater.java
    class AnonymousClass_2 implements OnClickListener {
        final /* synthetic */ NativeStrandAsset a;

        AnonymousClass_2(NativeStrandAsset nativeStrandAsset) {
            this.a = nativeStrandAsset;
        }

        public void onClick(View view) {
            if (ac.this.f != null) {
                ac.this.f.a(this.a);
            }
        }
    }

    // compiled from: NativeStrandLayoutInflater.java
    static interface a {
        void a(NativeStrandAsset nativeStrandAsset);
    }

    // compiled from: NativeStrandLayoutInflater.java
    static interface b {
        void a(int i, NativeStrandAsset nativeStrandAsset);
    }

    static {
        a = ac.class.getSimpleName();
        i = new Handler(Looper.getMainLooper());
    }

    ac(Context context, x xVar, b bVar, a aVar) {
        this.g = 0;
        this.j = false;
        this.b = context;
        this.c = xVar;
        this.h = aj.a(context);
        this.e = bVar;
        this.f = aVar;
        this.d = new y(xVar, this);
    }

    public final ag a(ag agVar, ViewGroup viewGroup) {
        View view;
        if (agVar == null) {
            ag agVar2 = (ag) this.h.a(this.b, this.c.a());
        } else {
            view = agVar;
        }
        if (view.getChildCount() > 0) {
            aj.a(this.b).a((ViewGroup) view);
            aj.a(view, this.c.a().b());
        }
        view.setLayoutParams(aj.a(this.c.a(), viewGroup));
        i.post(new AnonymousClass_1(view, viewGroup));
        return view;
    }

    public final ViewGroup a(ViewGroup viewGroup, ViewGroup viewGroup2, v vVar) {
        return b(viewGroup, viewGroup2, vVar);
    }

    public final ViewGroup a(ViewGroup viewGroup, v vVar) {
        ViewGroup viewGroup2 = (ViewGroup) this.h.a(this.b, (NativeStrandAsset) vVar);
        viewGroup2.setLayoutParams(aj.a((NativeStrandAsset) vVar, viewGroup));
        return viewGroup2;
    }

    public final int a(int i) {
        this.g = i;
        if (this.e != null) {
            this.e.a(i, this.c.a(i));
        }
        return c();
    }

    private ViewGroup b(ViewGroup viewGroup, ViewGroup viewGroup2, v vVar) {
        Iterator it = vVar.iterator();
        while (it.hasNext()) {
            NativeStrandAsset nativeStrandAsset = (NativeStrandAsset) it.next();
            if (AssetType.ASSET_TYPE_CONTAINER != nativeStrandAsset.a()) {
                View a = aj.a(this.b).a(this.b, nativeStrandAsset);
                if (a != null) {
                    viewGroup.addView(a, aj.a(nativeStrandAsset, viewGroup));
                    if (nativeStrandAsset.g()) {
                        a.setOnClickListener(new AnonymousClass_2(nativeStrandAsset));
                    }
                }
            } else if (nativeStrandAsset.c().equalsIgnoreCase("card_scrollable")) {
                ao aoVar = (ao) this.h.a(this.b, nativeStrandAsset);
                aoVar.a((v) nativeStrandAsset, this.d, this.g, c(), this);
                viewGroup.addView(aoVar, aj.a(nativeStrandAsset, viewGroup));
            } else {
                viewGroup.addView(b((ViewGroup) aj.a(this.b).a(this.b, nativeStrandAsset), viewGroup, (v) nativeStrandAsset), aj.a(nativeStrandAsset, viewGroup));
            }
        }
        return viewGroup;
    }

    private int c() {
        if (this.g == 0) {
            return GravityCompat.START;
        }
        return this.c.d() + -1 == this.g ? GravityCompat.END : 1;
    }

    final int a() {
        return this.g;
    }

    final void a(View view) {
        this.h.a(view);
    }

    final void b() {
        this.j = true;
        this.d.a();
    }
}
