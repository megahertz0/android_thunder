package com.bumptech.glide.f.b;

import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.WindowManager;
import com.bumptech.glide.f.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

// compiled from: ViewTarget.java
public abstract class k<T extends View, Z> extends a<Z> {
    private static boolean b;
    private static Integer c;
    protected final T a;
    private final a d;

    // compiled from: ViewTarget.java
    private static class a {
        final View a;
        final List<h> b;
        a c;
        private Point d;

        // compiled from: ViewTarget.java
        private static class a implements OnPreDrawListener {
            private final WeakReference<a> a;

            public a(a aVar) {
                this.a = new WeakReference(aVar);
            }

            public final boolean onPreDraw() {
                if (Log.isLoggable("ViewTarget", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                    new StringBuilder("OnGlobalLayoutListener called listener=").append(this);
                }
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    a.a(aVar);
                }
                return true;
            }
        }

        public a(View view) {
            this.b = new ArrayList();
            this.a = view;
        }

        final int a() {
            LayoutParams layoutParams = this.a.getLayoutParams();
            if (a(this.a.getHeight())) {
                return this.a.getHeight();
            }
            return layoutParams != null ? a(layoutParams.height, true) : 0;
        }

        final int b() {
            LayoutParams layoutParams = this.a.getLayoutParams();
            if (a(this.a.getWidth())) {
                return this.a.getWidth();
            }
            return layoutParams != null ? a(layoutParams.width, false) : 0;
        }

        private int a(int i, boolean z) {
            if (i != -2) {
                return i;
            }
            if (this.d == null) {
                Display defaultDisplay = ((WindowManager) this.a.getContext().getSystemService("window")).getDefaultDisplay();
                if (VERSION.SDK_INT >= 13) {
                    this.d = new Point();
                    defaultDisplay.getSize(this.d);
                } else {
                    this.d = new Point(defaultDisplay.getWidth(), defaultDisplay.getHeight());
                }
            }
            Point point = this.d;
            return z ? point.y : point.x;
        }

        static boolean a(int i) {
            return i > 0 || i == -2;
        }

        static /* synthetic */ void a(a aVar) {
            if (!aVar.b.isEmpty()) {
                int b = aVar.b();
                int a = aVar.a();
                if (a(b) && a(a)) {
                    for (h hVar : aVar.b) {
                        hVar.a(b, a);
                    }
                    aVar.b.clear();
                    ViewTreeObserver viewTreeObserver = aVar.a.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeOnPreDrawListener(aVar.c);
                    }
                    aVar.c = null;
                }
            }
        }
    }

    static {
        b = false;
        c = null;
    }

    public k(T t) {
        if (t == null) {
            throw new NullPointerException("View must not be null!");
        }
        this.a = t;
        this.d = new a(t);
    }

    public final T a() {
        return this.a;
    }

    public final void a(h hVar) {
        a aVar = this.d;
        int b = aVar.b();
        int a = aVar.a();
        if (a.a(b) && a.a(a)) {
            hVar.a(b, a);
            return;
        }
        if (!aVar.b.contains(hVar)) {
            aVar.b.add(hVar);
        }
        if (aVar.c == null) {
            ViewTreeObserver viewTreeObserver = aVar.a.getViewTreeObserver();
            aVar.c = new a(aVar);
            viewTreeObserver.addOnPreDrawListener(aVar.c);
        }
    }

    public String toString() {
        return new StringBuilder("Target for: ").append(this.a).toString();
    }

    public final void a(b bVar) {
        if (c == null) {
            b = true;
            this.a.setTag(bVar);
            return;
        }
        this.a.setTag(c.intValue(), bVar);
    }

    public final b e() {
        Object tag;
        if (c == null) {
            tag = this.a.getTag();
        } else {
            tag = this.a.getTag(c.intValue());
        }
        if (tag == null) {
            return null;
        }
        if (tag instanceof b) {
            return (b) tag;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }
}
