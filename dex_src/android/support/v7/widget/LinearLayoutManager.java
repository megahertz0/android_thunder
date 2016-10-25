package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.m;
import android.support.v7.widget.RecyclerView.p;
import android.support.v7.widget.RecyclerView.q;
import android.support.v7.widget.RecyclerView.t;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class LinearLayoutManager extends h {
    private c a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    int i;
    ax j;
    boolean k;
    int l;
    int m;
    SavedState n;
    final a o;

    public static class SavedState implements Parcelable {
        public static final Creator<android.support.v7.widget.LinearLayoutManager.SavedState> CREATOR;
        int a;
        int b;
        boolean c;

        SavedState(Parcel parcel) {
            boolean z = true;
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            if (parcel.readInt() != 1) {
                z = false;
            }
            this.c = z;
        }

        public SavedState(android.support.v7.widget.LinearLayoutManager.SavedState savedState) {
            this.a = savedState.a;
            this.b = savedState.b;
            this.c = savedState.c;
        }

        final boolean a() {
            return this.a >= 0;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c ? 1 : 0);
        }

        static {
            CREATOR = new as();
        }
    }

    class a {
        int a;
        int b;
        boolean c;

        a() {
        }

        final void a() {
            this.b = this.c ? LinearLayoutManager.this.j.c() : LinearLayoutManager.this.j.b();
        }

        public final String toString() {
            return new StringBuilder("AnchorInfo{mPosition=").append(this.a).append(", mCoordinate=").append(this.b).append(", mLayoutFromEnd=").append(this.c).append('}').toString();
        }

        public final void a(View view) {
            if (this.c) {
                this.b = LinearLayoutManager.this.j.b(view) + LinearLayoutManager.this.j.a();
            } else {
                this.b = LinearLayoutManager.this.j.a(view);
            }
            this.a = LinearLayoutManager.a(view);
        }
    }

    protected static class b {
        public int a;
        public boolean b;
        public boolean c;
        public boolean d;

        protected b() {
        }
    }

    static class c {
        boolean a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        boolean i;
        int j;
        List<t> k;
        boolean l;

        c() {
            this.a = true;
            this.h = 0;
            this.i = false;
            this.k = null;
        }

        final boolean a(q qVar) {
            return this.d >= 0 && this.d < qVar.a();
        }

        final View a(m mVar) {
            if (this.k != null) {
                int size = this.k.size();
                for (int i = 0; i < size; i++) {
                    View view = ((t) this.k.get(i)).itemView;
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    if (!layoutParams.c.isRemoved() && this.d == layoutParams.c.getLayoutPosition()) {
                        a(view);
                        return view;
                    }
                }
                return null;
            }
            View b = mVar.b(this.d);
            this.d += this.e;
            return b;
        }

        public final void a(View view) {
            View view2;
            View view3;
            int size = this.k.size();
            Object obj = null;
            Object obj2 = InMobiClientPositioning.NO_REPEAT;
            int i = 0;
            while (i < size) {
                int layoutPosition;
                View view4;
                int i2;
                view2 = ((t) this.k.get(i)).itemView;
                LayoutParams layoutParams = (LayoutParams) view2.getLayoutParams();
                if (!(view2 == view || layoutParams.c.isRemoved())) {
                    layoutPosition = (layoutParams.c.getLayoutPosition() - this.d) * this.e;
                    if (layoutPosition >= 0 && layoutPosition < i2) {
                        if (layoutPosition == 0) {
                            break;
                        }
                        view4 = view2;
                        i++;
                        view3 = view4;
                        i2 = layoutPosition;
                    }
                }
                layoutPosition = i2;
                view4 = view3;
                i++;
                view3 = view4;
                i2 = layoutPosition;
            }
            view2 = view3;
            if (view2 == null) {
                this.d = -1;
            } else {
                this.d = ((LayoutParams) view2.getLayoutParams()).c.getLayoutPosition();
            }
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
        this.c = false;
        this.k = false;
        this.d = false;
        this.e = true;
        this.l = -1;
        this.m = Integer.MIN_VALUE;
        this.n = null;
        this.o = new a();
        a(i);
        b(z);
        this.u = true;
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.c = false;
        this.k = false;
        this.d = false;
        this.e = true;
        this.l = -1;
        this.m = Integer.MIN_VALUE;
        this.n = null;
        this.o = new a();
        android.support.v7.widget.RecyclerView.h.a a = a(context, attributeSet, i, i2);
        a(a.a);
        b(a.c);
        a(a.d);
        this.u = true;
    }

    public LayoutParams b() {
        return new LayoutParams(-2, -2);
    }

    public final void a(RecyclerView recyclerView, m mVar) {
        super.a(recyclerView, mVar);
        if (this.f) {
            c(mVar);
            mVar.a();
        }
    }

    public final void a(AccessibilityEvent accessibilityEvent) {
        super.a(accessibilityEvent);
        if (n() > 0) {
            AccessibilityRecordCompat asRecord = AccessibilityEventCompat.asRecord(accessibilityEvent);
            asRecord.setFromIndex(j());
            asRecord.setToIndex(k());
        }
    }

    public final Parcelable d() {
        if (this.n != null) {
            return new SavedState(this.n);
        }
        Parcelable savedState = new SavedState();
        if (n() > 0) {
            h();
            boolean z = this.b ^ this.k;
            savedState.c = z;
            View x;
            if (z) {
                x = x();
                savedState.b = this.j.c() - this.j.b(x);
                savedState.a = a(x);
                return savedState;
            }
            x = w();
            savedState.a = a(x);
            savedState.b = this.j.a(x) - this.j.b();
            return savedState;
        }
        savedState.a = -1;
        return savedState;
    }

    public final void a(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.n = (SavedState) parcelable;
            l();
        }
    }

    public final boolean e() {
        return this.i == 0;
    }

    public final boolean f() {
        return this.i == 1;
    }

    public void a(boolean z) {
        a(null);
        if (this.d != z) {
            this.d = z;
            l();
        }
    }

    public final void a(int i) {
        if (i == 0 || i == 1) {
            a(null);
            if (i != this.i) {
                this.i = i;
                this.j = null;
                l();
                return;
            }
            return;
        }
        throw new IllegalArgumentException(new StringBuilder("invalid orientation:").append(i).toString());
    }

    private void u() {
        boolean z = true;
        if (this.i == 1 || !g()) {
            z = this.c;
        } else if (this.c) {
            z = false;
        }
        this.k = z;
    }

    private void b(boolean z) {
        a(null);
        if (z != this.c) {
            this.c = z;
            l();
        }
    }

    public final View b(int i) {
        int n = n();
        if (n == 0) {
            return null;
        }
        int a = i - a(e(0));
        if (a >= 0 && a < n) {
            View e = e(a);
            if (a(e) == i) {
                return e;
            }
        }
        return super.b(i);
    }

    private int g(q qVar) {
        int i;
        if (qVar.a != -1) {
            Object obj = 1;
        } else {
            i = 0;
        }
        return i != 0 ? this.j.e() : 0;
    }

    public final void a(RecyclerView recyclerView, int i) {
        p arVar = new ar(this, recyclerView.getContext());
        arVar.g = i;
        a(arVar);
    }

    public void c(m mVar, q qVar) {
        if (!(this.n == null && this.l == -1) && qVar.a() == 0) {
            c(mVar);
            return;
        }
        Object obj;
        View b;
        int b2;
        int c;
        int b3;
        int min;
        int c2;
        if (this.n != null && this.n.a()) {
            this.l = this.n.a;
        }
        h();
        this.a.a = false;
        u();
        a aVar = this.o;
        aVar.a = -1;
        aVar.b = Integer.MIN_VALUE;
        aVar.c = false;
        this.o.c = this.k ^ this.d;
        a aVar2 = this.o;
        if (qVar.g || this.l == -1) {
            obj = null;
        } else if (this.l < 0 || this.l >= qVar.a()) {
            this.l = -1;
            this.m = Integer.MIN_VALUE;
            obj = null;
        } else {
            aVar2.a = this.l;
            if (this.n == null || !this.n.a()) {
                if (this.m == Integer.MIN_VALUE) {
                    b = b(this.l);
                    if (b == null) {
                        if (n() > 0) {
                            aVar2.c = (this.l < a(e(0)) ? 1 : null) == this.k;
                        }
                        aVar2.a();
                    } else if (this.j.c(b) > this.j.e()) {
                        aVar2.a();
                    } else if (this.j.a(b) - this.j.b() < 0) {
                        aVar2.b = this.j.b();
                        aVar2.c = false;
                    } else if (this.j.c() - this.j.b(b) < 0) {
                        aVar2.b = this.j.c();
                        aVar2.c = true;
                    } else {
                        if (aVar2.c) {
                            b2 = this.j.b(b) + this.j.a();
                        } else {
                            b2 = this.j.a(b);
                        }
                        aVar2.b = b2;
                    }
                    obj = 1;
                } else {
                    aVar2.c = this.k;
                    if (this.k) {
                        aVar2.b = this.j.c() - this.m;
                    } else {
                        aVar2.b = this.j.b() + this.m;
                    }
                }
                obj = 1;
            } else {
                aVar2.c = this.n.c;
                if (aVar2.c) {
                    aVar2.b = this.j.c() - this.n.b;
                } else {
                    aVar2.b = this.j.b() + this.n.b;
                }
                obj = 1;
            }
        }
        if (obj == null) {
            if (n() != 0) {
                View view;
                if (this.q == null) {
                    view = null;
                } else {
                    b = this.q.getFocusedChild();
                    view = (b == null || this.p.d(b)) ? null : b;
                }
                if (view != null) {
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    if (layoutParams.c.isRemoved() || layoutParams.c.getLayoutPosition() < 0 || layoutParams.c.getLayoutPosition() >= qVar.a()) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                    if (obj != null) {
                        b2 = aVar2.d.j.a();
                        if (b2 >= 0) {
                            aVar2.a(view);
                        } else {
                            aVar2.a = a(view);
                            if (aVar2.c) {
                                b2 = (aVar2.d.j.c() - b2) - aVar2.d.j.b(view);
                                aVar2.b = aVar2.d.j.c() - b2;
                                if (b2 > 0) {
                                    c = aVar2.b - aVar2.d.j.c(view);
                                    b3 = aVar2.d.j.b();
                                    min = c - (Math.min(aVar2.d.j.a(view) - b3, 0) + b3);
                                    if (min < 0) {
                                        aVar2.b = Math.min(b2, -min) + aVar2.b;
                                    }
                                }
                            } else {
                                c = aVar2.d.j.a(view);
                                b3 = c - aVar2.d.j.b();
                                aVar2.b = c;
                                if (b3 > 0) {
                                    b2 = (aVar2.d.j.c() - Math.min(0, (aVar2.d.j.c() - b2) - aVar2.d.j.b(view))) - (c + aVar2.d.j.c(view));
                                    if (b2 < 0) {
                                        aVar2.b -= Math.min(b3, -b2);
                                    }
                                }
                            }
                        }
                        obj = 1;
                        if (obj == null) {
                            aVar2.a();
                            if (this.d) {
                                b2 = qVar.a() - 1;
                            } else {
                                b2 = 0;
                            }
                            aVar2.a = b2;
                        }
                    }
                }
                if (this.b == this.d) {
                    b = aVar2.c ? d(mVar, qVar) : e(mVar, qVar);
                    if (b != null) {
                        aVar2.a(b);
                        if (!qVar.g && c()) {
                            obj = (this.j.a(b) >= this.j.c() || this.j.b(b) < this.j.b()) ? 1 : null;
                            if (obj != null) {
                                aVar2.b = aVar2.c ? this.j.c() : this.j.b();
                            }
                        }
                        obj = 1;
                        if (obj == null) {
                            aVar2.a();
                            if (this.d) {
                                b2 = 0;
                            } else {
                                b2 = qVar.a() - 1;
                            }
                            aVar2.a = b2;
                        }
                    }
                }
            }
            obj = null;
            if (obj == null) {
                aVar2.a();
                if (this.d) {
                    b2 = qVar.a() - 1;
                } else {
                    b2 = 0;
                }
                aVar2.a = b2;
            }
        }
        b2 = g(qVar);
        if (this.a.j >= 0) {
            min = 0;
        } else {
            min = b2;
            b2 = 0;
        }
        min += this.j.b();
        b2 += this.j.f();
        if (!(!qVar.g || this.l == -1 || this.m == Integer.MIN_VALUE)) {
            View b4 = b(this.l);
            if (b4 != null) {
                if (this.k) {
                    c2 = (this.j.c() - this.j.b(b4)) - this.m;
                } else {
                    c2 = this.m - (this.j.a(b4) - this.j.b());
                }
                if (c2 > 0) {
                    min += c2;
                } else {
                    b2 -= c2;
                }
            }
        }
        c2 = this.o.c ? this.k ? 1 : -1 : this.k ? -1 : 1;
        a(mVar, qVar, this.o, c2);
        a(mVar);
        this.a.l = v();
        this.a.i = qVar.g;
        if (this.o.c) {
            b(this.o);
            this.a.h = min;
            a(mVar, this.a, qVar, false);
            min = this.a.b;
            c = this.a.d;
            if (this.a.c > 0) {
                b2 += this.a.c;
            }
            a(this.o);
            this.a.h = b2;
            c cVar = this.a;
            cVar.d += this.a.e;
            a(mVar, this.a, qVar, false);
            c2 = this.a.b;
            if (this.a.c > 0) {
                b2 = this.a.c;
                k(c, min);
                this.a.h = b2;
                a(mVar, this.a, qVar, false);
                b2 = this.a.b;
            } else {
                b2 = min;
            }
            min = b2;
            b2 = c2;
        } else {
            a(this.o);
            this.a.h = b2;
            a(mVar, this.a, qVar, false);
            b2 = this.a.b;
            c2 = this.a.d;
            if (this.a.c > 0) {
                min += this.a.c;
            }
            b(this.o);
            this.a.h = min;
            c cVar2 = this.a;
            cVar2.d += this.a.e;
            a(mVar, this.a, qVar, false);
            min = this.a.b;
            if (this.a.c > 0) {
                c = this.a.c;
                j(c2, b2);
                this.a.h = c;
                a(mVar, this.a, qVar, false);
                b2 = this.a.b;
            }
        }
        if (n() <= 0) {
            c2 = min;
            min = b2;
        } else if ((this.k ^ this.d) != 0) {
            c2 = a(b2, mVar, qVar, true);
            min += c2;
            b2 += c2;
            c2 = b(min, mVar, qVar, false);
            b2 += c2;
            c2 = min + c2;
            min = b2;
        } else {
            c2 = b(min, mVar, qVar, true);
            min += c2;
            b2 += c2;
            c2 = a(b2, mVar, qVar, false);
            b2 += c2;
            c2 = min + c2;
            min = b2;
        }
        if (qVar.i && n() != 0 && !qVar.g && c()) {
            int i = 0;
            b3 = 0;
            List list = mVar.d;
            int size = list.size();
            int a = a(e(0));
            int i2 = 0;
            while (i2 < size) {
                t tVar = (t) list.get(i2);
                if (tVar.isRemoved()) {
                    b2 = b3;
                    c = i;
                } else {
                    if (((tVar.getLayoutPosition() < a ? 1 : null) != this.k ? -1 : 1) == -1) {
                        c = this.j.c(tVar.itemView) + i;
                        b2 = b3;
                    } else {
                        b2 = this.j.c(tVar.itemView) + b3;
                        c = i;
                    }
                }
                i = c;
                i2++;
                b3 = b2;
            }
            this.a.k = list;
            if (i > 0) {
                k(a(w()), c2);
                this.a.h = i;
                this.a.c = 0;
                this.a.a(null);
                a(mVar, this.a, qVar, false);
            }
            if (b3 > 0) {
                j(a(x()), min);
                this.a.h = b3;
                this.a.c = 0;
                this.a.a(null);
                a(mVar, this.a, qVar, false);
            }
            this.a.k = null;
        }
        if (!qVar.g) {
            this.l = -1;
            this.m = Integer.MIN_VALUE;
            ax axVar = this.j;
            axVar.b = axVar.e();
        }
        this.b = this.d;
        this.n = null;
    }

    void a(m mVar, q qVar, a aVar, int i) {
    }

    private int a(int i, m mVar, q qVar, boolean z) {
        int c = this.j.c() - i;
        if (c <= 0) {
            return 0;
        }
        c = -c(-c, mVar, qVar);
        int i2 = i + c;
        if (!z) {
            return c;
        }
        i2 = this.j.c() - i2;
        if (i2 <= 0) {
            return c;
        }
        this.j.a(i2);
        return c + i2;
    }

    private int b(int i, m mVar, q qVar, boolean z) {
        int b = i - this.j.b();
        if (b <= 0) {
            return 0;
        }
        b = -c(b, mVar, qVar);
        int i2 = i + b;
        if (!z) {
            return b;
        }
        i2 -= this.j.b();
        if (i2 <= 0) {
            return b;
        }
        this.j.a(-i2);
        return b - i2;
    }

    private void a(a aVar) {
        j(aVar.a, aVar.b);
    }

    private void j(int i, int i2) {
        this.a.c = this.j.c() - i2;
        this.a.e = this.k ? -1 : 1;
        this.a.d = i;
        this.a.f = 1;
        this.a.b = i2;
        this.a.g = Integer.MIN_VALUE;
    }

    private void b(a aVar) {
        k(aVar.a, aVar.b);
    }

    private void k(int i, int i2) {
        this.a.c = i2 - this.j.b();
        this.a.d = i;
        this.a.e = this.k ? 1 : -1;
        this.a.f = -1;
        this.a.b = i2;
        this.a.g = Integer.MIN_VALUE;
    }

    protected final boolean g() {
        return ViewCompat.getLayoutDirection(this.q) == 1;
    }

    final void h() {
        if (this.a == null) {
            this.a = new c();
        }
        if (this.j == null) {
            this.j = ax.a(this, this.i);
        }
    }

    public final void c(int i) {
        this.l = i;
        this.m = Integer.MIN_VALUE;
        if (this.n != null) {
            this.n.a = -1;
        }
        l();
    }

    public final void e(int i, int i2) {
        this.l = i;
        this.m = i2;
        if (this.n != null) {
            this.n.a = -1;
        }
        l();
    }

    public int a(int i, m mVar, q qVar) {
        return this.i == 1 ? 0 : c(i, mVar, qVar);
    }

    public int b(int i, m mVar, q qVar) {
        return this.i == 0 ? 0 : c(i, mVar, qVar);
    }

    public final int a(q qVar) {
        return h(qVar);
    }

    public final int b(q qVar) {
        return h(qVar);
    }

    public final int c(q qVar) {
        return i(qVar);
    }

    public final int d(q qVar) {
        return i(qVar);
    }

    public final int e(q qVar) {
        return j(qVar);
    }

    public final int f(q qVar) {
        return j(qVar);
    }

    private int h(q qVar) {
        boolean z = true;
        if (n() == 0) {
            return 0;
        }
        h();
        ax axVar = this.j;
        View c = c(!this.e);
        if (this.e) {
            z = false;
        }
        return bl.a(qVar, axVar, c, d(z), this, this.e, this.k);
    }

    private int i(q qVar) {
        boolean z = true;
        if (n() == 0) {
            return 0;
        }
        h();
        ax axVar = this.j;
        View c = c(!this.e);
        if (this.e) {
            z = false;
        }
        return bl.a(qVar, axVar, c, d(z), this, this.e);
    }

    private int j(q qVar) {
        boolean z = true;
        if (n() == 0) {
            return 0;
        }
        h();
        ax axVar = this.j;
        View c = c(!this.e);
        if (this.e) {
            z = false;
        }
        return bl.b(qVar, axVar, c, d(z), this, this.e);
    }

    private void a(int i, int i2, boolean z, q qVar) {
        int i3 = -1;
        int i4 = 1;
        this.a.l = v();
        this.a.h = g(qVar);
        this.a.f = i;
        View x;
        c cVar;
        if (i == 1) {
            c cVar2 = this.a;
            cVar2.h += this.j.f();
            x = x();
            cVar = this.a;
            if (!this.k) {
                i3 = 1;
            }
            cVar.e = i3;
            this.a.d = a(x) + this.a.e;
            this.a.b = this.j.b(x);
            i3 = this.j.b(x) - this.j.c();
        } else {
            x = w();
            cVar = this.a;
            cVar.h += this.j.b();
            cVar = this.a;
            if (!this.k) {
                i4 = -1;
            }
            cVar.e = i4;
            this.a.d = a(x) + this.a.e;
            this.a.b = this.j.a(x);
            i3 = (-this.j.a(x)) + this.j.b();
        }
        this.a.c = i2;
        if (z) {
            c cVar3 = this.a;
            cVar3.c -= i3;
        }
        this.a.g = i3;
    }

    private boolean v() {
        return this.j.g() == 0 && this.j.d() == 0;
    }

    private int c(int i, m mVar, q qVar) {
        if (n() == 0 || i == 0) {
            return 0;
        }
        this.a.a = true;
        h();
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        a(i2, abs, true, qVar);
        int a = this.a.g + a(mVar, this.a, qVar, false);
        if (a < 0) {
            return 0;
        }
        if (abs > a) {
            i = i2 * a;
        }
        this.j.a(-i);
        this.a.j = i;
        return i;
    }

    public final void a(String str) {
        if (this.n == null) {
            super.a(str);
        }
    }

    private void a(m mVar, int i, int i2) {
        if (i != i2) {
            if (i2 > i) {
                for (int i3 = i2 - 1; i3 >= i; i3--) {
                    a(i3, mVar);
                }
                return;
            }
            while (i > i2) {
                a(i, mVar);
                i--;
            }
        }
    }

    private void a(m mVar, c cVar) {
        if (cVar.a && !cVar.l) {
            int i;
            int n;
            int d;
            if (cVar.f == -1) {
                i = cVar.g;
                n = n();
                if (i >= 0) {
                    d = this.j.d() - i;
                    if (this.k) {
                        for (i = 0; i < n; i++) {
                            if (this.j.a(e(i)) < d) {
                                a(mVar, 0, i);
                                return;
                            }
                        }
                        return;
                    }
                    for (i = n - 1; i >= 0; i--) {
                        if (this.j.a(e(i)) < d) {
                            a(mVar, n - 1, i);
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            n = cVar.g;
            if (n >= 0) {
                d = n();
                if (this.k) {
                    for (i = d - 1; i >= 0; i--) {
                        if (this.j.b(e(i)) > n) {
                            a(mVar, d - 1, i);
                            return;
                        }
                    }
                    return;
                }
                for (i = 0; i < d; i++) {
                    if (this.j.b(e(i)) > n) {
                        a(mVar, 0, i);
                        return;
                    }
                }
            }
        }
    }

    private int a(m mVar, c cVar, q qVar, boolean z) {
        int i = cVar.c;
        if (cVar.g != Integer.MIN_VALUE) {
            if (cVar.c < 0) {
                cVar.g += cVar.c;
            }
            a(mVar, cVar);
        }
        int i2 = cVar.c + cVar.h;
        b bVar = new b();
        while (true) {
            if ((!cVar.l && i2 <= 0) || !cVar.a(qVar)) {
                break;
            }
            bVar.a = 0;
            bVar.b = false;
            bVar.c = false;
            bVar.d = false;
            a(mVar, qVar, cVar, bVar);
            if (!bVar.b) {
                cVar.b += bVar.a * cVar.f;
                if (!(bVar.c && this.a.k == null && qVar.g)) {
                    cVar.c -= bVar.a;
                    i2 -= bVar.a;
                }
                if (cVar.g != Integer.MIN_VALUE) {
                    cVar.g += bVar.a;
                    if (cVar.c < 0) {
                        cVar.g += cVar.c;
                    }
                    a(mVar, cVar);
                }
                if (z && bVar.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - cVar.c;
    }

    void a(m mVar, q qVar, c cVar, b bVar) {
        View a = cVar.a(mVar);
        if (a == null) {
            bVar.b = true;
            return;
        }
        int q;
        int i;
        LayoutParams layoutParams = (LayoutParams) a.getLayoutParams();
        boolean z;
        Object obj;
        if (cVar.k == null) {
            z = this.k;
            if (cVar.f == -1) {
                obj = 1;
            } else {
                obj = null;
            }
            if (z == r1) {
                super.a(a, -1, false);
            } else {
                super.a(a, 0, false);
            }
        } else {
            z = this.k;
            if (cVar.f == -1) {
                obj = 1;
            } else {
                obj = null;
            }
            if (z == r1) {
                super.a(a, -1, true);
            } else {
                super.a(a, 0, true);
            }
        }
        LayoutParams layoutParams2 = (LayoutParams) a.getLayoutParams();
        Rect itemDecorInsetsForChild = this.q.getItemDecorInsetsForChild(a);
        int i2 = (itemDecorInsetsForChild.left + itemDecorInsetsForChild.right) + 0;
        int i3 = (itemDecorInsetsForChild.bottom + itemDecorInsetsForChild.top) + 0;
        i2 = h.a(this.y, this.w, i2 + (((o() + q()) + layoutParams2.leftMargin) + layoutParams2.rightMargin), layoutParams2.width, e());
        i3 = h.a(this.z, this.x, i3 + (((p() + r()) + layoutParams2.topMargin) + layoutParams2.bottomMargin), layoutParams2.height, f());
        if (a(a, i2, i3, layoutParams2)) {
            a.measure(i2, i3);
        }
        bVar.a = this.j.c(a);
        if (this.i == 1) {
            if (g()) {
                q = this.y - q();
                i3 = q - this.j.d(a);
            } else {
                i3 = o();
                q = this.j.d(a) + i3;
            }
            if (cVar.f == -1) {
                int i4 = cVar.b;
                i2 = cVar.b - bVar.a;
                i = i3;
                i3 = q;
                q = i4;
            } else {
                i2 = cVar.b;
                i = i3;
                i3 = q;
                q = cVar.b + bVar.a;
            }
        } else {
            i2 = p();
            q = this.j.d(a) + i2;
            if (cVar.f == -1) {
                i3 = cVar.b;
                i = cVar.b - bVar.a;
            } else {
                i = cVar.b;
                i3 = cVar.b + bVar.a;
            }
        }
        a(a, i + layoutParams.leftMargin, i2 + layoutParams.topMargin, i3 - layoutParams.rightMargin, q - layoutParams.bottomMargin);
        if (layoutParams.c.isRemoved() || layoutParams.c.isUpdated()) {
            bVar.c = true;
        }
        bVar.d = a.isFocusable();
    }

    final boolean i() {
        if (!(this.x == 1073741824 || this.w == 1073741824)) {
            boolean z;
            int n = n();
            for (int i = 0; i < n; i++) {
                ViewGroup.LayoutParams layoutParams = e(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    z = true;
                    break;
                }
            }
            Object obj = null;
            if (z) {
                return true;
            }
        }
        return false;
    }

    final int d(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return -1;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return 1;
            case R.styleable.Toolbar_maxButtonHeight:
                return this.i != 0 ? Integer.MIN_VALUE : -1;
            case R.styleable.AppCompatTheme_actionModeCopyDrawable:
                return this.i != 1 ? Integer.MIN_VALUE : -1;
            case R.styleable.AppCompatTheme_textAppearanceSearchResultSubtitle:
                return this.i == 0 ? 1 : Integer.MIN_VALUE;
            case TransportMediator.KEYCODE_MEDIA_RECORD:
                return this.i == 1 ? 1 : Integer.MIN_VALUE;
            default:
                return Integer.MIN_VALUE;
        }
    }

    private View w() {
        return e(this.k ? n() - 1 : 0);
    }

    private View x() {
        return e(this.k ? 0 : n() - 1);
    }

    private View c(boolean z) {
        return this.k ? a(n() - 1, -1, z, true) : a(0, n(), z, true);
    }

    private View d(boolean z) {
        return this.k ? a(0, n(), z, true) : a(n() - 1, -1, z, true);
    }

    private View d(m mVar, q qVar) {
        return this.k ? f(mVar, qVar) : g(mVar, qVar);
    }

    private View e(m mVar, q qVar) {
        return this.k ? g(mVar, qVar) : f(mVar, qVar);
    }

    private View f(m mVar, q qVar) {
        return a(mVar, qVar, 0, n(), qVar.a());
    }

    private View g(m mVar, q qVar) {
        return a(mVar, qVar, n() - 1, -1, qVar.a());
    }

    View a(m mVar, q qVar, int i, int i2, int i3) {
        View view;
        View view2;
        Object obj = null;
        h();
        int b = this.j.b();
        int c = this.j.c();
        int i4 = i2 > i ? 1 : -1;
        Object obj2 = null;
        while (i != i2) {
            View view3;
            View e = e(i);
            int a = a(e);
            if (a >= 0 && a < i3) {
                if (((LayoutParams) e.getLayoutParams()).c.isRemoved()) {
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

    public final int j() {
        View a = a(0, n(), false, true);
        return a == null ? -1 : a(a);
    }

    public final int k() {
        View a = a(n() - 1, -1, false, true);
        return a == null ? -1 : a(a);
    }

    public final View a(int i, int i2, boolean z, boolean z2) {
        View view;
        h();
        int b = this.j.b();
        int c = this.j.c();
        int i3 = i2 > i ? 1 : -1;
        Object obj = null;
        while (i != i2) {
            View e = e(i);
            int a = this.j.a(e);
            int b2 = this.j.b(e);
            if (a < c && b2 > b) {
                if (!z) {
                    return e;
                }
                if (a >= b && b2 <= c) {
                    return e;
                }
                if (z2 && view == null) {
                    i += i3;
                    view = e;
                }
            }
            e = view;
            i += i3;
            view = e;
        }
        return view;
    }

    public View a(View view, int i, m mVar, q qVar) {
        u();
        if (n() == 0) {
            return null;
        }
        int d = d(i);
        if (d == Integer.MIN_VALUE) {
            return null;
        }
        View e;
        h();
        if (d == -1) {
            e = e(mVar, qVar);
        } else {
            e = d(mVar, qVar);
        }
        if (e == null) {
            return null;
        }
        View w;
        h();
        a(d, (int) (0.33333334f * ((float) this.j.e())), false, qVar);
        this.a.g = Integer.MIN_VALUE;
        this.a.a = false;
        a(mVar, this.a, qVar, true);
        if (d == -1) {
            w = w();
        } else {
            w = x();
        }
        return (w == e || !w.isFocusable()) ? null : w;
    }

    public boolean c() {
        return this.n == null && this.b == this.d;
    }
}
