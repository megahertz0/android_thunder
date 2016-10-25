package android.support.v7.widget;

import android.support.v4.animation.AnimatorCompatHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.widget.RecyclerView.t;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// compiled from: DefaultItemAnimator.java
public final class af extends cb {
    ArrayList<ArrayList<t>> a;
    ArrayList<ArrayList<b>> b;
    ArrayList<ArrayList<a>> c;
    ArrayList<t> d;
    ArrayList<t> e;
    ArrayList<t> f;
    ArrayList<t> g;
    private ArrayList<t> n;
    private ArrayList<t> o;
    private ArrayList<b> p;
    private ArrayList<a> q;

    // compiled from: DefaultItemAnimator.java
    private static class a {
        public t a;
        public t b;
        public int c;
        public int d;
        public int e;
        public int f;

        private a(t tVar, t tVar2) {
            this.a = tVar;
            this.b = tVar2;
        }

        private a(t tVar, t tVar2, int i, int i2, int i3, int i4) {
            this(tVar, tVar2);
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
        }

        public final String toString() {
            return new StringBuilder("ChangeInfo{oldHolder=").append(this.a).append(", newHolder=").append(this.b).append(", fromX=").append(this.c).append(", fromY=").append(this.d).append(", toX=").append(this.e).append(", toY=").append(this.f).append('}').toString();
        }
    }

    // compiled from: DefaultItemAnimator.java
    private static class b {
        public t a;
        public int b;
        public int c;
        public int d;
        public int e;

        private b(t tVar, int i, int i2, int i3, int i4) {
            this.a = tVar;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    // compiled from: DefaultItemAnimator.java
    private static class c implements ViewPropertyAnimatorListener {
        private c() {
        }

        public void onAnimationStart(View view) {
        }

        public void onAnimationEnd(View view) {
        }

        public void onAnimationCancel(View view) {
        }
    }

    public af() {
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.p = new ArrayList();
        this.q = new ArrayList();
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.g = new ArrayList();
    }

    public final void a() {
        int i;
        int i2;
        int i3;
        int i4 = !this.n.isEmpty() ? 1 : 0;
        if (this.p.isEmpty()) {
            i = 0;
        } else {
            i = 1;
        }
        if (this.q.isEmpty()) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        if (this.o.isEmpty()) {
            i3 = 0;
        } else {
            i3 = 1;
        }
        if (i4 != 0 || i != 0 || i3 != 0 || i2 != 0) {
            ArrayList arrayList;
            Runnable agVar;
            Iterator it = this.n.iterator();
            while (it.hasNext()) {
                t tVar = (t) it.next();
                ViewPropertyAnimatorCompat animate = ViewCompat.animate(tVar.itemView);
                this.f.add(tVar);
                animate.setDuration(this.j).alpha(AutoScrollHelper.RELATIVE_UNSPECIFIED).setListener(new aj(this, tVar, animate)).start();
            }
            this.n.clear();
            if (i != 0) {
                arrayList = new ArrayList();
                arrayList.addAll(this.p);
                this.b.add(arrayList);
                this.p.clear();
                agVar = new ag(this, arrayList);
                if (i4 != 0) {
                    ViewCompat.postOnAnimationDelayed(((b) arrayList.get(0)).a.itemView, agVar, this.j);
                } else {
                    agVar.run();
                }
            }
            if (i2 != 0) {
                arrayList = new ArrayList();
                arrayList.addAll(this.q);
                this.c.add(arrayList);
                this.q.clear();
                agVar = new ah(this, arrayList);
                if (i4 != 0) {
                    ViewCompat.postOnAnimationDelayed(((a) arrayList.get(0)).a.itemView, agVar, this.j);
                } else {
                    agVar.run();
                }
            }
            if (i3 != 0) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.o);
                this.a.add(arrayList2);
                this.o.clear();
                Runnable aiVar = new ai(this, arrayList2);
                if (i4 == 0 && i == 0 && i2 == 0) {
                    aiVar.run();
                    return;
                }
                long j;
                long j2;
                long j3;
                if (i4 != 0) {
                    j = this.j;
                } else {
                    j = 0;
                }
                if (i != 0) {
                    j2 = this.k;
                } else {
                    j2 = 0;
                }
                if (i2 != 0) {
                    j3 = this.l;
                } else {
                    j3 = 0;
                }
                ViewCompat.postOnAnimationDelayed(((t) arrayList2.get(0)).itemView, aiVar, j + Math.max(j2, j3));
            }
        }
    }

    public final boolean a(t tVar) {
        g(tVar);
        this.n.add(tVar);
        return true;
    }

    public final boolean b(t tVar) {
        g(tVar);
        ViewCompat.setAlpha(tVar.itemView, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        this.o.add(tVar);
        return true;
    }

    public final boolean a(t tVar, int i, int i2, int i3, int i4) {
        View view = tVar.itemView;
        int translationX = (int) (((float) i) + ViewCompat.getTranslationX(tVar.itemView));
        int translationY = (int) (((float) i2) + ViewCompat.getTranslationY(tVar.itemView));
        g(tVar);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            e(tVar);
            return false;
        }
        if (i5 != 0) {
            ViewCompat.setTranslationX(view, (float) (-i5));
        }
        if (i6 != 0) {
            ViewCompat.setTranslationY(view, (float) (-i6));
        }
        this.p.add(new b(translationX, translationY, i3, i4, (byte) 0));
        return true;
    }

    public final boolean a(t tVar, t tVar2, int i, int i2, int i3, int i4) {
        if (tVar == tVar2) {
            return a(tVar, i, i2, i3, i4);
        }
        float translationX = ViewCompat.getTranslationX(tVar.itemView);
        float translationY = ViewCompat.getTranslationY(tVar.itemView);
        float alpha = ViewCompat.getAlpha(tVar.itemView);
        g(tVar);
        int i5 = (int) (((float) (i3 - i)) - translationX);
        int i6 = (int) (((float) (i4 - i2)) - translationY);
        ViewCompat.setTranslationX(tVar.itemView, translationX);
        ViewCompat.setTranslationY(tVar.itemView, translationY);
        ViewCompat.setAlpha(tVar.itemView, alpha);
        if (tVar2 != null) {
            g(tVar2);
            ViewCompat.setTranslationX(tVar2.itemView, (float) (-i5));
            ViewCompat.setTranslationY(tVar2.itemView, (float) (-i6));
            ViewCompat.setAlpha(tVar2.itemView, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        }
        this.q.add(new a(tVar2, i, i2, i3, i4, (byte) 0));
        return true;
    }

    private void a(List<a> list, t tVar) {
        for (int size = list.size() - 1; size >= 0; size--) {
            a aVar = (a) list.get(size);
            if (a(aVar, tVar) && aVar.a == null && aVar.b == null) {
                list.remove(aVar);
            }
        }
    }

    private void a(a aVar) {
        if (aVar.a != null) {
            a(aVar, aVar.a);
        }
        if (aVar.b != null) {
            a(aVar, aVar.b);
        }
    }

    private boolean a(a aVar, t tVar) {
        if (aVar.b == tVar) {
            aVar.b = null;
        } else if (aVar.a != tVar) {
            return false;
        } else {
            aVar.a = null;
        }
        ViewCompat.setAlpha(tVar.itemView, 1.0f);
        ViewCompat.setTranslationX(tVar.itemView, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        ViewCompat.setTranslationY(tVar.itemView, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        e(tVar);
        return true;
    }

    public final void c(t tVar) {
        int size;
        View view = tVar.itemView;
        ViewCompat.animate(view).cancel();
        for (size = this.p.size() - 1; size >= 0; size--) {
            if (((b) this.p.get(size)).a == tVar) {
                ViewCompat.setTranslationY(view, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                ViewCompat.setTranslationX(view, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                e(tVar);
                this.p.remove(size);
            }
        }
        a(this.q, tVar);
        if (this.n.remove(tVar)) {
            ViewCompat.setAlpha(view, 1.0f);
            e(tVar);
        }
        if (this.o.remove(tVar)) {
            ViewCompat.setAlpha(view, 1.0f);
            e(tVar);
        }
        for (size = this.c.size() - 1; size >= 0; size--) {
            List list = (ArrayList) this.c.get(size);
            a(list, tVar);
            if (list.isEmpty()) {
                this.c.remove(size);
            }
        }
        for (int size2 = this.b.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.b.get(size2);
            int size3 = arrayList.size() - 1;
            while (size3 >= 0) {
                if (((b) arrayList.get(size3)).a == tVar) {
                    ViewCompat.setTranslationY(view, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                    ViewCompat.setTranslationX(view, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                    e(tVar);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.b.remove(size2);
                    }
                } else {
                    size3--;
                }
            }
        }
        for (size = this.a.size() - 1; size >= 0; size--) {
            arrayList = (ArrayList) this.a.get(size);
            if (arrayList.remove(tVar)) {
                ViewCompat.setAlpha(view, 1.0f);
                e(tVar);
                if (arrayList.isEmpty()) {
                    this.a.remove(size);
                }
            }
        }
        this.f.remove(tVar);
        this.d.remove(tVar);
        this.g.remove(tVar);
        this.e.remove(tVar);
        c();
    }

    private void g(t tVar) {
        AnimatorCompatHelper.clearInterpolator(tVar.itemView);
        c(tVar);
    }

    public final boolean b() {
        return (this.o.isEmpty() && this.q.isEmpty() && this.p.isEmpty() && this.n.isEmpty() && this.e.isEmpty() && this.f.isEmpty() && this.d.isEmpty() && this.g.isEmpty() && this.b.isEmpty() && this.a.isEmpty() && this.c.isEmpty()) ? false : true;
    }

    final void c() {
        if (!b()) {
            e();
        }
    }

    public final void d() {
        int size;
        for (size = this.p.size() - 1; size >= 0; size--) {
            b bVar = (b) this.p.get(size);
            View view = bVar.a.itemView;
            ViewCompat.setTranslationY(view, AutoScrollHelper.RELATIVE_UNSPECIFIED);
            ViewCompat.setTranslationX(view, AutoScrollHelper.RELATIVE_UNSPECIFIED);
            e(bVar.a);
            this.p.remove(size);
        }
        for (size = this.n.size() - 1; size >= 0; size--) {
            e((t) this.n.get(size));
            this.n.remove(size);
        }
        for (size = this.o.size() - 1; size >= 0; size--) {
            t tVar = (t) this.o.get(size);
            ViewCompat.setAlpha(tVar.itemView, 1.0f);
            e(tVar);
            this.o.remove(size);
        }
        for (size = this.q.size() - 1; size >= 0; size--) {
            a((a) this.q.get(size));
        }
        this.q.clear();
        if (b()) {
            int size2;
            ArrayList arrayList;
            int size3;
            for (size2 = this.b.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.b.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    b bVar2 = (b) arrayList.get(size3);
                    View view2 = bVar2.a.itemView;
                    ViewCompat.setTranslationY(view2, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                    ViewCompat.setTranslationX(view2, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                    e(bVar2.a);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.b.remove(arrayList);
                    }
                }
            }
            for (size2 = this.a.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.a.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    t tVar2 = (t) arrayList.get(size3);
                    ViewCompat.setAlpha(tVar2.itemView, 1.0f);
                    e(tVar2);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.a.remove(arrayList);
                    }
                }
            }
            for (size2 = this.c.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.c.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    a((a) arrayList.get(size3));
                    if (arrayList.isEmpty()) {
                        this.c.remove(arrayList);
                    }
                }
            }
            a(this.f);
            a(this.e);
            a(this.d);
            a(this.g);
            e();
        }
    }

    private static void a(List<t> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ViewCompat.animate(((t) list.get(size)).itemView).cancel();
        }
    }

    public final boolean a(t tVar, List<Object> list) {
        return !list.isEmpty() || super.a(tVar, list);
    }
}
