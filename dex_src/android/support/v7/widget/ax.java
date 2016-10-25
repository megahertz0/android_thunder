package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.h;
import android.view.View;
import org.android.spdy.SpdyAgent;

// compiled from: OrientationHelper.java
public abstract class ax {
    protected final h a;
    int b;

    public abstract int a(View view);

    public abstract void a(int i);

    public abstract int b();

    public abstract int b(View view);

    public abstract int c();

    public abstract int c(View view);

    public abstract int d();

    public abstract int d(View view);

    public abstract int e();

    public abstract int f();

    public abstract int g();

    public abstract int h();

    private ax(h hVar) {
        this.b = Integer.MIN_VALUE;
        this.a = hVar;
    }

    public final int a() {
        return Integer.MIN_VALUE == this.b ? 0 : e() - this.b;
    }

    public static ax a(h hVar, int i) {
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                return new ay(hVar);
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return new az(hVar);
            default:
                throw new IllegalArgumentException("invalid orientation");
        }
    }
}
