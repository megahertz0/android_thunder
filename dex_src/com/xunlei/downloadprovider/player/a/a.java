package com.xunlei.downloadprovider.player.a;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.xllib.a.b;

// compiled from: AutoPlayHelper.java
public final class a {
    public ListView a;
    public int b;
    public boolean c;
    public int d;
    public int e;
    public b f;
    private int g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;

    public a() {
        this.b = 0;
        a();
    }

    public final void a() {
        this.i = b.h(BrothersApplication.a());
        this.j = b.g(BrothersApplication.a());
        this.k = b.a(BrothersApplication.a());
    }

    public final boolean a(b bVar) {
        if (bVar == null || !bVar.e() || (bVar.getPosition() == this.g && bVar.getVisibilityPercents() > 40)) {
            return false;
        }
        bVar.d();
        return true;
    }

    public static boolean a(int i) {
        return i == 2;
    }

    public final void a(AbsListView absListView, boolean z, b bVar) {
        if (!this.h && absListView.getCount() > 0) {
            this.h = true;
            new StringBuilder("traverse--isScrollUp=").append(this.c).append("|isOrder=").append(z);
            if (z) {
                a(absListView, bVar);
            } else {
                b(absListView, bVar);
            }
            this.h = false;
        }
    }

    private static void a(AbsListView absListView, b bVar) {
        int childCount = absListView.getChildCount();
        int c = c(absListView, bVar);
        if (c < 0) {
            c = 0;
        }
        while (c < childCount && !a(absListView, c)) {
            c++;
        }
    }

    private static void b(AbsListView absListView, b bVar) {
        int childCount = absListView.getChildCount();
        int c = c(absListView, bVar);
        if (c < 0) {
            c = childCount - 1;
        }
        while (c >= 0 && !a(absListView, c)) {
            c--;
        }
    }

    public static void a(AbsListView absListView) {
        int childCount = absListView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = absListView.getChildAt(i);
            if (childAt instanceof b) {
                b bVar = (b) childAt;
                if (bVar != null) {
                    bVar.d();
                }
            }
        }
    }

    private static boolean a(AbsListView absListView, int i) {
        if (i < 0 || i >= absListView.getChildCount()) {
            return false;
        }
        View childAt = absListView.getChildAt(i);
        if (childAt instanceof b) {
            b bVar = (b) childAt;
            if (bVar.f() && bVar.getVisibilityPercents() >= 50) {
                a(absListView);
                bVar.c();
                return true;
            }
        }
        return false;
    }

    private static int c(AbsListView absListView, b bVar) {
        return bVar == null ? -1 : absListView.indexOfChild(bVar.getLayout());
    }

    public final void b(b bVar) {
        this.f = bVar;
        this.g = bVar == null ? 0 : bVar.getPosition();
    }

    public final boolean b() {
        return this.f != null;
    }

    public final boolean c() {
        if (!this.k) {
            return false;
        }
        c a = c.a();
        if (a.b == 2 || a.b == 1) {
            int i = 1;
        } else {
            boolean z = false;
        }
        if (!z) {
            return false;
        }
        if (this.i) {
            if (a.b == 2 || a.b == 1) {
                i = 1;
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
        }
        if (!this.j) {
            return false;
        }
        if (a.b == 1) {
            i = 1;
        } else {
            z = false;
        }
        return z;
    }
}
