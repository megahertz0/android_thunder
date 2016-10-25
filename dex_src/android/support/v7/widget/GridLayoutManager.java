package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.widget.GridLayoutManager.b;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.m;
import android.support.v7.widget.RecyclerView.q;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.Arrays;

public class GridLayoutManager extends LinearLayoutManager {
    boolean a;
    int b;
    int[] c;
    View[] d;
    final SparseIntArray e;
    final SparseIntArray f;
    b g;
    final Rect h;

    public static class LayoutParams extends android.support.v7.widget.RecyclerView.LayoutParams {
        int a;
        int b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = -1;
            this.b = 0;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.a = -1;
            this.b = 0;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.a = -1;
            this.b = 0;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = -1;
            this.b = 0;
        }
    }

    public static abstract class b {
        final SparseIntArray a;
        private boolean b;

        public b() {
            this.a = new SparseIntArray();
            this.b = false;
        }

        final int b(int i, int i2) {
            if (!this.b) {
                return a(i, i2);
            }
            int i3 = this.a.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            i3 = a(i, i2);
            this.a.put(i, i3);
            return i3;
        }

        public int a(int i, int i2) {
            if (1 == i2) {
                return 0;
            }
            int size;
            int i3;
            int i4;
            if (this.b && this.a.size() > 0) {
                size = this.a.size() - 1;
                i3 = 0;
                while (i3 <= size) {
                    int i5 = (i3 + size) >>> 1;
                    if (this.a.keyAt(i5) < i) {
                        i3 = i5 + 1;
                    } else {
                        size = i5 - 1;
                    }
                }
                size = i3 - 1;
                if (size < 0 || size >= this.a.size()) {
                    size = -1;
                } else {
                    size = this.a.keyAt(size);
                }
                if (size >= 0) {
                    i3 = this.a.get(size) + 1;
                    size++;
                    i4 = size;
                    size = i3;
                    for (i3 = i4; i3 < i; i3++) {
                        size++;
                        if (size == i2) {
                            size = 0;
                        } else if (size > i2) {
                            size = 1;
                        }
                    }
                    return size + 1 > i2 ? size : 0;
                }
            }
            size = 0;
            i3 = 0;
            i4 = size;
            size = i3;
            for (i3 = i4; i3 < i; i3++) {
                size++;
                if (size == i2) {
                    size = 0;
                } else if (size > i2) {
                    size = 1;
                }
            }
            if (size + 1 > i2) {
            }
        }

        public static int c(int i, int i2) {
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                i4++;
                if (i4 == i2) {
                    i3++;
                    i4 = 0;
                } else if (i4 > i2) {
                    i4 = 1;
                    i3++;
                }
            }
            return i4 + 1 > i2 ? i3 + 1 : i3;
        }
    }

    public static final class a extends b {
        public final int a(int i, int i2) {
            return i % i2;
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = false;
        this.b = -1;
        this.e = new SparseIntArray();
        this.f = new SparseIntArray();
        this.g = new a();
        this.h = new Rect();
        j(a(context, attributeSet, i, i2).b);
    }

    public GridLayoutManager(Context context, int i) {
        super(context);
        this.a = false;
        this.b = -1;
        this.e = new SparseIntArray();
        this.f = new SparseIntArray();
        this.g = new a();
        this.h = new Rect();
        j(i);
    }

    public GridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i2, z);
        this.a = false;
        this.b = -1;
        this.e = new SparseIntArray();
        this.f = new SparseIntArray();
        this.g = new a();
        this.h = new Rect();
        j(i);
    }

    public final void a(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.a(false);
    }

    public final int a(m mVar, q qVar) {
        if (this.i == 0) {
            return this.b;
        }
        return qVar.a() <= 0 ? 0 : a(mVar, qVar, qVar.a() - 1) + 1;
    }

    public final int b(m mVar, q qVar) {
        if (this.i == 1) {
            return this.b;
        }
        return qVar.a() <= 0 ? 0 : a(mVar, qVar, qVar.a() - 1) + 1;
    }

    public final void a(m mVar, q qVar, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            int a = a(mVar, qVar, layoutParams2.c.getLayoutPosition());
            if (this.i == 0) {
                int i = layoutParams2.a;
                int i2 = layoutParams2.b;
                boolean z = this.b > 1 && layoutParams2.b == this.b;
                accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(i, i2, a, 1, z, false));
                return;
            }
            int i3 = layoutParams2.a;
            int i4 = layoutParams2.b;
            boolean z2 = this.b > 1 && layoutParams2.b == this.b;
            accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(a, 1, i3, i4, z2, false));
            return;
        }
        super.a(view, accessibilityNodeInfoCompat);
    }

    public final void c(m mVar, q qVar) {
        if (qVar.g) {
            int n = n();
            for (int i = 0; i < n; i++) {
                LayoutParams layoutParams = (LayoutParams) e(i).getLayoutParams();
                int layoutPosition = layoutParams.c.getLayoutPosition();
                this.e.put(layoutPosition, layoutParams.b);
                this.f.put(layoutPosition, layoutParams.a);
            }
        }
        super.c(mVar, qVar);
        this.e.clear();
        this.f.clear();
        if (!qVar.g) {
            this.a = false;
        }
    }

    public final void a(int i, int i2) {
        this.g.a.clear();
    }

    public final void a() {
        this.g.a.clear();
    }

    public final void b(int i, int i2) {
        this.g.a.clear();
    }

    public final void c(int i, int i2) {
        this.g.a.clear();
    }

    public final void d(int i, int i2) {
        this.g.a.clear();
    }

    public final android.support.v7.widget.RecyclerView.LayoutParams b() {
        return this.i == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    public final android.support.v7.widget.RecyclerView.LayoutParams a(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public final android.support.v7.widget.RecyclerView.LayoutParams a(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public final boolean a(android.support.v7.widget.RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void a(Rect rect, int i, int i2) {
        if (this.c == null) {
            super.a(rect, i, i2);
        }
        int q = q() + o();
        int p = p() + r();
        if (this.i == 1) {
            p = a(i2, p + rect.height(), ViewCompat.getMinimumHeight(this.q));
            q = a(i, q + this.c[this.c.length - 1], ViewCompat.getMinimumWidth(this.q));
        } else {
            q = a(i, q + rect.width(), ViewCompat.getMinimumWidth(this.q));
            p = a(i2, p + this.c[this.c.length - 1], ViewCompat.getMinimumHeight(this.q));
        }
        i(q, p);
    }

    private void i(int i) {
        int i2 = 0;
        int[] iArr = this.c;
        int i3 = this.b;
        if (!(iArr != null && iArr.length == i3 + 1 && iArr[iArr.length - 1] == i)) {
            iArr = new int[(i3 + 1)];
        }
        iArr[0] = 0;
        int i4 = i / i3;
        int i5 = i % i3;
        int i6 = 0;
        for (int i7 = 1; i7 <= i3; i7++) {
            int i8;
            i2 += i5;
            if (i2 <= 0 || i3 - i2 >= i5) {
                i8 = i4;
            } else {
                i8 = i4 + 1;
                i2 -= i3;
            }
            i6 += i8;
            iArr[i7] = i6;
        }
        this.c = iArr;
    }

    final void a(m mVar, q qVar, a aVar, int i) {
        Object obj = 1;
        super.a(mVar, qVar, aVar, i);
        u();
        if (qVar.a() > 0 && !qVar.g) {
            if (i != 1) {
                obj = null;
            }
            int b = b(mVar, qVar, aVar.a);
            if (obj != null) {
                while (b > 0 && aVar.a > 0) {
                    aVar.a--;
                    b = b(mVar, qVar, aVar.a);
                }
            } else {
                int a = qVar.a() - 1;
                int i2 = aVar.a;
                while (i2 < a) {
                    int b2 = b(mVar, qVar, i2 + 1);
                    if (b2 <= b) {
                        break;
                    }
                    i2++;
                    b = b2;
                }
                aVar.a = i2;
            }
        }
        v();
    }

    private void v() {
        if (this.d == null || this.d.length != this.b) {
            this.d = new View[this.b];
        }
    }

    public final int a(int i, m mVar, q qVar) {
        u();
        v();
        return super.a(i, mVar, qVar);
    }

    public final int b(int i, m mVar, q qVar) {
        u();
        v();
        return super.b(i, mVar, qVar);
    }

    final View a(m mVar, q qVar, int i, int i2, int i3) {
        int i4;
        View view;
        View view2;
        Object obj = null;
        h();
        int b = this.j.b();
        int c = this.j.c();
        if (i2 > i) {
            i4 = 1;
        } else {
            i4 = -1;
        }
        Object obj2 = null;
        while (i != i2) {
            View view3;
            View e = e(i);
            int a = a(e);
            if (a >= 0 && a < i3 && b(mVar, qVar, a) == 0) {
                if (((android.support.v7.widget.RecyclerView.LayoutParams) e.getLayoutParams()).c.isRemoved()) {
                    if (view == null) {
                        view3 = view2;
                        i += i4;
                        view2 = view3;
                        view = e;
                    }
                } else if (this.j.a(e) < c && this.j.b(e) >= b) {
                    return e;
                } else {
                    if (view2 == null) {
                        view3 = e;
                        e = view;
                        i += i4;
                        view2 = view3;
                        view = e;
                    }
                }
            }
            view3 = view2;
            e = view;
            i += i4;
            view2 = view3;
            view = e;
        }
        return view2 != null ? view2 : view;
    }

    private int b(m mVar, q qVar, int i) {
        if (!qVar.g) {
            return this.g.b(i, this.b);
        }
        int i2 = this.f.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        i2 = mVar.a(i);
        return i2 == -1 ? 0 : this.g.b(i2, this.b);
    }

    private int c(m mVar, q qVar, int i) {
        if (!qVar.g) {
            return 1;
        }
        int i2 = this.e.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        return mVar.a(i) == -1 ? 1 : 1;
    }

    final void a(m mVar, q qVar, c cVar, b bVar) {
        int i;
        View a;
        int h = this.j.h();
        if (h != 1073741824) {
            int i2 = 1;
        } else {
            Object obj = null;
        }
        if (n() > 0) {
            int i3 = this.c[this.b];
        } else {
            Object obj2 = null;
        }
        if (obj != null) {
            u();
        }
        if (cVar.e == 1) {
            int i4 = 1;
        } else {
            Object obj3 = null;
        }
        int i5 = this.b;
        if (r17) {
            i = 0;
        } else {
            i5 = b(mVar, qVar, cVar.d) + c(mVar, qVar, cVar.d);
            i = 0;
        }
        while (i < this.b && cVar.a(qVar) && i5 > 0) {
            int i6 = cVar.d;
            int c = c(mVar, qVar, i6);
            if (c <= this.b) {
                i5 -= c;
                if (i5 < 0) {
                    break;
                }
                a = cVar.a(mVar);
                if (a == null) {
                    break;
                }
                this.d[i] = a;
                i++;
            } else {
                throw new IllegalArgumentException(new StringBuilder("Item at position ").append(i6).append(" requires ").append(c).append(" spans but GridLayoutManager has only ").append(this.b).append(" spans.").toString());
            }
        }
        if (i == 0) {
            bVar.b = true;
            return;
        }
        int a2;
        int i7;
        Object obj4 = null;
        float f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
        a(mVar, qVar, i, r17);
        int i8 = 0;
        while (i8 < i) {
            a = this.d[i8];
            if (cVar.k == null) {
                if (r17) {
                    super.a(a, -1, false);
                } else {
                    super.a(a, 0, false);
                }
            } else if (r17) {
                super.a(a, -1, true);
            } else {
                super.a(a, 0, true);
            }
            LayoutParams layoutParams = (LayoutParams) a.getLayoutParams();
            c = a(this.c[layoutParams.a + layoutParams.b] - this.c[layoutParams.a], h, 0, this.i == 0 ? layoutParams.height : layoutParams.width, false);
            a2 = a(this.j.e(), this.j.g(), 0, this.i == 1 ? layoutParams.height : layoutParams.width, true);
            if (this.i == 1) {
                a(a, c, a2, layoutParams.height == -1, false);
            } else {
                a(a, a2, c, layoutParams.width == -1, false);
            }
            i5 = this.j.c(a);
            if (i5 <= r19) {
                i5 = r19;
            }
            float d = (((float) this.j.d(a)) * 1.0f) / ((float) layoutParams.b);
            if (d <= f) {
                d = f;
            }
            i8++;
            f = d;
            int i9 = i5;
        }
        if (obj != null) {
            i(Math.max(Math.round(((float) this.b) * f), i3));
            i7 = 0;
            i2 = 0;
            while (i2 < i) {
                a = this.d[i2];
                LayoutParams layoutParams2 = (LayoutParams) a.getLayoutParams();
                c = a(this.c[layoutParams2.a + layoutParams2.b] - this.c[layoutParams2.a], 1073741824, 0, this.i == 0 ? layoutParams2.height : layoutParams2.width, false);
                a2 = a(this.j.e(), this.j.g(), 0, this.i == 1 ? layoutParams2.height : layoutParams2.width, true);
                if (this.i == 1) {
                    a(a, c, a2, false, true);
                } else {
                    a(a, a2, c, false, true);
                }
                i5 = this.j.c(a);
                if (i5 <= i7) {
                    i5 = i7;
                }
                i2++;
                i7 = i5;
            }
        } else {
            i7 = i9;
        }
        a2 = MeasureSpec.makeMeasureSpec(i7, 1073741824);
        for (i2 = 0; i2 < i; i2++) {
            a = this.d[i2];
            if (this.j.c(a) != i7) {
                layoutParams2 = (LayoutParams) a.getLayoutParams();
                c = a(this.c[layoutParams2.a + layoutParams2.b] - this.c[layoutParams2.a], 1073741824, 0, this.i == 0 ? layoutParams2.height : layoutParams2.width, false);
                if (this.i == 1) {
                    a(a, c, a2, true, true);
                } else {
                    a(a, a2, c, true, true);
                }
            }
        }
        bVar.a = i7;
        a2 = 0;
        c = 0;
        i6 = 0;
        i5 = 0;
        if (this.i == 1) {
            if (cVar.f == -1) {
                i5 = cVar.b;
                i6 = i5 - i7;
            } else {
                i6 = cVar.b;
                i5 = i6 + i7;
            }
        } else if (cVar.f == -1) {
            c = cVar.b;
            a2 = c - i7;
        } else {
            a2 = cVar.b;
            c = a2 + i7;
        }
        int i10 = a2;
        a2 = c;
        c = i6;
        i6 = i5;
        for (int i11 = 0; i11 < i; i11++) {
            View view = this.d[i11];
            layoutParams2 = (LayoutParams) view.getLayoutParams();
            if (this.i != 1) {
                c = this.c[layoutParams2.a] + p();
                i6 = this.j.d(view) + c;
            } else if (g()) {
                a2 = o() + this.c[layoutParams2.a + layoutParams2.b];
                i10 = a2 - this.j.d(view);
            } else {
                i10 = this.c[layoutParams2.a] + o();
                a2 = this.j.d(view) + i10;
            }
            a(view, layoutParams2.leftMargin + i10, layoutParams2.topMargin + c, a2 - layoutParams2.rightMargin, i6 - layoutParams2.bottomMargin);
            if (layoutParams2.c.isRemoved() || layoutParams2.c.isUpdated()) {
                bVar.c = true;
            }
            bVar.d |= view.isFocusable();
        }
        Arrays.fill(this.d, null);
    }

    private void a(View view, int i, int i2, boolean z, boolean z2) {
        boolean a;
        a(view, this.h);
        android.support.v7.widget.RecyclerView.LayoutParams layoutParams = (android.support.v7.widget.RecyclerView.LayoutParams) view.getLayoutParams();
        if (z || this.i == 1) {
            i = c(i, layoutParams.leftMargin + this.h.left, layoutParams.rightMargin + this.h.right);
        }
        if (z || this.i == 0) {
            i2 = c(i2, layoutParams.topMargin + this.h.top, layoutParams.bottomMargin + this.h.bottom);
        }
        if (!z2) {
            a = a(view, i, i2, layoutParams);
        } else if (this.v && h.b(view.getMeasuredWidth(), i, layoutParams.width) && h.b(view.getMeasuredHeight(), i2, layoutParams.height)) {
            a = false;
        } else {
            a = true;
        }
        if (a) {
            view.measure(i, i2);
        }
    }

    private static int c(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? MeasureSpec.makeMeasureSpec(Math.max(0, (MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    private void a(m mVar, q qVar, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (z) {
            i2 = 1;
            i3 = 0;
        } else {
            i3 = i - 1;
            i2 = -1;
            i = -1;
        }
        if (this.i == 1 && g()) {
            i4 = -1;
            i5 = this.b - 1;
            i6 = i3;
        } else {
            i4 = 1;
            i5 = 0;
            i6 = i3;
        }
        while (i6 != i) {
            View view = this.d[i6];
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.b = c(mVar, qVar, a(view));
            if (i4 != -1 || layoutParams.b <= 1) {
                layoutParams.a = i5;
            } else {
                layoutParams.a = i5 - (layoutParams.b - 1);
            }
            i5 += layoutParams.b * i4;
            i6 += i2;
        }
    }

    private void j(int i) {
        if (i != this.b) {
            this.a = true;
            if (i <= 0) {
                throw new IllegalArgumentException(new StringBuilder("Span count should be at least 1. Provided ").append(i).toString());
            }
            this.b = i;
            this.g.a.clear();
        }
    }

    public final View a(View view, int i, m mVar, q qVar) {
        View b = b(view);
        if (b == null) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) b.getLayoutParams();
        int a = layoutParams.a;
        int a2 = layoutParams.a + layoutParams.b;
        if (super.a(view, i, mVar, qVar) == null) {
            return null;
        }
        int n;
        int i2;
        int i3;
        View view2;
        if (((d(i) == 1 ? 1 : null) != this.k ? 1 : null) != null) {
            n = n() - 1;
            i2 = -1;
            i3 = -1;
        } else {
            Object obj = null;
            i2 = 1;
            i3 = n();
        }
        Object obj2 = (this.i == 1 && g()) ? 1 : null;
        Object obj3 = null;
        Object obj4 = -1;
        Object obj5 = null;
        int i4 = n;
        while (i4 != i3) {
            View e = e(i4);
            if (e == b) {
                break;
            }
            int a3;
            int i5;
            View view3;
            if (e.isFocusable()) {
                layoutParams = (LayoutParams) e.getLayoutParams();
                int a4 = layoutParams.a;
                int a5 = layoutParams.a + layoutParams.b;
                if (a4 == a && a5 == a2) {
                    return e;
                }
                Object obj6 = null;
                if (view2 == null) {
                    obj6 = 1;
                } else {
                    int min = Math.min(a5, a2) - Math.max(a4, a);
                    if (min > a3) {
                        obj6 = 1;
                    } else if (min == a3) {
                        if (obj2 == (a4 > i5 ? 1 : null)) {
                            obj6 = 1;
                        }
                    }
                }
                if (obj6 != null) {
                    a3 = layoutParams.a;
                    n = Math.min(a5, a2) - Math.max(a4, a);
                    view3 = e;
                    view2 = view3;
                    i4 += i2;
                    i5 = a3;
                    a3 = n;
                }
            }
            n = a3;
            a3 = i5;
            view3 = view2;
            view2 = view3;
            i4 += i2;
            i5 = a3;
            a3 = n;
        }
        return view2;
    }

    public final boolean c() {
        return this.n == null && !this.a;
    }

    private void u() {
        int q;
        if (this.i == 1) {
            q = (this.y - q()) - o();
        } else {
            q = (this.z - r()) - p();
        }
        i(q);
    }

    private int a(m mVar, q qVar, int i) {
        if (!qVar.g) {
            return b.c(i, this.b);
        }
        int a = mVar.a(i);
        return a == -1 ? 0 : b.c(a, this.b);
    }
}
