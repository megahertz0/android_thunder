package uk.co.senab.photoview.c;

import android.annotation.TargetApi;
import android.content.Context;
import android.widget.OverScroller;

@TargetApi(9)
// compiled from: GingerScroller.java
public class a extends d {
    protected final OverScroller a;
    private boolean b;

    public a(Context context) {
        this.b = false;
        this.a = new OverScroller(context);
    }

    public boolean a() {
        if (this.b) {
            this.a.computeScrollOffset();
            this.b = false;
        }
        return this.a.computeScrollOffset();
    }

    public final void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.a.fling(i, i2, i3, i4, i5, i6, i7, i8, 0, 0);
    }

    public final void b() {
        this.a.forceFinished(true);
    }

    public final boolean c() {
        return this.a.isFinished();
    }

    public final int d() {
        return this.a.getCurrX();
    }

    public final int e() {
        return this.a.getCurrY();
    }
}
