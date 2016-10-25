package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.m.a;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

// compiled from: BaseMenuPresenter.java
public abstract class b implements m {
    public Context a;
    public Context b;
    public f c;
    protected LayoutInflater d;
    protected LayoutInflater e;
    public a f;
    public n g;
    public int h;
    private int i;
    private int j;

    public abstract void a(h hVar, n.a aVar);

    public b(Context context, int i, int i2) {
        this.a = context;
        this.d = LayoutInflater.from(context);
        this.i = i;
        this.j = i2;
    }

    public void a(Context context, f fVar) {
        this.b = context;
        this.e = LayoutInflater.from(this.b);
        this.c = fVar;
    }

    public n a(ViewGroup viewGroup) {
        if (this.g == null) {
            this.g = (n) this.d.inflate(this.i, viewGroup, false);
            this.g.a(this.c);
            a(true);
        }
        return this.g;
    }

    public void a(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.g;
        if (viewGroup != null) {
            int i;
            if (this.c != null) {
                this.c.i();
                ArrayList h = this.c.h();
                int size = h.size();
                int i2 = 0;
                i = 0;
                while (i2 < size) {
                    int i3;
                    h hVar = (h) h.get(i2);
                    if (c(hVar)) {
                        View childAt = viewGroup.getChildAt(i);
                        h itemData = childAt instanceof n.a ? ((n.a) childAt).getItemData() : null;
                        View a = a(hVar, childAt, viewGroup);
                        if (hVar != itemData) {
                            a.setPressed(false);
                            ViewCompat.jumpDrawablesToCurrentState(a);
                        }
                        if (a != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) a.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(a);
                            }
                            ((ViewGroup) this.g).addView(a, i);
                        }
                        i3 = i + 1;
                    } else {
                        i3 = i;
                    }
                    i2++;
                    i = i3;
                }
            } else {
                i = 0;
            }
            while (i >= viewGroup.getChildCount() || a(viewGroup, i)) {
            }
            i++;
        }
    }

    public boolean a(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    public View a(h hVar, View view, ViewGroup viewGroup) {
        n.a aVar;
        if (view instanceof n.a) {
            aVar = (n.a) view;
        } else {
            aVar = (n.a) this.d.inflate(this.j, viewGroup, false);
        }
        a(hVar, aVar);
        return (View) aVar;
    }

    public boolean c(h hVar) {
        return true;
    }

    public void a(f fVar, boolean z) {
        if (this.f != null) {
            this.f.a(fVar, z);
        }
    }

    public boolean a(q qVar) {
        return this.f != null ? this.f.a(qVar) : false;
    }

    public boolean a() {
        return false;
    }

    public final boolean a(h hVar) {
        return false;
    }

    public final boolean b(h hVar) {
        return false;
    }

    public final int b() {
        return this.h;
    }
}
