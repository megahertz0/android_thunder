package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.m;
import android.support.v7.widget.RecyclerView.p;
import android.support.v7.widget.RecyclerView.q;
import android.support.v7.widget.StaggeredGridLayoutManager.LayoutParams;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class StaggeredGridLayoutManager extends h {
    private boolean A;
    private SavedState B;
    private int C;
    private final Rect D;
    private final a E;
    private boolean F;
    private boolean G;
    private final Runnable H;
    public int a;
    public b[] b;
    ax c;
    ax d;
    boolean e;
    int f;
    int g;
    LazySpanLookup h;
    private int i;
    private int j;
    private final aq k;
    private boolean l;
    private BitSet m;
    private int n;
    private boolean o;

    public static class LayoutParams extends android.support.v7.widget.RecyclerView.LayoutParams {
        b a;
        boolean b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public final int a() {
            return this.a == null ? -1 : this.a.e;
        }
    }

    static class LazySpanLookup {
        int[] a;
        List<FullSpanItem> b;

        static class FullSpanItem implements Parcelable {
            public static final Creator<FullSpanItem> CREATOR;
            int a;
            int b;
            int[] c;
            boolean d;

            public FullSpanItem(Parcel parcel) {
                boolean z = true;
                this.a = parcel.readInt();
                this.b = parcel.readInt();
                if (parcel.readInt() != 1) {
                    z = false;
                }
                this.d = z;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    this.c = new int[readInt];
                    parcel.readIntArray(this.c);
                }
            }

            final int a(int i) {
                return this.c == null ? 0 : this.c[i];
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b);
                parcel.writeInt(this.d ? 1 : 0);
                if (this.c == null || this.c.length <= 0) {
                    parcel.writeInt(0);
                    return;
                }
                parcel.writeInt(this.c.length);
                parcel.writeIntArray(this.c);
            }

            public String toString() {
                return new StringBuilder("FullSpanItem{mPosition=").append(this.a).append(", mGapDir=").append(this.b).append(", mHasUnwantedGapAfter=").append(this.d).append(", mGapPerSpan=").append(Arrays.toString(this.c)).append('}').toString();
            }

            static {
                CREATOR = new ce();
            }
        }

        LazySpanLookup() {
        }

        final int a(int i) {
            if (this.b != null) {
                for (int size = this.b.size() - 1; size >= 0; size--) {
                    if (((FullSpanItem) this.b.get(size)).a >= i) {
                        this.b.remove(size);
                    }
                }
            }
            return b(i);
        }

        final int b(int i) {
            if (this.a == null) {
                return -1;
            }
            if (i >= this.a.length) {
                return -1;
            }
            int i2;
            if (this.b != null) {
                FullSpanItem d = d(i);
                if (d != null) {
                    this.b.remove(d);
                }
                int size = this.b.size();
                int i3 = 0;
                while (i3 < size) {
                    if (((FullSpanItem) this.b.get(i3)).a >= i) {
                        break;
                    }
                    i3++;
                }
                i3 = -1;
                if (i3 != -1) {
                    d = (FullSpanItem) this.b.get(i3);
                    this.b.remove(i3);
                    i2 = d.a;
                    if (i2 != -1) {
                        Arrays.fill(this.a, i, this.a.length, -1);
                        return this.a.length;
                    }
                    Arrays.fill(this.a, i, i2 + 1, -1);
                    return i2 + 1;
                }
            }
            i2 = -1;
            if (i2 != -1) {
                Arrays.fill(this.a, i, i2 + 1, -1);
                return i2 + 1;
            }
            Arrays.fill(this.a, i, this.a.length, -1);
            return this.a.length;
        }

        final void c(int i) {
            if (this.a == null) {
                this.a = new int[(Math.max(i, XZBDevice.Stop) + 1)];
                Arrays.fill(this.a, -1);
            } else if (i >= this.a.length) {
                Object obj = this.a;
                int length = this.a.length;
                while (length <= i) {
                    length *= 2;
                }
                this.a = new int[length];
                System.arraycopy(obj, 0, this.a, 0, obj.length);
                Arrays.fill(this.a, obj.length, this.a.length, -1);
            }
        }

        final void a() {
            if (this.a != null) {
                Arrays.fill(this.a, -1);
            }
            this.b = null;
        }

        final void a(int i, int i2) {
            if (this.a != null && i < this.a.length) {
                c(i + i2);
                System.arraycopy(this.a, i + i2, this.a, i, (this.a.length - i) - i2);
                Arrays.fill(this.a, this.a.length - i2, this.a.length, -1);
                if (this.b != null) {
                    int i3 = i + i2;
                    for (int size = this.b.size() - 1; size >= 0; size--) {
                        FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                        if (fullSpanItem.a >= i) {
                            if (fullSpanItem.a < i3) {
                                this.b.remove(size);
                            } else {
                                fullSpanItem.a -= i2;
                            }
                        }
                    }
                }
            }
        }

        final void b(int i, int i2) {
            if (this.a != null && i < this.a.length) {
                c(i + i2);
                System.arraycopy(this.a, i, this.a, i + i2, (this.a.length - i) - i2);
                Arrays.fill(this.a, i, i + i2, -1);
                if (this.b != null) {
                    for (int size = this.b.size() - 1; size >= 0; size--) {
                        FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                        if (fullSpanItem.a >= i) {
                            fullSpanItem.a += i2;
                        }
                    }
                }
            }
        }

        public final void a(FullSpanItem fullSpanItem) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem fullSpanItem2 = (FullSpanItem) this.b.get(i);
                if (fullSpanItem2.a == fullSpanItem.a) {
                    this.b.remove(i);
                }
                if (fullSpanItem2.a >= fullSpanItem.a) {
                    this.b.add(i, fullSpanItem);
                    return;
                }
            }
            this.b.add(fullSpanItem);
        }

        public final FullSpanItem d(int i) {
            if (this.b == null) {
                return null;
            }
            for (int size = this.b.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                if (fullSpanItem.a == i) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public final FullSpanItem a(int i, int i2, int i3) {
            if (this.b == null) {
                return null;
            }
            int size = this.b.size();
            for (int i4 = 0; i4 < size; i4++) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(i4);
                if (fullSpanItem.a >= i2) {
                    return null;
                }
                if (fullSpanItem.a >= i) {
                    if (i3 == 0 || fullSpanItem.b == i3 || fullSpanItem.d) {
                        return fullSpanItem;
                    }
                }
            }
            return null;
        }
    }

    public static class SavedState implements Parcelable {
        public static final Creator<android.support.v7.widget.StaggeredGridLayoutManager.SavedState> CREATOR;
        int a;
        int b;
        int c;
        int[] d;
        int e;
        int[] f;
        List<FullSpanItem> g;
        boolean h;
        boolean i;
        boolean j;

        SavedState(Parcel parcel) {
            boolean z;
            boolean z2 = true;
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            if (this.c > 0) {
                this.d = new int[this.c];
                parcel.readIntArray(this.d);
            }
            this.e = parcel.readInt();
            if (this.e > 0) {
                this.f = new int[this.e];
                parcel.readIntArray(this.f);
            }
            this.h = parcel.readInt() == 1;
            if (parcel.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.i = z;
            if (parcel.readInt() != 1) {
                z2 = false;
            }
            this.j = z2;
            this.g = parcel.readArrayList(FullSpanItem.class.getClassLoader());
        }

        public SavedState(android.support.v7.widget.StaggeredGridLayoutManager.SavedState savedState) {
            this.c = savedState.c;
            this.a = savedState.a;
            this.b = savedState.b;
            this.d = savedState.d;
            this.e = savedState.e;
            this.f = savedState.f;
            this.h = savedState.h;
            this.i = savedState.i;
            this.j = savedState.j;
            this.g = savedState.g;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int i2;
            int i3 = 1;
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            if (this.c > 0) {
                parcel.writeIntArray(this.d);
            }
            parcel.writeInt(this.e);
            if (this.e > 0) {
                parcel.writeIntArray(this.f);
            }
            if (this.h) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeInt(i2);
            if (this.i) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeInt(i2);
            if (!this.j) {
                i3 = 0;
            }
            parcel.writeInt(i3);
            parcel.writeList(this.g);
        }

        static {
            CREATOR = new cf();
        }
    }

    private class a {
        int a;
        int b;
        boolean c;
        boolean d;

        private a() {
        }
    }

    class b {
        public ArrayList<View> a;
        int b;
        int c;
        int d;
        final int e;

        private b(int i) {
            this.a = new ArrayList();
            this.b = Integer.MIN_VALUE;
            this.c = Integer.MIN_VALUE;
            this.d = 0;
            this.e = i;
        }

        final int a(int i) {
            if (this.b != Integer.MIN_VALUE) {
                return this.b;
            }
            if (this.a.size() == 0) {
                return i;
            }
            f();
            return this.b;
        }

        private void f() {
            View view = (View) this.a.get(0);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            this.b = StaggeredGridLayoutManager.this.c.a(view);
            if (layoutParams.b) {
                FullSpanItem d = StaggeredGridLayoutManager.this.h.d(layoutParams.c.getLayoutPosition());
                if (d != null && d.b == -1) {
                    this.b -= d.a(this.e);
                }
            }
        }

        final int a() {
            if (this.b != Integer.MIN_VALUE) {
                return this.b;
            }
            f();
            return this.b;
        }

        final int b(int i) {
            if (this.c != Integer.MIN_VALUE) {
                return this.c;
            }
            if (this.a.size() == 0) {
                return i;
            }
            g();
            return this.c;
        }

        private void g() {
            View view = (View) this.a.get(this.a.size() - 1);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            this.c = StaggeredGridLayoutManager.this.c.b(view);
            if (layoutParams.b) {
                FullSpanItem d = StaggeredGridLayoutManager.this.h.d(layoutParams.c.getLayoutPosition());
                if (d != null && d.b == 1) {
                    this.c = d.a(this.e) + this.c;
                }
            }
        }

        final int b() {
            if (this.c != Integer.MIN_VALUE) {
                return this.c;
            }
            g();
            return this.c;
        }

        final void a(View view) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.a = this;
            this.a.add(0, view);
            this.b = Integer.MIN_VALUE;
            if (this.a.size() == 1) {
                this.c = Integer.MIN_VALUE;
            }
            if (layoutParams.c.isRemoved() || layoutParams.c.isUpdated()) {
                this.d += StaggeredGridLayoutManager.this.c.c(view);
            }
        }

        final void b(View view) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.a = this;
            this.a.add(view);
            this.c = Integer.MIN_VALUE;
            if (this.a.size() == 1) {
                this.b = Integer.MIN_VALUE;
            }
            if (layoutParams.c.isRemoved() || layoutParams.c.isUpdated()) {
                this.d += StaggeredGridLayoutManager.this.c.c(view);
            }
        }

        final void c() {
            this.a.clear();
            this.b = Integer.MIN_VALUE;
            this.c = Integer.MIN_VALUE;
            this.d = 0;
        }

        final void c(int i) {
            this.b = i;
            this.c = i;
        }

        final void d() {
            int size = this.a.size();
            View view = (View) this.a.remove(size - 1);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.a = null;
            if (layoutParams.c.isRemoved() || layoutParams.c.isUpdated()) {
                this.d -= StaggeredGridLayoutManager.this.c.c(view);
            }
            if (size == 1) {
                this.b = Integer.MIN_VALUE;
            }
            this.c = Integer.MIN_VALUE;
        }

        final void e() {
            View view = (View) this.a.remove(0);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.a = null;
            if (this.a.size() == 0) {
                this.c = Integer.MIN_VALUE;
            }
            if (layoutParams.c.isRemoved() || layoutParams.c.isUpdated()) {
                this.d -= StaggeredGridLayoutManager.this.c.c(view);
            }
            this.b = Integer.MIN_VALUE;
        }

        final void d(int i) {
            if (this.b != Integer.MIN_VALUE) {
                this.b += i;
            }
            if (this.c != Integer.MIN_VALUE) {
                this.c += i;
            }
        }

        public final int a(int i, int i2) {
            int b = StaggeredGridLayoutManager.this.c.b();
            int c = StaggeredGridLayoutManager.this.c.c();
            int i3 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = (View) this.a.get(i);
                int a = StaggeredGridLayoutManager.this.c.a(view);
                int b2 = StaggeredGridLayoutManager.this.c.b(view);
                if (a < c && b2 > b) {
                    return StaggeredGridLayoutManager.a(view);
                }
                i += i3;
            }
            return -1;
        }

        public final View b(int i, int i2) {
            Object obj = null;
            int size;
            int i3;
            View view;
            View view2;
            if (i2 == -1) {
                size = this.a.size();
                i3 = 0;
                while (i3 < size) {
                    view = (View) this.a.get(i3);
                    if (!view.isFocusable()) {
                        break;
                    }
                    if (StaggeredGridLayoutManager.a(view) > i) {
                        int i4 = 1;
                    } else {
                        Object obj2 = null;
                    }
                    if (r1 != StaggeredGridLayoutManager.this.l) {
                        break;
                    }
                    i3++;
                    view2 = view;
                }
                return view2;
            }
            for (size = this.a.size() - 1; size >= 0; size--) {
                view = (View) this.a.get(size);
                if (!view.isFocusable()) {
                    break;
                }
                if (StaggeredGridLayoutManager.a(view) > i) {
                    i4 = 1;
                } else {
                    obj2 = null;
                }
                if (StaggeredGridLayoutManager.this.l) {
                    Object obj3 = null;
                } else {
                    i3 = 1;
                }
                if (obj2 != obj3) {
                    break;
                }
                view2 = view;
            }
            return view2;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        boolean z = true;
        this.a = -1;
        this.l = false;
        this.e = false;
        this.f = -1;
        this.g = Integer.MIN_VALUE;
        this.h = new LazySpanLookup();
        this.n = 2;
        this.D = new Rect();
        this.E = new a();
        this.F = false;
        this.G = true;
        this.H = new cc(this);
        android.support.v7.widget.RecyclerView.h.a a = a(context, attributeSet, i, i2);
        int i3 = a.a;
        if (i3 == 0 || i3 == 1) {
            a(null);
            if (i3 != this.i) {
                this.i = i3;
                ax axVar = this.c;
                this.c = this.d;
                this.d = axVar;
                l();
            }
            a(a.b);
            a(a.c);
            if (this.n == 0) {
                z = false;
            }
            this.u = z;
            this.k = new aq();
            g();
            return;
        }
        throw new IllegalArgumentException("invalid orientation.");
    }

    public StaggeredGridLayoutManager(int i, int i2) {
        boolean z = true;
        this.a = -1;
        this.l = false;
        this.e = false;
        this.f = -1;
        this.g = Integer.MIN_VALUE;
        this.h = new LazySpanLookup();
        this.n = 2;
        this.D = new Rect();
        this.E = new a();
        this.F = false;
        this.G = true;
        this.H = new cc(this);
        this.i = i2;
        a(i);
        if (this.n == 0) {
            z = false;
        }
        this.u = z;
        this.k = new aq();
        g();
    }

    private void g() {
        this.c = ax.a(this, this.i);
        this.d = ax.a(this, 1 - this.i);
    }

    private boolean h() {
        if (n() == 0 || this.n == 0 || !this.t) {
            return false;
        }
        int v;
        int w;
        if (this.e) {
            v = v();
            w = w();
        } else {
            v = w();
            w = v();
        }
        if (v == 0 && j() != null) {
            this.h.a();
            this.s = true;
            l();
            return true;
        } else if (!this.F) {
            return false;
        } else {
            int i = this.e ? -1 : 1;
            FullSpanItem a = this.h.a(v, w + 1, i);
            if (a == null) {
                this.F = false;
                this.h.a(w + 1);
                return false;
            }
            FullSpanItem a2 = this.h.a(v, a.a, i * -1);
            if (a2 == null) {
                this.h.a(a.a);
            } else {
                this.h.a(a2.a + 1);
            }
            this.s = true;
            l();
            return true;
        }
    }

    public final void h(int i) {
        if (i == 0) {
            h();
        }
    }

    public final void a(RecyclerView recyclerView, m mVar) {
        a(this.H);
        for (int i = 0; i < this.a; i++) {
            this.b[i].c();
        }
    }

    private View j() {
        int i;
        int i2;
        int n = n() - 1;
        BitSet bitSet = new BitSet(this.a);
        bitSet.set(0, this.a, true);
        if (this.i == 1 && u()) {
            boolean z = true;
        } else {
            int i3 = -1;
        }
        if (this.e) {
            i = -1;
        } else {
            i = n + 1;
            n = 0;
        }
        if (n < i) {
            i2 = 1;
        } else {
            i2 = -1;
        }
        int i4 = n;
        while (i4 != i) {
            boolean z2;
            View e = e(i4);
            LayoutParams layoutParams = (LayoutParams) e.getLayoutParams();
            if (bitSet.get(layoutParams.a.e)) {
                b bVar = layoutParams.a;
                if (this.e) {
                    if (bVar.b() < this.c.c()) {
                        if (((LayoutParams) ((View) bVar.a.get(bVar.a.size() - 1)).getLayoutParams()).b) {
                            n = 0;
                        } else {
                            z2 = true;
                        }
                    }
                    n = 0;
                } else {
                    if (bVar.a() > this.c.b()) {
                        if (((LayoutParams) ((View) bVar.a.get(0)).getLayoutParams()).b) {
                            n = 0;
                        } else {
                            z2 = true;
                        }
                    }
                    n = 0;
                }
                if (z2) {
                    return e;
                }
                bitSet.clear(layoutParams.a.e);
            }
            if (!layoutParams.b && i4 + i2 != i) {
                View e2 = e(i4 + i2);
                int b;
                if (this.e) {
                    n = this.c.b(e);
                    b = this.c.b(e2);
                    if (n < b) {
                        return e;
                    }
                    if (n == b) {
                        z2 = true;
                    }
                    n = 0;
                } else {
                    n = this.c.a(e);
                    b = this.c.a(e2);
                    if (n > b) {
                        return e;
                    }
                    if (n == b) {
                        z2 = true;
                    }
                    n = 0;
                }
                if (z2) {
                    boolean z3;
                    if (layoutParams.a.e - ((LayoutParams) e2.getLayoutParams()).a.e < 0) {
                        z2 = true;
                    } else {
                        n = 0;
                    }
                    if (z >= false) {
                        z3 = true;
                    } else {
                        int i5 = 0;
                    }
                    if (z2 != z3) {
                        return e;
                    }
                } else {
                    continue;
                }
            }
            i4 += i2;
        }
        return null;
    }

    private void a(int i) {
        a(null);
        if (i != this.a) {
            this.h.a();
            l();
            this.a = i;
            this.m = new BitSet(this.a);
            this.b = new b[this.a];
            for (int i2 = 0; i2 < this.a; i2++) {
                this.b[i2] = new b(i2, (byte) 0);
            }
            l();
        }
    }

    private void a(boolean z) {
        a(null);
        if (!(this.B == null || this.B.h == z)) {
            this.B.h = z;
        }
        this.l = z;
        l();
    }

    public final void a(String str) {
        if (this.B == null) {
            super.a(str);
        }
    }

    private void k() {
        boolean z = true;
        if (this.i == 1 || !u()) {
            z = this.l;
        } else if (this.l) {
            z = false;
        }
        this.e = z;
    }

    private boolean u() {
        return ViewCompat.getLayoutDirection(this.q) == 1;
    }

    public final void a(Rect rect, int i, int i2) {
        int q = q() + o();
        int p = p() + r();
        if (this.i == 1) {
            p = a(i2, p + rect.height(), ViewCompat.getMinimumHeight(this.q));
            q = a(i, q + (this.j * this.a), ViewCompat.getMinimumWidth(this.q));
        } else {
            q = a(i, q + rect.width(), ViewCompat.getMinimumWidth(this.q));
            p = a(i2, p + (this.j * this.a), ViewCompat.getMinimumHeight(this.q));
        }
        i(q, p);
    }

    public final void c(m mVar, q qVar) {
        int i = 1;
        while (true) {
            a aVar = this.E;
            aVar.a = -1;
            aVar.b = Integer.MIN_VALUE;
            aVar.c = false;
            aVar.d = false;
            if (!(this.B == null && this.f == -1) && qVar.a() == 0) {
                c(mVar);
                return;
            }
            int i2;
            int i3;
            Object obj;
            int n;
            int i4;
            Object obj2;
            if (this.B != null) {
                if (this.B.c > 0) {
                    if (this.B.c == this.a) {
                        for (i2 = 0; i2 < this.a; i2++) {
                            this.b[i2].c();
                            i3 = this.B.d[i2];
                            if (i3 != Integer.MIN_VALUE) {
                                if (this.B.i) {
                                    i3 += this.c.c();
                                } else {
                                    i3 += this.c.b();
                                }
                            }
                            this.b[i2].c(i3);
                        }
                    } else {
                        SavedState savedState = this.B;
                        savedState.d = null;
                        savedState.c = 0;
                        savedState.e = 0;
                        savedState.f = null;
                        savedState.g = null;
                        this.B.a = this.B.b;
                    }
                }
                this.A = this.B.j;
                a(this.B.h);
                k();
                if (this.B.a != -1) {
                    this.f = this.B.a;
                    aVar.c = this.B.i;
                } else {
                    aVar.c = this.e;
                }
                if (this.B.e > 1) {
                    this.h.a = this.B.f;
                    this.h.b = this.B.g;
                }
            } else {
                k();
                aVar.c = this.e;
            }
            if (qVar.g || this.f == -1) {
                obj = null;
            } else if (this.f < 0 || this.f >= qVar.a()) {
                this.f = -1;
                this.g = Integer.MIN_VALUE;
                obj = null;
            } else {
                if (this.B == null || this.B.a == -1 || this.B.c <= 0) {
                    View b = b(this.f);
                    if (b != null) {
                        aVar.a = this.e ? v() : w();
                        if (this.g != Integer.MIN_VALUE) {
                            if (aVar.c) {
                                aVar.b = (this.c.c() - this.g) - this.c.b(b);
                            } else {
                                aVar.b = (this.c.b() + this.g) - this.c.a(b);
                            }
                            obj = 1;
                        } else if (this.c.c(b) > this.c.e()) {
                            if (aVar.c) {
                                i2 = this.c.c();
                            } else {
                                i2 = this.c.b();
                            }
                            aVar.b = i2;
                        } else {
                            i2 = this.c.a(b) - this.c.b();
                            if (i2 < 0) {
                                aVar.b = -i2;
                            } else {
                                i2 = this.c.c() - this.c.b(b);
                                if (i2 < 0) {
                                    aVar.b = i2;
                                } else {
                                    aVar.b = Integer.MIN_VALUE;
                                }
                            }
                        }
                    } else {
                        aVar.a = this.f;
                        if (this.g == Integer.MIN_VALUE) {
                            boolean z;
                            if (m(aVar.a) == 1) {
                                z = true;
                            } else {
                                z = false;
                            }
                            aVar.c = z;
                            if (aVar.c) {
                                i2 = aVar.e.c.c();
                            } else {
                                i2 = aVar.e.c.b();
                            }
                            aVar.b = i2;
                        } else {
                            i2 = this.g;
                            if (aVar.c) {
                                aVar.b = aVar.e.c.c() - i2;
                            } else {
                                aVar.b = i2 + aVar.e.c.b();
                            }
                        }
                        aVar.d = true;
                    }
                } else {
                    aVar.b = Integer.MIN_VALUE;
                    aVar.a = this.f;
                }
                obj = 1;
            }
            if (obj == null) {
                int a;
                if (this.o) {
                    a = qVar.a();
                    for (i3 = n() - 1; i3 >= 0; i3--) {
                        i2 = a(e(i3));
                        if (i2 >= 0 && i2 < a) {
                            break;
                        }
                    }
                    i2 = 0;
                } else {
                    a = qVar.a();
                    n = n();
                    for (i3 = 0; i3 < n; i3++) {
                        i2 = a(e(i3));
                        if (i2 >= 0 && i2 < a) {
                            break;
                        }
                    }
                    i2 = 0;
                }
                aVar.a = i2;
                aVar.b = Integer.MIN_VALUE;
            }
            if (this.B == null) {
                if (!(aVar.c == this.o && u() == this.A)) {
                    this.h.a();
                    aVar.d = true;
                }
            }
            if (n() > 0) {
                if (this.B == null || this.B.c <= 0) {
                    if (aVar.d) {
                        for (i2 = 0; i2 < this.a; i2++) {
                            this.b[i2].c();
                            if (aVar.b != Integer.MIN_VALUE) {
                                this.b[i2].c(aVar.b);
                            }
                        }
                    } else {
                        for (i3 = 0; i3 < this.a; i3++) {
                            b bVar = this.b[i3];
                            boolean z2 = this.e;
                            i4 = aVar.b;
                            if (z2) {
                                i2 = bVar.b((int) ExploreByTouchHelper.INVALID_ID);
                            } else {
                                i2 = bVar.a((int) ExploreByTouchHelper.INVALID_ID);
                            }
                            bVar.c();
                            if (i2 != Integer.MIN_VALUE) {
                                if (!z2 || i2 >= bVar.f.c.c()) {
                                    if (z2 || i2 <= bVar.f.c.b()) {
                                        if (i4 != Integer.MIN_VALUE) {
                                            i2 += i4;
                                        }
                                        bVar.c = i2;
                                        bVar.b = i2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            a(mVar);
            this.k.a = false;
            this.F = false;
            d(this.d.e());
            a(aVar.a, qVar);
            if (aVar.c) {
                i(-1);
                a(mVar, this.k, qVar);
                i(1);
                this.k.c = aVar.a + this.k.d;
                a(mVar, this.k, qVar);
            } else {
                i(1);
                a(mVar, this.k, qVar);
                i(-1);
                this.k.c = aVar.a + this.k.d;
                a(mVar, this.k, qVar);
            }
            if (this.d.g() != 1073741824) {
                float f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
                i4 = n();
                n = 0;
                while (n < i4) {
                    float f2;
                    View e = e(n);
                    float c = (float) this.d.c(e);
                    if (c >= f) {
                        if (((LayoutParams) e.getLayoutParams()).b) {
                            f2 = (1.0f * c) / ((float) this.a);
                        } else {
                            f2 = c;
                        }
                        f2 = Math.max(f, f2);
                    } else {
                        f2 = f;
                    }
                    n++;
                    f = f2;
                }
                n = this.j;
                i2 = Math.round(((float) this.a) * f);
                if (this.d.g() == Integer.MIN_VALUE) {
                    i2 = Math.min(i2, this.d.e());
                }
                d(i2);
                if (this.j != n) {
                    for (i3 = 0; i3 < i4; i3++) {
                        View e2 = e(i3);
                        LayoutParams layoutParams = (LayoutParams) e2.getLayoutParams();
                        if (!layoutParams.b) {
                            if (u() && this.i == 1) {
                                e2.offsetLeftAndRight(((-((this.a - 1) - layoutParams.a.e)) * this.j) - ((-((this.a - 1) - layoutParams.a.e)) * n));
                            } else {
                                int i5 = layoutParams.a.e * this.j;
                                i2 = layoutParams.a.e * n;
                                if (this.i == 1) {
                                    e2.offsetLeftAndRight(i5 - i2);
                                } else {
                                    e2.offsetTopAndBottom(i5 - i2);
                                }
                            }
                        }
                    }
                }
            }
            if (n() > 0) {
                if (this.e) {
                    a(mVar, qVar, true);
                    b(mVar, qVar, false);
                } else {
                    b(mVar, qVar, true);
                    a(mVar, qVar, false);
                }
            }
            obj = null;
            if (!(obj2 == null || qVar.g)) {
                obj2 = (this.n == 0 || n() <= 0 || (!this.F && j() == null)) ? null : 1;
                if (obj2 != null) {
                    a(this.H);
                    if (h()) {
                        obj = 1;
                    }
                }
                this.f = -1;
                this.g = Integer.MIN_VALUE;
            }
            this.o = aVar.c;
            this.A = u();
            this.B = null;
            if (obj != null) {
                obj2 = null;
            } else {
                return;
            }
        }
    }

    private void d(int i) {
        this.j = i / this.a;
        this.C = MeasureSpec.makeMeasureSpec(i, this.d.g());
    }

    public final boolean c() {
        return this.B == null;
    }

    public final int a(q qVar) {
        return g(qVar);
    }

    private int g(q qVar) {
        boolean z = true;
        if (n() == 0) {
            return 0;
        }
        ax axVar = this.c;
        View b = b(!this.G);
        if (this.G) {
            z = false;
        }
        return bl.a(qVar, axVar, b, c(z), this, this.G, this.e);
    }

    public final int b(q qVar) {
        return g(qVar);
    }

    public final int c(q qVar) {
        return h(qVar);
    }

    private int h(q qVar) {
        boolean z = true;
        if (n() == 0) {
            return 0;
        }
        ax axVar = this.c;
        View b = b(!this.G);
        if (this.G) {
            z = false;
        }
        return bl.a(qVar, axVar, b, c(z), this, this.G);
    }

    public final int d(q qVar) {
        return h(qVar);
    }

    public final int e(q qVar) {
        return i(qVar);
    }

    private int i(q qVar) {
        boolean z = true;
        if (n() == 0) {
            return 0;
        }
        ax axVar = this.c;
        View b = b(!this.G);
        if (this.G) {
            z = false;
        }
        return bl.b(qVar, axVar, b, c(z), this, this.G);
    }

    public final int f(q qVar) {
        return i(qVar);
    }

    private void a(View view, int i, int i2) {
        a(view, this.D);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int c = c(i, layoutParams.leftMargin + this.D.left, layoutParams.rightMargin + this.D.right);
        int c2 = c(i2, layoutParams.topMargin + this.D.top, layoutParams.bottomMargin + this.D.bottom);
        if (a(view, c, c2, layoutParams)) {
            view.measure(c, c2);
        }
    }

    private static int c(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? MeasureSpec.makeMeasureSpec(Math.max(0, (MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    public final void a(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.B = (SavedState) parcelable;
            l();
        }
    }

    public final Parcelable d() {
        if (this.B != null) {
            return new SavedState(this.B);
        }
        SavedState savedState = new SavedState();
        savedState.h = this.l;
        savedState.i = this.o;
        savedState.j = this.A;
        if (this.h == null || this.h.a == null) {
            savedState.e = 0;
        } else {
            savedState.f = this.h.a;
            savedState.e = savedState.f.length;
            savedState.g = this.h.b;
        }
        if (n() > 0) {
            int v;
            if (this.o) {
                v = v();
            } else {
                v = w();
            }
            savedState.a = v;
            View c = this.e ? c(true) : b(true);
            if (c == null) {
                v = -1;
            } else {
                v = a(c);
            }
            savedState.b = v;
            savedState.c = this.a;
            savedState.d = new int[this.a];
            for (v = 0; v < this.a; v++) {
                int b;
                if (this.o) {
                    b = this.b[v].b((int) ExploreByTouchHelper.INVALID_ID);
                    if (b != Integer.MIN_VALUE) {
                        b -= this.c.c();
                    }
                } else {
                    b = this.b[v].a((int) ExploreByTouchHelper.INVALID_ID);
                    if (b != Integer.MIN_VALUE) {
                        b -= this.c.b();
                    }
                }
                savedState.d[v] = b;
            }
        } else {
            savedState.a = -1;
            savedState.b = -1;
            savedState.c = 0;
        }
        return savedState;
    }

    public final void a(m mVar, q qVar, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int i = -1;
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            int i2;
            int a;
            int i3;
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (this.i == 0) {
                i2 = layoutParams2.b ? this.a : 1;
                a = layoutParams2.a();
                i3 = -1;
            } else {
                i3 = layoutParams2.a();
                if (layoutParams2.b) {
                    a = -1;
                    i = this.a;
                    i2 = -1;
                } else {
                    a = -1;
                    i = 1;
                    i2 = -1;
                }
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(a, i2, i3, i, layoutParams2.b, false));
            return;
        }
        super.a(view, accessibilityNodeInfoCompat);
    }

    public final void a(AccessibilityEvent accessibilityEvent) {
        super.a(accessibilityEvent);
        if (n() > 0) {
            AccessibilityRecordCompat asRecord = AccessibilityEventCompat.asRecord(accessibilityEvent);
            View b = b(false);
            View c = c(false);
            if (b != null && c != null) {
                int a = a(b);
                int a2 = a(c);
                if (a < a2) {
                    asRecord.setFromIndex(a);
                    asRecord.setToIndex(a2);
                    return;
                }
                asRecord.setFromIndex(a2);
                asRecord.setToIndex(a);
            }
        }
    }

    public final int a(m mVar, q qVar) {
        return this.i == 0 ? this.a : super.a(mVar, qVar);
    }

    public final int b(m mVar, q qVar) {
        return this.i == 1 ? this.a : super.b(mVar, qVar);
    }

    private View b(boolean z) {
        View view;
        int b = this.c.b();
        int c = this.c.c();
        int n = n();
        Object obj = null;
        int i = 0;
        while (i < n) {
            View e = e(i);
            int a = this.c.a(e);
            if (this.c.b(e) <= b || a >= c || (a >= b || !z)) {
                return e;
            }
            if (view == null) {
                i++;
                view = e;
            }
            e = view;
            i++;
            view = e;
        }
        return view;
    }

    private View c(boolean z) {
        View view;
        int b = this.c.b();
        int c = this.c.c();
        Object obj = null;
        int n = n() - 1;
        while (n >= 0) {
            View e = e(n);
            int a = this.c.a(e);
            int b2 = this.c.b(e);
            if (b2 <= b || a >= c || (b2 <= c || !z)) {
                return e;
            }
            if (view == null) {
                n--;
                view = e;
            }
            e = view;
            n--;
            view = e;
        }
        return view;
    }

    private void a(m mVar, q qVar, boolean z) {
        int k = k(ExploreByTouchHelper.INVALID_ID);
        if (k != Integer.MIN_VALUE) {
            k = this.c.c() - k;
            if (k > 0) {
                k -= -c(-k, mVar, qVar);
                if (z && k > 0) {
                    this.c.a(k);
                }
            }
        }
    }

    private void b(m mVar, q qVar, boolean z) {
        int j = j(InMobiClientPositioning.NO_REPEAT);
        if (j != Integer.MAX_VALUE) {
            j -= this.c.b();
            if (j > 0) {
                j -= c(j, mVar, qVar);
                if (z && j > 0) {
                    this.c.a(-j);
                }
            }
        }
    }

    private void a(int i, q qVar) {
        int i2;
        int i3;
        boolean z;
        aq aqVar;
        boolean z2 = false;
        this.k.b = 0;
        this.k.c = i;
        if (m()) {
            i2 = qVar.a;
            if (i2 != -1) {
                if (this.e == (i2 < i)) {
                    i2 = this.c.e();
                    i3 = 0;
                } else {
                    i3 = this.c.e();
                    i2 = 0;
                }
                if (this.q == null && this.q.mClipToPadding) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    this.k.g = i2 + this.c.d();
                    this.k.f = -i3;
                } else {
                    this.k.f = this.c.b() - i3;
                    this.k.g = i2 + this.c.c();
                }
                this.k.h = false;
                this.k.a = true;
                aqVar = this.k;
                if (this.c.g() == 0 && this.c.d() == 0) {
                    z2 = true;
                }
                aqVar.i = z2;
            }
        }
        i2 = 0;
        i3 = 0;
        if (this.q == null) {
        }
        z = false;
        if (z) {
            this.k.g = i2 + this.c.d();
            this.k.f = -i3;
        } else {
            this.k.f = this.c.b() - i3;
            this.k.g = i2 + this.c.c();
        }
        this.k.h = false;
        this.k.a = true;
        aqVar = this.k;
        z2 = true;
        aqVar.i = z2;
    }

    private void i(int i) {
        int i2 = 1;
        this.k.e = i;
        aq aqVar = this.k;
        boolean z = this.e;
        if (i == -1) {
            int i3 = 1;
        } else {
            Object obj = null;
        }
        if (z != r2) {
            i2 = -1;
        }
        aqVar.d = i2;
    }

    public final void f(int i) {
        super.f(i);
        for (int i2 = 0; i2 < this.a; i2++) {
            this.b[i2].d(i);
        }
    }

    public final void g(int i) {
        super.g(i);
        for (int i2 = 0; i2 < this.a; i2++) {
            this.b[i2].d(i);
        }
    }

    public final void b(int i, int i2) {
        d(i, i2, XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    public final void a(int i, int i2) {
        d(i, i2, 1);
    }

    public final void a() {
        this.h.a();
        l();
    }

    public final void d(int i, int i2) {
        d(i, i2, XZBDevice.Wait);
    }

    public final void c(int i, int i2) {
        d(i, i2, XZBDevice.DOWNLOAD_LIST_ALL);
    }

    private void d(int i, int i2, int i3) {
        int i4;
        int i5;
        int v = this.e ? v() : w();
        if (i3 != 8) {
            i4 = i + i2;
            i5 = i;
        } else if (i < i2) {
            i4 = i2 + 1;
            i5 = i;
        } else {
            i4 = i + 1;
            i5 = i2;
        }
        this.h.b(i5);
        switch (i3) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.h.b(i, i2);
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.h.a(i, i2);
                break;
            case XZBDevice.Wait:
                this.h.a(i, 1);
                this.h.b(i2, 1);
                break;
        }
        if (i4 > v) {
            if (i5 <= (this.e ? w() : v())) {
                l();
            }
        }
    }

    private int a(m mVar, aq aqVar, q qVar) {
        int i;
        int c;
        int b;
        this.m.set(0, this.a, true);
        if (this.k.i) {
            if (aqVar.e == 1) {
                i = Integer.MAX_VALUE;
            } else {
                i = Integer.MIN_VALUE;
            }
        } else if (aqVar.e == 1) {
            i = aqVar.g + aqVar.b;
        } else {
            i = aqVar.f - aqVar.b;
        }
        e(aqVar.e, i);
        if (this.e) {
            c = this.c.c();
        } else {
            c = this.c.b();
        }
        Object obj = null;
        while (true) {
            Object obj2;
            if (aqVar.c < 0 || aqVar.c >= qVar.a()) {
                obj2 = null;
            } else {
                obj2 = 1;
            }
            if (obj2 != null) {
                if (!this.k.i && this.m.isEmpty()) {
                    break;
                }
                Object obj3;
                b bVar;
                int i2;
                int i3;
                int b2;
                View b3 = mVar.b(aqVar.c);
                aqVar.c += aqVar.d;
                LayoutParams layoutParams = (LayoutParams) b3.getLayoutParams();
                int layoutPosition = layoutParams.c.getLayoutPosition();
                LazySpanLookup lazySpanLookup = this.h;
                int i4 = (lazySpanLookup.a == null || layoutPosition >= lazySpanLookup.a.length) ? -1 : lazySpanLookup.a[layoutPosition];
                if (i4 == -1) {
                    int i5 = 1;
                } else {
                    obj3 = null;
                }
                if (obj3 != null) {
                    if (layoutParams.b) {
                        bVar = this.b[0];
                    } else {
                        if (l(aqVar.e)) {
                            i4 = this.a - 1;
                            i2 = -1;
                            i3 = -1;
                        } else {
                            i4 = 0;
                            i2 = this.a;
                            i3 = 1;
                        }
                        Object obj4;
                        int b4;
                        int i6;
                        b bVar2;
                        int i7;
                        int i8;
                        if (aqVar.e == 1) {
                            bVar = null;
                            obj4 = InMobiClientPositioning.NO_REPEAT;
                            b4 = this.c.b();
                            i6 = i4;
                            while (i6 != i2) {
                                bVar2 = this.b[i6];
                                b2 = bVar2.b(b4);
                                if (b2 < i7) {
                                    i8 = b2;
                                } else {
                                    bVar2 = bVar;
                                    i8 = i7;
                                }
                                i6 += i3;
                                i7 = i8;
                                bVar = bVar2;
                            }
                        } else {
                            bVar = null;
                            obj4 = ExploreByTouchHelper.INVALID_ID;
                            b4 = this.c.c();
                            i6 = i4;
                            while (i6 != i2) {
                                bVar2 = this.b[i6];
                                b2 = bVar2.a(b4);
                                if (b2 > i7) {
                                    i8 = b2;
                                } else {
                                    bVar2 = bVar;
                                    i8 = i7;
                                }
                                i6 += i3;
                                i7 = i8;
                                bVar = bVar2;
                            }
                        }
                    }
                    lazySpanLookup = this.h;
                    lazySpanLookup.c(layoutPosition);
                    lazySpanLookup.a[layoutPosition] = bVar.e;
                } else {
                    bVar = this.b[i4];
                }
                layoutParams.a = bVar;
                if (aqVar.e == 1) {
                    super.a(b3, -1, false);
                } else {
                    super.a(b3, 0, false);
                }
                if (layoutParams.b) {
                    if (this.i == 1) {
                        a(b3, this.C, a(this.z, this.x, 0, layoutParams.height, true));
                    } else {
                        a(b3, a(this.y, this.w, 0, layoutParams.width, true), this.C);
                    }
                } else if (this.i == 1) {
                    a(b3, a(this.j, this.w, 0, layoutParams.width, false), a(this.z, this.x, 0, layoutParams.height, true));
                } else {
                    a(b3, a(this.y, this.w, 0, layoutParams.width, true), a(this.j, this.x, 0, layoutParams.height, false));
                }
                FullSpanItem fullSpanItem;
                if (aqVar.e == 1) {
                    i3 = layoutParams.b ? k(c) : bVar.b(c);
                    i2 = i3 + this.c.c(b3);
                    if (obj3 == null || !layoutParams.b) {
                        i4 = i3;
                        i3 = i2;
                    } else {
                        fullSpanItem = new FullSpanItem();
                        fullSpanItem.c = new int[this.a];
                        for (i4 = 0; i4 < this.a; i4++) {
                            fullSpanItem.c[i4] = i3 - this.b[i4].b(i3);
                        }
                        fullSpanItem.b = -1;
                        fullSpanItem.a = layoutPosition;
                        this.h.a(fullSpanItem);
                        i4 = i3;
                        i3 = i2;
                    }
                } else {
                    i3 = layoutParams.b ? j(c) : bVar.a(c);
                    i2 = i3 - this.c.c(b3);
                    if (obj3 != null && layoutParams.b) {
                        fullSpanItem = new FullSpanItem();
                        fullSpanItem.c = new int[this.a];
                        for (i4 = 0; i4 < this.a; i4++) {
                            fullSpanItem.c[i4] = this.b[i4].a(i3) - i3;
                        }
                        fullSpanItem.b = 1;
                        fullSpanItem.a = layoutPosition;
                        this.h.a(fullSpanItem);
                    }
                    i4 = i2;
                }
                if (layoutParams.b && aqVar.d == -1) {
                    if (obj3 == null) {
                        Object obj5;
                        if (aqVar.e == 1) {
                            b2 = this.b[0].b((int) ExploreByTouchHelper.INVALID_ID);
                            for (i2 = 1; i2 < this.a; i2++) {
                                if (this.b[i2].b((int) ExploreByTouchHelper.INVALID_ID) != b2) {
                                    obj5 = null;
                                    break;
                                }
                            }
                            obj5 = 1;
                            obj5 = obj5 == null ? 1 : null;
                        } else {
                            b2 = this.b[0].a((int) ExploreByTouchHelper.INVALID_ID);
                            for (i2 = 1; i2 < this.a; i2++) {
                                if (this.b[i2].a((int) ExploreByTouchHelper.INVALID_ID) != b2) {
                                    obj5 = null;
                                    break;
                                }
                            }
                            obj5 = 1;
                            obj5 = obj5 == null ? 1 : null;
                        }
                        if (obj5 != null) {
                            FullSpanItem d = this.h.d(layoutPosition);
                            if (d != null) {
                                d.d = true;
                            }
                        }
                    }
                    this.F = true;
                }
                if (aqVar.e == 1) {
                    if (layoutParams.b) {
                        for (i2 = this.a - 1; i2 >= 0; i2--) {
                            this.b[i2].b(b3);
                        }
                    } else {
                        layoutParams.a.b(b3);
                    }
                } else if (layoutParams.b) {
                    for (i2 = this.a - 1; i2 >= 0; i2--) {
                        this.b[i2].a(b3);
                    }
                } else {
                    layoutParams.a.a(b3);
                }
                if (u() && this.i == 1) {
                    i2 = layoutParams.b ? this.d.c() : this.d.c() - (((this.a - 1) - bVar.e) * this.j);
                    b2 = i2;
                    i2 -= this.d.c(b3);
                } else {
                    i2 = layoutParams.b ? this.d.b() : (bVar.e * this.j) + this.d.b();
                    b2 = this.d.c(b3) + i2;
                }
                if (this.i == 1) {
                    b(b3, i2, i4, b2, i3);
                } else {
                    b(b3, i4, i2, i3, b2);
                }
                if (layoutParams.b) {
                    e(this.k.e, i);
                } else {
                    a(bVar, this.k.e, i);
                }
                a(mVar, this.k);
                if (this.k.h && b3.isFocusable()) {
                    if (layoutParams.b) {
                        this.m.clear();
                    } else {
                        this.m.set(bVar.e, false);
                    }
                }
                obj = 1;
            } else {
                break;
            }
        }
        if (obj == null) {
            a(mVar, this.k);
        }
        if (this.k.e == -1) {
            b = this.c.b() - j(this.c.b());
        } else {
            b = k(this.c.c()) - this.c.c();
        }
        return b > 0 ? Math.min(aqVar.b, b) : 0;
    }

    private void a(m mVar, aq aqVar) {
        int i = 1;
        if (aqVar.a && !aqVar.i) {
            if (aqVar.b == 0) {
                if (aqVar.e == -1) {
                    b(mVar, aqVar.g);
                } else {
                    a(mVar, aqVar.f);
                }
            } else if (aqVar.e == -1) {
                r3 = aqVar.f;
                int i2 = aqVar.f;
                a = this.b[0].a(i2);
                while (i < this.a) {
                    a = this.b[i].a(i2);
                    if (a > a) {
                        a = a;
                    }
                    i++;
                }
                i = r3 - a;
                if (i < 0) {
                    i = aqVar.g;
                } else {
                    i = aqVar.g - Math.min(i, aqVar.b);
                }
                b(mVar, i);
            } else {
                r3 = aqVar.g;
                a = this.b[0].b(r3);
                while (i < this.a) {
                    a = this.b[i].b(r3);
                    if (a < a) {
                        a = a;
                    }
                    i++;
                }
                i = a - aqVar.g;
                if (i < 0) {
                    i = aqVar.f;
                } else {
                    i = Math.min(i, aqVar.b) + aqVar.f;
                }
                a(mVar, i);
            }
        }
    }

    private static void b(View view, int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        a(view, layoutParams.leftMargin + i, layoutParams.topMargin + i2, i3 - layoutParams.rightMargin, i4 - layoutParams.bottomMargin);
    }

    private void e(int i, int i2) {
        for (int i3 = 0; i3 < this.a; i3++) {
            if (!this.b[i3].a.isEmpty()) {
                a(this.b[i3], i, i2);
            }
        }
    }

    private void a(b bVar, int i, int i2) {
        int i3 = bVar.d;
        if (i == -1) {
            if (i3 + bVar.a() <= i2) {
                this.m.set(bVar.e, false);
            }
        } else if (bVar.b() - i3 >= i2) {
            this.m.set(bVar.e, false);
        }
    }

    private int j(int i) {
        int a = this.b[0].a(i);
        for (int i2 = 1; i2 < this.a; i2++) {
            int a2 = this.b[i2].a(i);
            if (a2 < a) {
                a = a2;
            }
        }
        return a;
    }

    private int k(int i) {
        int b = this.b[0].b(i);
        for (int i2 = 1; i2 < this.a; i2++) {
            int b2 = this.b[i2].b(i);
            if (b2 > b) {
                b = b2;
            }
        }
        return b;
    }

    private void a(m mVar, int i) {
        while (n() > 0) {
            View e = e(0);
            if (this.c.b(e) <= i) {
                LayoutParams layoutParams = (LayoutParams) e.getLayoutParams();
                if (layoutParams.b) {
                    int i2 = 0;
                    while (i2 < this.a) {
                        if (this.b[i2].a.size() != 1) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    for (i2 = 0; i2 < this.a; i2++) {
                        this.b[i2].e();
                    }
                } else if (layoutParams.a.a.size() != 1) {
                    layoutParams.a.e();
                } else {
                    return;
                }
                a(e, mVar);
            } else {
                return;
            }
        }
    }

    private void b(m mVar, int i) {
        int n = n() - 1;
        while (n >= 0) {
            View e = e(n);
            if (this.c.a(e) >= i) {
                LayoutParams layoutParams = (LayoutParams) e.getLayoutParams();
                if (layoutParams.b) {
                    int i2 = 0;
                    while (i2 < this.a) {
                        if (this.b[i2].a.size() != 1) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    for (i2 = 0; i2 < this.a; i2++) {
                        this.b[i2].d();
                    }
                } else if (layoutParams.a.a.size() != 1) {
                    layoutParams.a.d();
                } else {
                    return;
                }
                a(e, mVar);
                n--;
            } else {
                return;
            }
        }
    }

    private boolean l(int i) {
        boolean z;
        Object obj;
        if (this.i == 0) {
            if (i == -1) {
                z = true;
            } else {
                obj = null;
            }
            return z != this.e;
        } else {
            if (i == -1) {
                z = true;
            } else {
                obj = null;
            }
            if (z == this.e) {
                z = true;
            } else {
                obj = null;
            }
            return z == u();
        }
    }

    public final boolean f() {
        return this.i == 1;
    }

    public final boolean e() {
        return this.i == 0;
    }

    public final int a(int i, m mVar, q qVar) {
        return c(i, mVar, qVar);
    }

    public final int b(int i, m mVar, q qVar) {
        return c(i, mVar, qVar);
    }

    private int m(int i) {
        if (n() == 0) {
            return this.e ? 1 : -1;
        } else {
            if (i < w()) {
                int i2 = 1;
            } else {
                Object obj = null;
            }
            return r0 != this.e ? -1 : 1;
        }
    }

    public final void a(RecyclerView recyclerView, int i) {
        p cdVar = new cd(this, recyclerView.getContext());
        cdVar.g = i;
        a(cdVar);
    }

    public final void c(int i) {
        if (!(this.B == null || this.B.a == i)) {
            SavedState savedState = this.B;
            savedState.d = null;
            savedState.c = 0;
            savedState.a = -1;
            savedState.b = -1;
        }
        this.f = i;
        this.g = Integer.MIN_VALUE;
        l();
    }

    private int c(int i, m mVar, q qVar) {
        int v;
        int i2;
        if (i > 0) {
            v = v();
            i2 = 1;
        } else {
            i2 = -1;
            v = w();
        }
        this.k.a = true;
        a(v, qVar);
        i(i2);
        this.k.c = this.k.d + v;
        int abs = Math.abs(i);
        this.k.b = abs;
        i2 = a(mVar, this.k, qVar);
        if (abs >= i2) {
            if (i < 0) {
                i = -i2;
            } else {
                i = i2;
            }
        }
        this.c.a(-i);
        this.o = this.e;
        return i;
    }

    private int v() {
        int n = n();
        return n == 0 ? 0 : a(e(n - 1));
    }

    private int w() {
        return n() == 0 ? 0 : a(e(0));
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

    public final View a(View view, int i, m mVar, q qVar) {
        int i2 = 0;
        if (n() == 0) {
            return null;
        }
        View b = b(view);
        if (b == null) {
            return null;
        }
        int i3;
        k();
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                i3 = -1;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                i3 = 1;
                break;
            case R.styleable.Toolbar_maxButtonHeight:
                i3 = this.i == 0 ? -1 : Integer.MIN_VALUE;
                break;
            case R.styleable.AppCompatTheme_actionModeCopyDrawable:
                i3 = this.i == 1 ? -1 : Integer.MIN_VALUE;
                break;
            case R.styleable.AppCompatTheme_textAppearanceSearchResultSubtitle:
                i3 = this.i == 0 ? 1 : Integer.MIN_VALUE;
                break;
            case TransportMediator.KEYCODE_MEDIA_RECORD:
                i3 = this.i == 1 ? 1 : Integer.MIN_VALUE;
                break;
            default:
                i3 = Integer.MIN_VALUE;
                break;
        }
        if (i3 == Integer.MIN_VALUE) {
            return null;
        }
        int v;
        View b2;
        LayoutParams layoutParams = (LayoutParams) b.getLayoutParams();
        boolean z = layoutParams.b;
        b bVar = layoutParams.a;
        if (i3 == 1) {
            v = v();
        } else {
            v = w();
        }
        a(v, qVar);
        i(i3);
        this.k.c = this.k.d + v;
        this.k.b = (int) (0.33333334f * ((float) this.c.e()));
        this.k.h = true;
        this.k.a = false;
        a(mVar, this.k, qVar);
        this.o = this.e;
        if (!z) {
            b2 = bVar.b(v, i3);
            if (!(b2 == null || b2 == b)) {
                return b2;
            }
        }
        if (l(i3)) {
            for (int i4 = this.a - 1; i4 >= 0; i4--) {
                View b3 = this.b[i4].b(v, i3);
                if (b3 != null && b3 != b) {
                    return b3;
                }
            }
        } else {
            while (i2 < this.a) {
                b2 = this.b[i2].b(v, i3);
                if (b2 != null && b2 != b) {
                    return b2;
                }
                i2++;
            }
        }
        return null;
    }
}
