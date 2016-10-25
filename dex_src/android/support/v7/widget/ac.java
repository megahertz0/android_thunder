package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.t;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.xunlei.tdlive.R;
import java.util.ArrayList;
import java.util.List;

// compiled from: ChildHelper.java
final class ac {
    final b a;
    final a b;
    final List<View> c;

    // compiled from: ChildHelper.java
    static class a {
        long a;
        a b;

        a() {
            this.a = 0;
        }

        final void a(int i) {
            while (i >= 64) {
                a();
                this = this.b;
                i -= 64;
            }
            this.a |= 1 << i;
        }

        private void a() {
            if (this.b == null) {
                this.b = new a();
            }
        }

        final void b(int i) {
            while (i >= 64) {
                if (this.b != null) {
                    this = this.b;
                    i -= 64;
                } else {
                    return;
                }
            }
            this.a &= (1 << i) ^ -1;
        }

        final boolean c(int i) {
            while (i >= 64) {
                a();
                this = this.b;
                i -= 64;
            }
            return (this.a & (1 << i)) != 0;
        }

        final void a(int i, boolean z) {
            while (true) {
                if (i >= 64) {
                    a();
                    this = this.b;
                    i -= 64;
                } else {
                    boolean z2 = (this.a & Long.MIN_VALUE) != 0 ? 1 : null;
                    long j = (1 << i) - 1;
                    this.a = (((j ^ -1) & this.a) << 1) | (this.a & j);
                    if (z) {
                        a(i);
                    } else {
                        b(i);
                    }
                    if (z2 || this.b != null) {
                        a();
                        this = this.b;
                        i = 0;
                        z = z2;
                    } else {
                        return;
                    }
                }
            }
        }

        final boolean d(int i) {
            while (i >= 64) {
                a();
                this = this.b;
                i -= 64;
            }
            long j = 1 << i;
            boolean z = (this.a & j) != 0;
            this.a &= j ^ -1;
            j--;
            this.a = Long.rotateRight((j ^ -1) & this.a, 1) | (this.a & j);
            if (this.b != null) {
                if (this.b.c(0)) {
                    a(R.styleable.AppCompatTheme_editTextBackground);
                }
                this.b.d(0);
            }
            return z;
        }

        final int e(int i) {
            return this.b == null ? i >= 64 ? Long.bitCount(this.a) : Long.bitCount(this.a & ((1 << i) - 1)) : i < 64 ? Long.bitCount(this.a & ((1 << i) - 1)) : this.b.e(i - 64) + Long.bitCount(this.a);
        }

        public final String toString() {
            return this.b == null ? Long.toBinaryString(this.a) : this.b.toString() + "xx" + Long.toBinaryString(this.a);
        }
    }

    // compiled from: ChildHelper.java
    static interface b {
        int a();

        int a(View view);

        void a(int i);

        void a(View view, int i);

        void a(View view, int i, LayoutParams layoutParams);

        t b(View view);

        View b(int i);

        void b();

        void c(int i);

        void c(View view);

        void d(View view);
    }

    ac(b bVar) {
        this.a = bVar;
        this.b = new a();
        this.c = new ArrayList();
    }

    final void a(View view) {
        this.c.add(view);
        this.a.c(view);
    }

    final boolean b(View view) {
        if (!this.c.remove(view)) {
            return false;
        }
        this.a.d(view);
        return true;
    }

    final void a(View view, int i, boolean z) {
        int a;
        if (i < 0) {
            a = this.a.a();
        } else {
            a = a(i);
        }
        this.b.a(a, z);
        if (z) {
            a(view);
        }
        this.a.a(view, a);
    }

    final int a(int i) {
        if (i < 0) {
            return -1;
        }
        int a = this.a.a();
        int i2 = i;
        while (i2 < a) {
            int e = i - (i2 - this.b.e(i2));
            if (e == 0) {
                while (this.b.c(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += e;
        }
        return -1;
    }

    final View b(int i) {
        return this.a.b(a(i));
    }

    final void a(View view, int i, LayoutParams layoutParams, boolean z) {
        int a;
        if (i < 0) {
            a = this.a.a();
        } else {
            a = a(i);
        }
        this.b.a(a, z);
        if (z) {
            a(view);
        }
        this.a.a(view, a, layoutParams);
    }

    final int a() {
        return this.a.a() - this.c.size();
    }

    final int b() {
        return this.a.a();
    }

    final View c(int i) {
        return this.a.b(i);
    }

    final void d(int i) {
        int a = a(i);
        this.b.d(a);
        this.a.c(a);
    }

    final int c(View view) {
        int a = this.a.a(view);
        return (a == -1 || this.b.c(a)) ? -1 : a - this.b.e(a);
    }

    final boolean d(View view) {
        return this.c.contains(view);
    }

    public final String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }
}
